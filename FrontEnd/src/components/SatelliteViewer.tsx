
'use client';

import { useEffect, useRef, useState } from 'react';
import * as THREE from 'three';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';
import FromLatLongToXYZ from '../utils/FromLatLongToXYZ';
import { useSatelliteContext } from '@/contexts/SatelliteContext';
import {
  loadSatelliteModel,
  calculateSatelliteOrbitalTrajectory,
  createSatelliteOrbitPath,
  updateSatellitePosition
} from '../utils/satelliteUtils';

interface SatelliteObject {
  id: string;
  name: string;
  group: THREE.Group;
  orbitParams: {
    perigeeAltitude: number;
    eccentricity: number;
    inclination: number;
    longitudeOfAscendingNode: number;
    argumentOfPeriapsis: number;
    speedMultiplier: number;
    maxSensorBaseRadius: number;
    maxSensorHeight: number;
  };
  animationParams: {
    currentTime: number;
    periodSeconds: number;
    orbitPoints: THREE.Vector3[];
  };
  color: number;
  highlight: THREE.Mesh | null;
  sensorCone: THREE.Mesh | null;
  label: THREE.Sprite | null;
  labelBaseScale: { x: number; y: number; z: number };
}

export default function SatelliteViewer() {
  const containerRef = useRef<HTMLDivElement>(null);
  const [loading, setLoading] = useState(true);
  const { satellites, selectedSatelliteId, setSelectedSatelliteId } = useSatelliteContext();
  const [cameraTrackingEnabled, setCameraTrackingEnabled] = useState(true);
  const isTrackingSatellite = useRef(false);
  const previousSelectedId = useRef<string | null>(null);
  const initialCameraPosition = useRef<THREE.Vector3 | null>(null);
  const initialControlsTarget = useRef<THREE.Vector3 | null>(null);
  const cameraTransitionInProgress = useRef(false);

  //** Three.js objects **//
  const scene = useRef(new THREE.Scene());
  const camera = useRef(new THREE.PerspectiveCamera(75, typeof window !== 'undefined' ? window.innerWidth / window.innerHeight : 1, 0.1, 1000));
  const renderer = useRef<THREE.WebGLRenderer>();
  const textureLoader = useRef(new THREE.TextureLoader());

  const animateFunctions = useRef<(() => void)[]>([]);
  const controls = useRef<OrbitControls>();

  
  //* Earth related objects *//
  const earth = useRef<THREE.Mesh>();
  const clouds = useRef<THREE.Mesh>();
  const stars = useRef<THREE.Points>();
  const satelliteObjects = useRef<SatelliteObject[]>([]);
  const orbitPaths = useRef<THREE.Line[]>([]);
  

  //* Constants *//
  const earthRadius = 6371;
  const earthRadiusSceneUnits = 4;
  const scaleFactor = earthRadiusSceneUnits / earthRadius;
  const satelliteModelPath = 'main-sat.glb';

  //* Reset camera to initial position *//
  const resetCamera = () => {
    if (!camera.current || !controls.current || !initialCameraPosition.current || !initialControlsTarget.current) return;
    
    //* Block new transition if one is already in progress *//
    if (cameraTransitionInProgress.current) return;
    
    cameraTransitionInProgress.current = true;
    
    //* Smoothly transition back to the initial position *//
    const startPosition = camera.current.position.clone();
    const startTarget = controls.current.target.clone();
    const endPosition = initialCameraPosition.current.clone();
    const endTarget = initialControlsTarget.current.clone();
    
    const duration = 1000;
    const startTime = Date.now();
    
    const animate = () => {
      const elapsed = Date.now() - startTime;
      const t = Math.min(elapsed / duration, 1);
      const easeT = t < 0.5 ? 2 * t * t : -1 + (4 - 2 * t) * t; //* Ease in-out quadratic *//
      
      camera.current!.position.lerpVectors(startPosition, endPosition, easeT);
      controls.current!.target.lerpVectors(startTarget, endTarget, easeT);
      controls.current!.update();
      
      if (t < 1) {
        requestAnimationFrame(animate);
      } else {
        isTrackingSatellite.current = false;
        cameraTransitionInProgress.current = false;
      }
    };
    
    animate();
  };

  //* Scene Initialize *//
  useEffect(() => {
    if (!containerRef.current) return;
  
    initThreeJS();
    setupScene();
    loadInitialSatellites();
  
    const handleResize = () => {
      if (!camera.current || !renderer.current || !containerRef.current) return;
  
      const { clientWidth, clientHeight } = containerRef.current;
      camera.current.aspect = clientWidth / clientHeight;
      camera.current.updateProjectionMatrix();
      renderer.current.setSize(clientWidth, clientHeight);
    };
  
    handleResize();
    window.addEventListener('resize', handleResize);
  
    return () => {
      window.removeEventListener('resize', handleResize);
      if (renderer.current?.domElement.parentNode) {
        renderer.current.domElement.parentNode.removeChild(renderer.current.domElement);
      }
    };
  }, []);

  //* Update selected satellite when selected in context *//
  useEffect(() => {
    if (selectedSatelliteId === null) {
      //* No satellite selected -> reset camera *//
      resetCamera();
      return;
    }

    if (previousSelectedId.current !== selectedSatelliteId) {
      previousSelectedId.current = selectedSatelliteId;
      
      const selectedObject = satelliteObjects.current.find(sat => sat.id === selectedSatelliteId);
      
      //* Highlight the selected satellite *//
      satelliteObjects.current.forEach(sat => {
        if (sat.highlight) {
          if (sat.id === selectedSatelliteId) {
            sat.highlight.scale.set(2, 2, 2);
          } else {
            sat.highlight.scale.set(1, 1, 1);
          }
        }
      });

      //* Focus camera on the selected satellite *//
      if (selectedObject && camera.current && controls.current && cameraTrackingEnabled) {
        if (!initialCameraPosition.current) {
          initialCameraPosition.current = camera.current.position.clone();
          initialControlsTarget.current = controls.current.target.clone();
        }
        
        if (cameraTransitionInProgress.current) return;
        
        cameraTransitionInProgress.current = true;
        
        //**** Start tracking the selected satellite ****//
        isTrackingSatellite.current = true;
        
        const satellitePosition = selectedObject.group.position.clone();
        const distanceVector = new THREE.Vector3(0, 0, 1);
        
        //* Position camera to look at satellite from a distance *//
        const cameraTarget = satellitePosition.clone();
        
        //* Move the camera to the new position with smooth transition *//
        const startPosition = camera.current.position.clone();
        const endPosition = satellitePosition.clone().add(distanceVector);
        const startTarget = controls.current.target.clone();
        const endTarget = cameraTarget;
        
        const duration = 1000; // ms
        const startTime = Date.now();
        
        const animate = () => {
          const elapsed = Date.now() - startTime;
          const t = Math.min(elapsed / duration, 1);
          const easeT = t < 0.5 ? 2 * t * t : -1 + (4 - 2 * t) * t;
          
          camera.current!.position.lerpVectors(startPosition, endPosition, easeT);
          controls.current!.target.lerpVectors(startTarget, endTarget, easeT);
          controls.current!.update();
          
          if (t < 1) {
            requestAnimationFrame(animate);
          } else {
            cameraTransitionInProgress.current = false;
          }
        };
        
        animate();
      }
    }
  }, [selectedSatelliteId, cameraTrackingEnabled]);

  const initThreeJS = () => {
    if (!containerRef.current) return;

    
    //* Initialize renderer and camera *//
    renderer.current = new THREE.WebGLRenderer({ antialias: true });
    renderer.current.setSize(containerRef.current.clientWidth, containerRef.current.clientHeight);
    renderer.current.setPixelRatio(window.devicePixelRatio);
    renderer.current.shadowMap.enabled = true;
    renderer.current.toneMapping = THREE.ACESFilmicToneMapping;
    renderer.current.toneMappingExposure = 1.5;
    containerRef.current.appendChild(renderer.current.domElement);

    camera.current.position.set(0, 0, 10);
    camera.current.lookAt(0, 0, 0);

    //* Initialize controls *//
    controls.current = new OrbitControls(camera.current, renderer.current.domElement);
    controls.current.enableDamping = true;
    controls.current.dampingFactor = 0.1;
    controls.current.zoomSpeed = 0.4;
    controls.current.rotateSpeed = 0.8;
    controls.current.minDistance = 5;
    controls.current.maxDistance = 30;
    controls.current.target.set(0, 0, 0);
    controls.current.update();

    //* Store initial camera position and target for reset *//
    initialCameraPosition.current = camera.current.position.clone();
    initialControlsTarget.current = controls.current.target.clone();
  };

  const setupScene = () => {
    const ambientLight = new THREE.AmbientLight(0xffffff, 1);
    scene.current.add(ambientLight);
    createEarth();
    createStars();
    createTornado(33.984376, -6.867138);
    animate();
  };

  const createEarth = () => {
    const earthGeometry = new THREE.SphereGeometry(earthRadiusSceneUnits, 64, 64);
  
    //* Earth textures *//
    const earthMap = textureLoader.current.load('https://cdn.jsdelivr.net/gh/mrdoob/three.js@dev/examples/textures/planets/earth_atmos_2048.jpg');
    const earthBumpMap = textureLoader.current.load('https://cdn.jsdelivr.net/gh/mrdoob/three.js@dev/examples/textures/planets/earth_normal_2048.jpg');
    const earthSpecMap = textureLoader.current.load('https://cdn.jsdelivr.net/gh/mrdoob/three.js@dev/examples/textures/planets/earth_specular_2048.jpg');
    
    const earthMaterial = new THREE.MeshPhongMaterial({
      map: earthMap,
      bumpMap: earthBumpMap,
      bumpScale: 0.1,
      specularMap: earthSpecMap,
      specular: new THREE.Color('white'),
      shininess: 15
    });
  
    earth.current = new THREE.Mesh(earthGeometry, earthMaterial);
    earth.current.receiveShadow = true;
    scene.current.add(earth.current);
  
    //* Cloud layer *//
    const cloudsGeometry = new THREE.SphereGeometry(earthRadiusSceneUnits + 0.03, 64, 64);
    const cloudsMaterial = new THREE.MeshPhongMaterial({
      map: textureLoader.current.load('https://cdn.jsdelivr.net/gh/mrdoob/three.js@dev/examples/textures/planets/earth_clouds_1024.png'),
      transparent: true,
      opacity: 0.8
    });
  
    clouds.current = new THREE.Mesh(cloudsGeometry, cloudsMaterial);
    scene.current.add(clouds.current);
  };

  const createStars = () => {
    const starsGeometry = new THREE.BufferGeometry();
    const starsMaterial = new THREE.PointsMaterial({
      color: 0xffffff,
      size: 0.3,
      transparent: true
    });
  
    const starsVertices: number[] = [];
    for (let i = 0; i < 15000; i++) {
      const x = THREE.MathUtils.randFloatSpread(2000);
      const y = THREE.MathUtils.randFloatSpread(2000);
      const z = THREE.MathUtils.randFloatSpread(2000);
      starsVertices.push(x, y, z);
    }
  
    starsGeometry.setAttribute('position', new THREE.Float32BufferAttribute(starsVertices, 3));
    stars.current = new THREE.Points(starsGeometry, starsMaterial);
    scene.current.add(stars.current);
  };

  const loadInitialSatellites = () => {
    satellites.forEach((data, index) => {
      createSatellite(data, index, satellites.length);
    });
    setLoading(false);
  };
  
  const createSatellite = (data: any, index: number, total: number) => {
    const satellite: SatelliteObject = {
      id: data.id,
      name: data.name,
      group: new THREE.Group(),
      orbitParams: {
        perigeeAltitude: data.perigeeAltitude,
        eccentricity: data.eccentricity,
        inclination: data.inclination,
        longitudeOfAscendingNode: data.longitudeOfAscendingNode,
        argumentOfPeriapsis: data.argumentOfPeriapsis,
        speedMultiplier: data.speedMultiplier,
        maxSensorBaseRadius: data.maxSensorBaseRadius || 0.9,
        maxSensorHeight: data.maxSensorHeight || 2.3
      },
      animationParams: {
        currentTime: 0,
        periodSeconds: 0,
        orbitPoints: []
      },
      color: parseInt(data.color.replace('#', '0x')),
      highlight: null,
      sensorCone: null,
      label: null,
      labelBaseScale: { x: 2, y: 2, z: 2 }
    };
  
    scene.current.add(satellite.group);
  
    //* Satellite placeholder *//
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
  
    //* Highlight dot *//
    const highlightGeometry = new THREE.SphereGeometry(0.03, 16, 16);
    const highlightMaterial = new THREE.MeshBasicMaterial({ 
      color: satellite.color,
      transparent: true,
      opacity: 0.8
    });
    satellite.highlight = new THREE.Mesh(highlightGeometry, highlightMaterial);
    satellite.group.add(satellite.highlight);
  
    //* Sensor cone, visible for all satellites *//
    const coneGeometry = new THREE.ConeGeometry(
      satellite.orbitParams.maxSensorBaseRadius || 0.9, 
      1, 
      64
    ); 
    const coneMaterial = new THREE.MeshBasicMaterial({ 
      color: satellite.color,
      transparent: true,
      opacity: 0.3
    });
    satellite.sensorCone = new THREE.Mesh(coneGeometry, coneMaterial);
    satellite.sensorCone.position.set(0, -0.05, 0);
    satellite.sensorCone.lookAt(0, 0, 0);
    satellite.sensorCone.visible = true;
    satellite.group.add(satellite.sensorCone);
  
    //* Label *//
    const canvas = document.createElement('canvas');
    canvas.width = 300;
    canvas.height = 128;
    const context = canvas.getContext('2d');
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
    satellite.labelBaseScale = { x: 2, y: 0.8, z: 1 };
    satellite.group.add(satellite.label);
  
    //* Orbit Calculation *//
    const result = calculateSatelliteOrbitalTrajectory(
      satellite.orbitParams.perigeeAltitude,
      satellite.orbitParams.eccentricity,
      satellite.orbitParams.inclination,
      satellite.orbitParams.longitudeOfAscendingNode,
      satellite.orbitParams.argumentOfPeriapsis,
      earthRadius,
      scaleFactor
    );
    
    satellite.animationParams.periodSeconds = result.periodSeconds;
    satellite.animationParams.orbitPoints = result.points3D;
  
    //* Create orbit path *//
    createSatelliteOrbitPath(satellite, scene.current, orbitPaths.current);
  
    //* Load 3D model *//
    const modelPath = data.model || satelliteModelPath;
    loadSatelliteModel(
      modelPath,
      satellitePlaceholder,
      satellite.group
    );
  
    satelliteObjects.current.push(satellite);
    updateSensorCone(satellite);
  };
  
  const animate = () => {
    if (!renderer.current || !camera.current) return;
  
    requestAnimationFrame(animate);
    updateSatellites();
    
    if (clouds.current) {
      clouds.current.rotation.y += 0.0001;
    }
  
    animateFunctions.current.forEach(fn => fn());
    
    if (controls.current) {
      controls.current.update();
    }
    
    //* If tracking a satellite, update camera position to follow it *//
    if (isTrackingSatellite.current && cameraTrackingEnabled && selectedSatelliteId && !cameraTransitionInProgress.current) {
      const selectedObject = satelliteObjects.current.find(sat => sat.id === selectedSatelliteId);
      if (selectedObject && controls.current) {
        controls.current.target.copy(selectedObject.group.position);
      }
    }
  
    renderer.current.render(scene.current, camera.current);
  };
  
  const updateSatellites = () => {
    satelliteObjects.current.forEach(satellite => {
      satellite.animationParams.currentTime += 0.016 * satellite.orbitParams.speedMultiplier;
      updateSatellitePosition(satellite);
      updateSatelliteOrientation(satellite);
      updateSensorCone(satellite);
      updateSatelliteLabel(satellite);
    });
  };

  const createTornado = (latitude: number, longitude: number) => {
    const [positionX, positionY, positionZ] = FromLatLongToXYZ(latitude, longitude);
    
    const height = 3;
    const radius = 1.4;
    const segments = 60;
    const tornadoGroup = new THREE.Group();
    tornadoGroup.position.set(positionX, positionY, positionZ);
  
    const geometry = new THREE.CylinderGeometry(radius, radius * 0.2, height, segments, segments, true);
    const pos = geometry.attributes['position'];
    const v3 = new THREE.Vector3();
    for (let i = 0; i < pos.count; i++) {
      v3.fromBufferAttribute(pos, i);
      const twistAmount = Math.sin(v3.y * 2) * 0.2;
      v3.x += twistAmount * Math.cos(v3.y * 5);
      v3.z += twistAmount * Math.sin(v3.y * 5);
      pos.setXYZ(i, v3.x, v3.y, v3.z);
    }
    
    const tornadoMaterial = new THREE.MeshBasicMaterial({
      color: 0xff8b4d,
      transparent: true,
      opacity: 0.7,
      side: THREE.DoubleSide
    });
    
    const tornado = new THREE.Mesh(geometry, tornadoMaterial);
    tornado.position.y = height / 2;
    tornadoGroup.add(tornado);
    
    //* Base plane effect *//
    const baseGeometry = new THREE.CircleGeometry(radius * 1.5, 32);
    const baseMaterial = new THREE.MeshBasicMaterial({
      color: 0xff8b4d,
      transparent: true,
      opacity: 0.3,
      side: THREE.DoubleSide
    });
    
    const base = new THREE.Mesh(baseGeometry, baseMaterial);
    base.rotation.x = -Math.PI / 2;
    base.position.y = 0;  
    tornadoGroup.add(base);
    
    tornadoGroup.lookAt(0, 0, 0);
    tornadoGroup.scale.set(0.1, -0.1, 0.1);
    tornadoGroup.rotateX(Math.PI/2);
    scene.current.add(tornadoGroup);
    
    //* Add animation *//
    animateFunctions.current.push(() => {
      const rotationSpeed = 0.08;
      tornado.rotation.y += rotationSpeed;
      const pulse = Math.sin(Date.now() * 0.001) * 0.05 + 1;
      tornado.scale.set(pulse, pulse, pulse);
    });
  };
  
  const updateSatelliteOrientation = (satellite: SatelliteObject) => {
    if (!satellite.group || !satellite.group.position) return;
    satellite.group.lookAt(0, 0, 0);
  };
  
  const updateSensorCone = (satellite: SatelliteObject) => {
    if (!satellite.sensorCone) return;
  
    const satellitePosition = satellite.group.position;
    const altitude = satellitePosition.length() - earthRadiusSceneUnits;
    const baseRadius = (altitude / (satellite.orbitParams.maxSensorHeight || 2.3)) * 
                      (satellite.orbitParams.maxSensorBaseRadius || 0.9);
    const sensorHeight = altitude;
  
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
  };
  
  const updateSatelliteLabel = (satellite: SatelliteObject) => {
    if (!satellite.label || !satellite.labelBaseScale || !camera.current) return;
    
    const distanceToCamera = camera.current.position.distanceTo(satellite.group.position);
    const scaleFactor = distanceToCamera / 15;
    
    satellite.label.scale.set(
      satellite.labelBaseScale.x * scaleFactor,
      satellite.labelBaseScale.y * scaleFactor,
      satellite.labelBaseScale.z
    );
    
    satellite.label.position.set(0.5, 0.2, 0);
  };
  
  return (
    <div className="relative w-full h-full">
      <div ref={containerRef} className="absolute inset-0" />
      {loading && (
        <div className="absolute inset-0 flex items-center justify-center bg-black/50">
          <p className="text-white">Loading satellite viewer...</p>
        </div>
      )}
      <div className="absolute bottom-4 right-4 bg-black/70 text-white text-xs p-1 rounded">
        <button
          onClick={() => {
            setCameraTrackingEnabled(!cameraTrackingEnabled);
            if (!cameraTrackingEnabled && selectedSatelliteId) {
              //* If turning tracking back on, focus on the selected satellite *//
              isTrackingSatellite.current = true;
            } else if (cameraTrackingEnabled) {
              //* If turning tracking off, reset camera to Earth view *//
              resetCamera();
            }
          }}
          className={`px-2 py-1 rounded ${cameraTrackingEnabled ? 'bg-satellite-accent/70' : 'bg-gray-700/70'}`}
        >
          {cameraTrackingEnabled ? 'Tracking On' : 'Tracking Off'}
        </button>
      </div>
    </div>
  );
}
