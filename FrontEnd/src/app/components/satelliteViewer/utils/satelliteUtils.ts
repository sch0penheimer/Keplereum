import * as THREE from 'three';
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader.js';

/**
    * Load the satellite model
    * @method loadSatelliteModel
    * @param {string} modelPath - Path to the GLTF model
    * @param {THREE.Mesh} placeholder - Placeholder mesh for the satellite
    * @param {THREE.Group} group - Group container for the satellite
    */
export function loadSatelliteModel( modelPath: string, placeholder: THREE.Object3D, group: THREE.Group ) : void {
  const gltfLoader = new GLTFLoader();
  gltfLoader.load(
    modelPath,
    (gltf) => {
      group.remove(placeholder);
      const satelliteModel = gltf.scene;
      satelliteModel.scale.set(0.1, 0.1, 0.1);
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
    * Calculate the satellite's orbital trajectory
    * @method calculateSatelliteOrbitalTrajectory
    * @param {number} perigeeAltitude - Perigee altitude in kilometers
    * @param {number} eccentricity - Eccentricity of the orbit
    * @param {number} inclination - Inclination in degrees
    * @param {number} longitudeOfAscendingNode - Longitude of ascending node in degrees
    * @param {number} argumentOfPeriapsis - Argument of periapsis in degrees
    * @returns {Object} - Contains cartesian values, points3D, and periodSeconds
    */
export function calculateSatelliteOrbitalTrajectory(
    perigeeAltitude: number,
    eccentricity: number,
    inclination: number,
    longitudeOfAscendingNode: number,
    argumentOfPeriapsis: number,
    earthRadius: number,
    scaleFactor:number
  ): { cartesianValues: number[], points3D: THREE.Vector3[], periodSeconds: number } {
    const perigeeRadius = earthRadius + perigeeAltitude;
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
      
      const xScene = xFinal * scaleFactor / 1000;
      const yScene = yFinal * scaleFactor / 1000;
      const zScene = z * scaleFactor / 1000;
      
      cartesianValues.push(timeSeconds, xScene, yScene, zScene);
      points3D.push(new THREE.Vector3(xScene, yScene, zScene));
    }
    
    return { cartesianValues, points3D, periodSeconds };
  }

  /**
      * Create the satellite's orbit path
      * @method createSatelliteOrbitPath
      * @param {Object} satellite - Satellite object
      */
    export function createSatelliteOrbitPath(satellite: any,scene:THREE.Scene,orbitPaths:any): void {
      const geometry = new THREE.BufferGeometry().setFromPoints(satellite.animationParams.orbitPoints);
      const material = new THREE.LineBasicMaterial({
        color: satellite.color,
        transparent: true,
        opacity: 0.8
      });
      
      const orbitPath = new THREE.Line(geometry, material);
      scene.add(orbitPath);
      satellite.orbitPath = orbitPath;
      orbitPaths.push(orbitPath);
    }

/**
    * Update the satellite's position based on its orbital parameters
    * @method updateSatellitePosition
    * @param {Object} satellite - Satellite object
    */
  export function updateSatellitePosition(satellite: any): void {
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