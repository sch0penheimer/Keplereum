import { Component, OnInit, AfterViewInit } from '@angular/core';
import * as THREE from 'three';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader.js';
import { loadSatelliteModel,
  calculateSatelliteOrbitalTrajectory,
  createSatelliteOrbitPath, 
  updateSatellitePosition
} from './utils/satelliteUtils';


@Component({
  selector: 'appSatelliteViewer',
  templateUrl: './satelliteViewer.component.html',
  styleUrls: ['']
})


export class SatelliteViewerComponent implements OnInit, AfterViewInit {
  private scene = new THREE.Scene();
  private camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
  private renderer = new THREE.WebGLRenderer({ antialias: true });
  private textureLoader = new THREE.TextureLoader();

  private controls = new OrbitControls(this.camera, this.renderer.domElement);
  
  //* Earth related objects *//
  private earth!: THREE.Mesh;
  private clouds!: THREE.Mesh;
  private stars!: THREE.Points;
  private satellites: any[] = [];
  private orbitPaths: any[] = [];
  private ambientLight = new THREE.AmbientLight(0xffffff, 2)
  

  //* Constants *//
  private earthRadius = 6371;
  private earthRadiusSceneUnits = 4;
  private scaleFactor = this.earthRadiusSceneUnits / this.earthRadius;
  private satelliteModelPath = 'assets/main-sat.glb';

  private satellitesData = [
    {
        id: "sat-1",
        name: "sat-1",
        color: "#00ffff",
        perigeeAltitude: 500,
        eccentricity: 0.1,
        inclination: 45,
        longitudeOfAscendingNode: 0,
        argumentOfPeriapsis: 0,
        speedMultiplier: 20,
        maxSensorBaseRadius: 0.9,
        maxSensorHeight: 2.3
    },
    {
        id: "sat-2",
        name: "sat-2",
        color: "#ff00ff",
        perigeeAltitude: 800,
        eccentricity: 0.7,
        inclination: 60,
        longitudeOfAscendingNode: 120,
        argumentOfPeriapsis: 45,
        speedMultiplier: 20,
        maxSensorBaseRadius: 0.9,
        maxSensorHeight: 3.5
    },
    {
        id: "sat-3",
        name: "sat-3",
        color: "#ffff00",
        perigeeAltitude: 1200,
        eccentricity: 0.15,
        inclination: 30,
        longitudeOfAscendingNode: 240,
        argumentOfPeriapsis: 90,
        speedMultiplier: 20,
        maxSensorBaseRadius: 0.9,
        maxSensorHeight: 2.3
    }
];
  ngOnInit(): void {
    //** afterViewInit is enough **/
  }

  ngAfterViewInit(): void {
    this.init();
  }

  /**
    * Initialize the application
    * @method init
    */
  private init(): void {
    this.setupRenderer();
    this.setupCamera();
    this.setupControls();
    this.setupLights();
    this.createEarth();
    this.createStars();
    this.setupUI();
    this.addEventListeners();
    //this.createTornado();
    //** Load satellites from JSON **//
    this.loadSatellitesFromJSON(this.satellitesData);
    //** Start animation loop **//
    this.animate();
  }


  /**
    * Setup the WebGL renderer
    * @method setupRenderer
    */
  private setupRenderer(): void {
    this.renderer.setSize(window.innerWidth, window.innerHeight);
    this.renderer.setPixelRatio(window.devicePixelRatio);
    this.renderer.shadowMap.enabled = true;

    //* Somehow a TS error? Works fine in a JS environnement */
    // this.renderer.physicallyCorrectLights = true;
    
    this.renderer.toneMapping = THREE.ACESFilmicToneMapping;
    this.renderer.toneMappingExposure = 1.5;
    document.body.appendChild(this.renderer.domElement);
  }

  /**
    * Setup the camera
    * @method setupCamera
    */
  private setupCamera(): void {
    this.camera.position.z = 15;
  }

  /**
    * Setup the orbit controls
    * @method setupControls
    */
  private setupControls(): void {
    this.controls.enableDamping = true;
    this.controls.dampingFactor = 0.1;
    this.controls.zoomSpeed = 0.4;
    this.controls.rotateSpeed = 0.8;
    this.controls.minDistance = 5;
    this.controls.maxDistance = 30;
  }

  /**
    * Setup scene lighting
    * @method setupLights
    */
  private setupLights(): void {;
    this.scene.add(this.ambientLight);
  }

  /**
    * Create the Earth object
    * @method createEarth
    */
  private createEarth(): void {
    const earthGeometry = new THREE.SphereGeometry(this.earthRadiusSceneUnits, 64, 64);

    //** Load Earth textures **//
    const earthMap = this.textureLoader.load('https://cdn.jsdelivr.net/gh/mrdoob/three.js@dev/examples/textures/planets/earth_atmos_2048.jpg');
    const earthBumpMap = this.textureLoader.load('https://cdn.jsdelivr.net/gh/mrdoob/three.js@dev/examples/textures/planets/earth_normal_2048.jpg');
    const earthSpecMap = this.textureLoader.load('https://cdn.jsdelivr.net/gh/mrdoob/three.js@dev/examples/textures/planets/earth_specular_2048.jpg');
    
    //** Earth material creation **//
    const earthMaterial = new THREE.MeshPhongMaterial({
      map: earthMap,
      bumpMap: earthBumpMap,
      bumpScale: 0.1,
      specularMap: earthSpecMap,
      specular: new THREE.Color('white'),
      shininess: 15
    });

    //** Create Earth mesh **//
    this.earth = new THREE.Mesh(earthGeometry, earthMaterial);
    this.earth.receiveShadow = true;
    this.scene.add(this.earth);

    //** Cloud layer **//
    const cloudsGeometry = new THREE.SphereGeometry(this.earthRadiusSceneUnits + 0.03, 64, 64);
    const cloudsMaterial = new THREE.MeshPhongMaterial({
      map: this.textureLoader.load('https://cdn.jsdelivr.net/gh/mrdoob/three.js@dev/examples/textures/planets/earth_clouds_1024.png'),
      transparent: true,
      opacity: 0.8
    });

    this.clouds = new THREE.Mesh(cloudsGeometry, cloudsMaterial);
    this.scene.add(this.clouds);
  }

  /**
    * Create the background stars
    * @method createStars
    */
  private createStars(): void {
    const starsGeometry = new THREE.BufferGeometry();
    const starsMaterial = new THREE.PointsMaterial({
      color: 0xffffff,
      size: 0.3,
      transparent: true
    });

    const starsVertices = [];
    for (let i = 0; i < 15000; i++) {
      const [x, y, z] = Array(3).fill(0).map(() => THREE.MathUtils.randFloatSpread(2000));
      starsVertices.push(x, y, z);
    }

    starsGeometry.setAttribute('position', new THREE.Float32BufferAttribute(starsVertices, 3));
    this.stars = new THREE.Points(starsGeometry, starsMaterial);
    this.scene.add(this.stars);
  }

  /**
    * Load satellites from JSON data
    * @method loadSatellitesFromJSON
    */
  private loadSatellitesFromJSON(satellitesData: Array<Object>): void {
    
    satellitesData.forEach((data, index) => {
        this.createSatellite(data, index, satellitesData.length);
    });
  }

  /**
    * Create a satellite based on JSON data
    * @method createSatellite
    * @param {Object} data - Satellite data from JSON
    * @param {number} index - Index of this satellite
    * @param {number} total - Total number of satellites
    */
  private createSatellite(data: any, index: any, total: any): void {
    //** Satellite object **//
    const satellite = {             
      id: data.id,
      name: data.name,

      //** Group Container for all satellite components **//
      group: new THREE.Group(),
      orbitParams: {
        perigeeAltitude: data.perigeeAltitude,
        eccentricity: data.eccentricity,
        inclination: data.inclination,
        longitudeOfAscendingNode: data.longitudeOfAscendingNode,
        argumentOfPeriapsis: data.argumentOfPeriapsis,
        speedMultiplier: data.speedMultiplier,
        maxSensorBaseRadius: data.maxSensorBaseRadius,
        maxSensorHeight: data.maxSensorHeight
      },
      animationParams: {
        currentTime: 0,
        periodSeconds: 0,
        orbitPoints: []
      },
      color: parseInt(data.color.replace('#', '0x')),
      //* Place Holders for highlighter, sensorCone, label and its default scale *//
      highlight: null as THREE.Mesh | null,
      sensorCone: null as THREE.Mesh | null,
      label: null as THREE.Sprite | null,
      labelBaseScale: { x: 1, y: 1, z: 1 }
    };

    //** Add satellite group to scene **//
    this.scene.add(satellite.group);

    //** Add placeholder until model loads **//
    const satellitePlaceholder = new THREE.Mesh(
        new THREE.BoxGeometry(0.2, 0.2, 0.2),
        new THREE.MeshStandardMaterial({ 
            color: satellite.color,
            emissive: satellite.color,
            emissiveIntensity: 0.5
        })
    );
    satellitePlaceholder.lookAt(0, 0, 0);
    satellite.group.add(satellitePlaceholder);

    //** 1- Satellite highlight dot **//
    const highlightGeometry = new THREE.SphereGeometry(0.03, 16, 16);
    const highlightMaterial = new THREE.MeshBasicMaterial({ 
        color: satellite.color,
        transparent: true,
        opacity: 0.8
    });

    satellite.highlight = new THREE.Mesh(highlightGeometry, highlightMaterial);
    satellite.group.add(satellite.highlight);

    //** 2- Sensor cone pointing to Earth **//
    //* Height is 1 by default, should be passed with the satellite data along with the base radius *//
    const coneGeometry = new THREE.ConeGeometry(satellite.orbitParams.maxSensorBaseRadius, 1, 64); 

    const coneMaterial = new THREE.MeshBasicMaterial({ 
        color: satellite.color,
        transparent: true,
        opacity: 0.5
    });

    satellite.sensorCone = new THREE.Mesh(coneGeometry, coneMaterial);
    
    //* Position the cone slightly below the satellite *//
    satellite.sensorCone.position.set(0, -0.05, 0);
    satellite.sensorCone.lookAt(0, 0, 0);

    
    satellite.group.add(satellite.sensorCone);

    //** 3- Satellite label **//
    const canvas = document.createElement('canvas');
    const context = canvas.getContext('2d');
    canvas.width = 300;
    canvas.height = 128;

    if (context) {
        context.font = 'Bold 32px Arial';
        context.fillStyle = data.color;
        context.textAlign = 'center';
        context.textBaseline = 'middle';
        context.fillText(data.name, 128, 32);
    }

    const texture = new THREE.CanvasTexture(canvas);
    const labelMaterial = new THREE.SpriteMaterial({ 
        map: texture,
        transparent: true
    });

    satellite.label = new THREE.Sprite(labelMaterial);
    satellite.label.scale.set(2, 0.8, 1);
    satellite.labelBaseScale = {x: 2, y: 0.8, z: 1};
    satellite.group.add(satellite.label);

    //** Calculate orbit points **//
    this.calculateSatelliteOrbit(satellite);

    //** Create orbit path visualization **//
    createSatelliteOrbitPath(satellite,this.scene,this.orbitPaths);

    //** 3D model loading **//
    const modelPath = data.model || this.satelliteModelPath;
    loadSatelliteModel(
        modelPath,
        satellitePlaceholder,
        satellite.group
    );

    //** Satellite -> satellites array **//
    this.satellites.push(satellite);

    //** Initialize the cone's height and orientation **//
    this.updateSensorCone(satellite);
  }

/*
  private createTornado(): void {
    const tornadoGroup = new THREE.Group();

    // Load Perlin noise texture
    const textureLoader = new THREE.TextureLoader();
    const perlinTexture = textureLoader.load("../../assets/rgb-256x256.png");
    perlinTexture.wrapS = THREE.RepeatWrapping;
    perlinTexture.wrapT = THREE.RepeatWrapping;

    // Tornado uniforms (Replace with shader material logic if necessary)
    const timeScale = 0.2;

    // Tornado Floor
    const floorMaterial = new THREE.MeshBasicMaterial({ transparent: true });
    const floorGeometry = new THREE.PlaneGeometry(2, 2);
    const floor = new THREE.Mesh(floorGeometry, floorMaterial);
    floor.rotation.x = -Math.PI * 0.5;
    tornadoGroup.add(floor);

    // Tornado Cylinder
    const cylinderGeometry = new THREE.CylinderGeometry(1, 1, 2, 20, 20, true);
    const emissiveMaterial = new THREE.MeshBasicMaterial({
        map: perlinTexture,
        transparent: true,
        side: THREE.DoubleSide
    });

    const tornadoMesh = new THREE.Mesh(cylinderGeometry, emissiveMaterial);
    tornadoMesh.position.y = 1;
    tornadoGroup.add(tornadoMesh);
    tornadoGroup.position.set(4,4,4);
    this.scene.add(tornadoGroup);
}
*/


  /**
    * Calculate the satellite's orbit parameters
    * @method calculateSatelliteOrbit
    * @param {Object} satellite - Satellite object
    */
  private calculateSatelliteOrbit(satellite: any): void {
    const result = calculateSatelliteOrbitalTrajectory(
      satellite.orbitParams.perigeeAltitude,
      satellite.orbitParams.eccentricity,
      satellite.orbitParams.inclination,
      satellite.orbitParams.longitudeOfAscendingNode,
      satellite.orbitParams.argumentOfPeriapsis,
      this.earthRadius,
      this.scaleFactor
    );
    
    satellite.animationParams.periodSeconds = result.periodSeconds;
    satellite.animationParams.orbitPoints = result.points3D;
  }

  /**
    * Create the satellite's orbit path
    * @method createSatelliteOrbitPath
    * @param {Object} satellite - Satellite object
    */
  private createSatelliteOrbitPath(satellite: any): void {
    const geometry = new THREE.BufferGeometry().setFromPoints(satellite.animationParams.orbitPoints);
    const material = new THREE.LineBasicMaterial({
      color: satellite.color,
      transparent: true,
      opacity: 0.8
    });
    
    const orbitPath = new THREE.Line(geometry, material);
    this.scene.add(orbitPath);
    satellite.orbitPath = orbitPath;
    this.orbitPaths.push(orbitPath);
  }

  /**
    * Update the satellites' positions and animations
    * @method updateSatellites
    */
  private updateSatellites(): void {
    this.satellites.forEach(satellite => {
      satellite.animationParams.currentTime += 0.016 * satellite.orbitParams.speedMultiplier;
      updateSatellitePosition(satellite);
      this.updateSatelliteOrientation(satellite);
      this.updateSensorCone(satellite);
      this.updateSatelliteLabel(satellite);
    });
  }


  /**
    * Update the satellite's orientation to face the Earth
    * @method updateSatelliteOrientation
    * @param {Object} satellite - Satellite object
    */
  private updateSatelliteOrientation(satellite: any): void {
    if (!satellite.group || !satellite.group.position) return;
    satellite.group.lookAt(0, 0, 0);
  }

  /**
    * Update the sensor cone based on the satellite's position and altitude
    * @method updateSensorCone
    * @param {Object} satellite - Satellite object
    */
  private updateSensorCone(satellite: any): void {
    if (!satellite.sensorCone) return;

    const satellitePosition = satellite.group.position;
    const altitude = satellitePosition.length() - this.earthRadiusSceneUnits;

    // // Console log the altitude
    // console.log(satellite.id + " LENGTH: " + altitude/this.scaleFactor);
    // // Console log the sensor radius
    // console.log(satellite.id + " RADIUS: " + satellite.sensorCone.scale.y/this.scaleFactor);

    // Calculate the base radius based on the ratio of maxSensorBaseRadius and maxSensorHeight
    const baseRadius = (altitude / satellite.orbitParams.maxSensorHeight) * 
                      satellite.orbitParams.maxSensorBaseRadius;
    const sensorHeight = altitude;

    // Update the scale of the sensor cone
    satellite.sensorCone.scale.set(
      baseRadius, 
      sensorHeight < 1 ? 1 : sensorHeight, 
      baseRadius
    );
    
    satellite.sensorCone.position.set(
      0, 
      0, 
      sensorHeight < 1 ? 0.5 : sensorHeight * 0.5
    );
  }

  /**
    * Update the satellite's label based on its distance to the camera
    * @method updateSatelliteLabel
    * @param {Object} satellite - Satellite object
    */
  private updateSatelliteLabel(satellite: any): void {
    if (!satellite.label || !satellite.labelBaseScale) return;
    
    const distanceToCamera = this.camera.position.distanceTo(satellite.group.position);
    const scaleFactor = distanceToCamera / 15;
    
    satellite.label.scale.set(
      satellite.labelBaseScale.x * scaleFactor,
      satellite.labelBaseScale.y * scaleFactor,
      satellite.labelBaseScale.z
    );
    
    satellite.label.position.set(0.5, 0.2, 0);
  }

  /**
    * Setup the UI for loading satellites
    * @method setupUI
    */
  private setupUI(): void {
    const controls = document.getElementById('controls');
    if (!controls) return;

    const fileInputDiv = document.createElement('div');
    fileInputDiv.innerHTML = `
      <label for="satellitesJson">Load Satellites (JSON):</label>
      <input type="file" id="satellitesJson" accept=".json">
    `;
    
    const resetButton = document.createElement('button');
    resetButton.id = 'resetSatellites';
    resetButton.textContent = 'Reset Satellites';
    
    controls.appendChild(fileInputDiv);
    controls.appendChild(resetButton);
    
    document.getElementById('satellitesJson')?.addEventListener('change', (event: Event) => {
      const input = event.target as HTMLInputElement;
      const file = input.files?.[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          try {
            const data = JSON.parse(e.target?.result as string);
            this.resetSatellites();
            this.loadSatellitesFromJSONData(data);
          } catch (error) {
            console.error('Error parsing JSON:', error);
            alert('Invalid JSON file');
          }
        };
        reader.readAsText(file);
      }
    });
    
    document.getElementById('resetSatellites')?.addEventListener('click', () => {
      this.resetSatellites();
    });
  }

  /**
    * Load satellites from JSON data
    * @method loadSatellitesFromJSONData
    * @param {Array} data - Array of satellite data
    */
  private loadSatellitesFromJSONData(data: any[]): void {
    if (!Array.isArray(data)) {
      console.error('Invalid data format: Expected an array');
      return;
    }
    
    data.forEach((satelliteData, index) => {
      this.createSatellite(satelliteData, index, data.length);
    });
  }

  /**
    * Reset the satellites in the scene
    * @method resetSatellites
    */
  private resetSatellites(): void {
    this.satellites.forEach(satellite => {
      this.scene.remove(satellite.group);
      if (satellite.orbitPath) {
        this.scene.remove(satellite.orbitPath);
      }
    });
    
    this.satellites = [];
    this.orbitPaths = [];
  }

  /**
    * Add event listeners for window resize
    * @method addEventListeners
    */
  private addEventListeners(): void {
    window.addEventListener('resize', () => {
      this.camera.aspect = window.innerWidth / window.innerHeight;
      this.camera.updateProjectionMatrix();
      this.renderer.setSize(window.innerWidth, window.innerHeight);
    });
  }

  /**
    * Animation loop for rendering the scene
    * @method animate
    */
  private animate(): void {
    requestAnimationFrame(() => this.animate());
    this.updateSatellites();
    
    if (this.clouds) {
      this.clouds.rotation.y += 0.00002;
    }
    
    this.controls.update();
    this.renderer.render(this.scene, this.camera);
  }
}
