import { Component, OnInit, AfterViewInit } from '@angular/core';
import * as THREE from 'three';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader.js';

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
  private gltfLoader = new GLTFLoader();

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
    //** Load satellites from JSON **//
    this.loadSatellitesFromJSON();
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
  private loadSatellitesFromJSON(): void {
    //** Dummy satellite JSON array **//
    const satellitesData = [
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

    //** Process each satellite in the data array **//
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
    this.createSatelliteOrbitPath(satellite);

    //** 3D model loading **//
    const modelPath = data.model || this.satelliteModelPath;
    this.loadSatelliteModel(
        modelPath,
        satellitePlaceholder,
        satellite.group
    );

    //** Satellite -> satellites array **//
    this.satellites.push(satellite);

    //** Initialize the cone's height and orientation **//
    this.updateSensorCone(satellite);
  }

  /**
    * Load the satellite model
    * @method loadSatelliteModel
    * @param {string} modelPath - Path to the GLTF model
    * @param {THREE.Mesh} placeholder - Placeholder mesh for the satellite
    * @param {THREE.Group} group - Group container for the satellite
    */
  private loadSatelliteModel( modelPath: string, placeholder: THREE.Object3D, group: THREE.Group ) : void {
    this.gltfLoader.load(
      modelPath,
      (gltf) => {
        group.remove(placeholder);

        const satelliteModel = gltf.scene;
        satelliteModel.scale.set(0.005, 0.005, 0.005);
        satelliteModel.traverse((child: any) => {
          if (child.isMesh) {
            child.castShadow = true;
            if (child.material) {
              child.material.emissive = new THREE.Color(0x555555);
            }
          }
        });

        group.add(satelliteModel);
      },
      
      (error) => {
        console.error('Error loading satellite model:', error);
      }
    );
  }

  /**
    * Calculate the satellite's orbit parameters
    * @method calculateSatelliteOrbit
    * @param {Object} satellite - Satellite object
    */
  private calculateSatelliteOrbit(satellite: any): void {
    const result = this.calculateSatelliteOrbitalTrajectory(
      satellite.orbitParams.perigeeAltitude,
      satellite.orbitParams.eccentricity,
      satellite.orbitParams.inclination,
      satellite.orbitParams.longitudeOfAscendingNode,
      satellite.orbitParams.argumentOfPeriapsis
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
    * Calculate the satellite's orbital trajectory
    * @method calculateSatelliteOrbitalTrajectory
    * @param {number} perigeeAltitude - Perigee altitude in kilometers
    * @param {number} eccentricity - Eccentricity of the orbit
    * @param {number} inclination - Inclination in degrees
    * @param {number} longitudeOfAscendingNode - Longitude of ascending node in degrees
    * @param {number} argumentOfPeriapsis - Argument of periapsis in degrees
    * @returns {Object} - Contains cartesian values, points3D, and periodSeconds
    */
  private calculateSatelliteOrbitalTrajectory(
    perigeeAltitude: number,
    eccentricity: number,
    inclination: number,
    longitudeOfAscendingNode: number,
    argumentOfPeriapsis: number
  ): { cartesianValues: number[], points3D: THREE.Vector3[], periodSeconds: number } {
    const perigeeRadius = this.earthRadius + perigeeAltitude;
    const semiMajorAxis = perigeeRadius / (1 - eccentricity);
    const semiMajorAxisMeters = semiMajorAxis * 1000;
    const GM = 3.986004418e14;
    const periodSeconds = 2 * Math.PI * Math.sqrt(Math.pow(semiMajorAxisMeters, 3) / GM);
    
    const numSamples = 500;
    const timeStepSeconds = periodSeconds / numSamples;
    
    const cartesianValues: number[] = [];
    const points3D: THREE.Vector3[] = [];
    
    // ...rest of the trajectory calculation logic remains the same...
    for (let i = 0; i <= numSamples; i++) {
      const timeSeconds = i * timeStepSeconds;
      const meanAnomaly = (i / numSamples) * 2 * Math.PI;
      
      let eccentricAnomaly = meanAnomaly;
      let delta = 1e-6;
      for (let j = 0; j < 50; j++) {
        let newEccentricAnomaly = meanAnomaly + eccentricity * Math.sin(eccentricAnomaly);
        if (Math.abs(newEccentricAnomaly - eccentricAnomaly) < delta) break;
        eccentricAnomaly = newEccentricAnomaly;
      }
      
      const trueAnomaly = 2 * Math.atan2(
        Math.sqrt(1 + eccentricity) * Math.sin(eccentricAnomaly / 2),
        Math.sqrt(1 - eccentricity) * Math.cos(eccentricAnomaly / 2)
      );
      const radius = semiMajorAxisMeters * (1 - eccentricity * Math.cos(eccentricAnomaly));
      
      const x = radius * Math.cos(trueAnomaly);
      const y = radius * Math.sin(trueAnomaly);
      
      const inclinationRad = inclination * Math.PI / 180;
      const lonAscNodeRad = longitudeOfAscendingNode * Math.PI / 180;
      const argPeriapsisRad = argumentOfPeriapsis * Math.PI / 180;
      
      let xTemp = x * Math.cos(argPeriapsisRad) - y * Math.sin(argPeriapsisRad);
      let yTemp = x * Math.sin(argPeriapsisRad) + y * Math.cos(argPeriapsisRad);
      
      let xNew = xTemp;
      let yNew = yTemp * Math.cos(inclinationRad);
      let z = yTemp * Math.sin(inclinationRad);
      
      let xFinal = xNew * Math.cos(lonAscNodeRad) - yNew * Math.sin(lonAscNodeRad);
      let yFinal = xNew * Math.sin(lonAscNodeRad) + yNew * Math.cos(lonAscNodeRad);
      
      const xScene = xFinal * this.scaleFactor / 1000;
      const yScene = yFinal * this.scaleFactor / 1000;
      const zScene = z * this.scaleFactor / 1000;
      
      cartesianValues.push(timeSeconds, xScene, yScene, zScene);
      points3D.push(new THREE.Vector3(xScene, yScene, zScene));
    }
    
    return { cartesianValues, points3D, periodSeconds };
  }

  private updateSatellites(): void {
    this.satellites.forEach(satellite => {
      satellite.animationParams.currentTime += 0.016 * satellite.orbitParams.speedMultiplier;
      this.updateSatellitePosition(satellite);
      this.updateSatelliteOrientation(satellite);
      this.updateSensorCone(satellite);
      this.updateSatelliteLabel(satellite);
    });
  }

  /**
    * Update the satellite's position based on its orbital parameters
    * @method updateSatellitePosition
    * @param {Object} satellite - Satellite object
    */
  private updateSatellitePosition(satellite: any): void {
    if (!satellite.animationParams.orbitPoints.length) return;
    
    const points = satellite.animationParams.orbitPoints;
    const totalPoints = points.length;
    const normalizedTime = (satellite.animationParams.currentTime % satellite.animationParams.periodSeconds) / satellite.animationParams.periodSeconds;
    const index = Math.floor(normalizedTime * (totalPoints - 1));
    const nextIndex = (index + 1) % totalPoints;
    
    const fraction = (normalizedTime * (totalPoints - 1)) - index;
    const position = new THREE.Vector3();
    position.lerpVectors(points[index], points[nextIndex], fraction);
    
    satellite.group.position.copy(position);
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
