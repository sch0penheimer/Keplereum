import React, { createContext, useContext, useState, ReactNode, useEffect } from 'react';
import * as THREE from 'three';
import { 
  calculateSatelliteOrbitalTrajectory,
  getCurrentSatelliteTelemetry
} from '@/utils/satelliteUtils';

import {
  earthRadius,
  scaleFactor
} from "@/utils/constants"

//** The telemetry interface **//
export interface SatelliteTelemetryData {
  property: string;
  value: string | number;
}

//** Orbital data interface **//
export interface OrbitalData {
  cartesianValues: number[];
  points3D: THREE.Vector3[];
  periodSeconds: number;
}

//** Satellite data interface **//
export interface SatelliteData {
  id: string;
  name: string;
  color: string;
  perigeeAltitude: number;
  eccentricity: number;
  inclination: number;
  longitudeOfAscendingNode: number;
  argumentOfPeriapsis: number;
  speedMultiplier: number;
  maxSensorBaseRadius: number;
  maxSensorHeight: number;
  tleEpoch: string;
  orbitalData?: {
    cartesianValues: number[];
    points3D: THREE.Vector3[];
    periodSeconds: number;
  };
  telemetry?: SatelliteTelemetryData[];
  passPredictions?: {
    id: number;
    rise: { date: string; time: string; az: string; deg: string };
    set: { date: string; time: string; az: string; deg: string };
    duration: string;
    visibility: string;
  }[];
  trackData?: {
    color: string;
    rotation: number;
    scale: { x: number; y: number };
  };
}

//** Context type interface **//
interface SatelliteContextType {
  satellites: SatelliteData[];
  setSatellites: React.Dispatch<React.SetStateAction<SatelliteData[]>>;
  selectedSatelliteId: string;
  setSelectedSatelliteId: (id: string) => void;
  selectedSatellite: SatelliteData | null;
  groundStations: { id: number; name: string; selected: boolean }[];
  setGroundStations: React.Dispatch<React.SetStateAction<{ id: number; name: string; selected: boolean }[]>>;
  loading: boolean;
}

//** maincontext **//
export const SatelliteContext = createContext<SatelliteContextType>({
  satellites: [],
  setSatellites: () => {},
  selectedSatelliteId: '',
  setSelectedSatelliteId: () => {},
  selectedSatellite: null,
  groundStations: [],
  setGroundStations: () => {},
  loading: true
});

export const useSatelliteContext = () => useContext(SatelliteContext);

//** Telemetry and OrbitalData placeholders data arrays (before initialization) **//
const SatelliteTelemetryDataPlaceholder: SatelliteTelemetryData[] = [
  { property: "TLE Age [days]", value: "" },
  { property: "Latitude [deg]", value: "" },
  { property: "Longitude [deg]", value: "" },
  { property: "Altitude [m]", value: "" },
  { property: "x [m]", value: "" },
  { property: "y [m]", value: "" },
  { property: "z [m]", value: "" },
  { property: "dx/dt [m/s]", value: "" },
  { property: "dy/dt [m/s]", value: "" },
  { property: "dz/dt [m/s]", value: "" },
  { property: "Semimajor axis (a) [m]", value: "" },
  { property: "Eccentricity (e)", value: "" },
  { property: "Inclination (i) [deg]", value: "" },
  { property: "RAAN [deg]", value: "" }
];

const OrbitalDataPlaceholder: OrbitalData = {
  cartesianValues: [],
  points3D: [],
  periodSeconds: NaN,
};

//** Initial satellite data (Dummy as in to be FETCHED) **//
const initialSatellitesData: SatelliteData[] = [
  {
    id: "sat-1",
    name: "AURA",
    color: "#00ffff",
    perigeeAltitude: 500,
    eccentricity: 0.1,
    inclination: 45,
    longitudeOfAscendingNode: 0,
    argumentOfPeriapsis: 0,
    speedMultiplier: 20,
    maxSensorBaseRadius: 0.9,
    maxSensorHeight: 2.3,
    orbitalData: OrbitalDataPlaceholder,
    telemetry: SatelliteTelemetryDataPlaceholder,
    tleEpoch: "2025-04-01T00:00:00Z",
    trackData: {
      color: "green",
      rotation: 30,
      scale: { x: 0.8, y: 1 },
    },
    passPredictions: [
      { id: 1, rise: { date: "04 Apr", time: "15:30", az: "E", deg: "47.5°" }, set: { date: "04 Apr", time: "15:45", az: "N", deg: "32.1°" }, duration: "407.553", visibility: "R" },
      { id: 2, rise: { date: "04 Apr", time: "17:12", az: "SSE", deg: "63.2°" }, set: { date: "04 Apr", time: "17:24", az: "NNW", deg: "54.7°" }, duration: "574.124", visibility: "R" },
      { id: 3, rise: { date: "04 Apr", time: "19:05", az: "NE", deg: "28.9°" }, set: { date: "04 Apr", time: "19:22", az: "NW", deg: "37.2°" }, duration: "420.155", visibility: "R" },
      { id: 4, rise: { date: "05 Apr", time: "05:30", az: "ENE", deg: "42.3°" }, set: { date: "05 Apr", time: "05:48", az: "ESE", deg: "39.6°" }, duration: "245.183", visibility: "V" },
      { id: 5, rise: { date: "05 Apr", time: "07:15", az: "NNE", deg: "57.1°" }, set: { date: "05 Apr", time: "07:35", az: "S", deg: "62.8°" }, duration: "569.649", visibility: "V" },
      { id: 6, rise: { date: "05 Apr", time: "09:00", az: "N", deg: "31.4°" }, set: { date: "05 Apr", time: "09:18", az: "WSW", deg: "27.9°" }, duration: "485.170", visibility: "V" },
    ]
  },
  {
    id: "sat-2",
    name: "AQUA",
    color: "#ff00ff",
    perigeeAltitude: 800,
    eccentricity: 0.7,
    inclination: 60,
    longitudeOfAscendingNode: 120,
    argumentOfPeriapsis: 45,
    speedMultiplier: 20,
    maxSensorBaseRadius: 0.9,
    maxSensorHeight: 3.5,
    orbitalData: OrbitalDataPlaceholder,
    telemetry: SatelliteTelemetryDataPlaceholder,
    tleEpoch: "2025-04-01T00:00:00Z",
    trackData: {
      color: "blue",
      rotation: -15,
      scale: { x: 1, y: 0.8 }
    },
    passPredictions: [
      { id: 1, rise: { date: "04 Apr", time: "16:45", az: "SE", deg: "52.7°" }, set: { date: "04 Apr", time: "17:02", az: "NE", deg: "45.3°" }, duration: "491.267", visibility: "R" },
      { id: 2, rise: { date: "04 Apr", time: "18:30", az: "SSW", deg: "38.9°" }, set: { date: "04 Apr", time: "18:47", az: "NNE", deg: "42.1°" }, duration: "518.934", visibility: "R" },
      { id: 3, rise: { date: "05 Apr", time: "06:15", az: "ENE", deg: "36.5°" }, set: { date: "05 Apr", time: "06:32", az: "ESE", deg: "41.8°" }, duration: "427.351", visibility: "V" },
      { id: 4, rise: { date: "05 Apr", time: "08:00", az: "NNW", deg: "59.3°" }, set: { date: "05 Apr", time: "08:20", az: "SSE", deg: "49.6°" }, duration: "583.772", visibility: "V" },
      { id: 5, rise: { date: "05 Apr", time: "09:45", az: "W", deg: "33.2°" }, set: { date: "05 Apr", time: "10:03", az: "E", deg: "29.7°" }, duration: "459.136", visibility: "V" },
    ]
  },
  {
    id: "sat-3",
    name: "CALIPSO",
    color: "#ffff00",
    perigeeAltitude: 1200,
    eccentricity: 0.15,
    inclination: 30,
    longitudeOfAscendingNode: 240,
    argumentOfPeriapsis: 90,
    speedMultiplier: 20,
    maxSensorBaseRadius: 0.9,
    maxSensorHeight: 2.3,
    orbitalData: OrbitalDataPlaceholder,
    telemetry: SatelliteTelemetryDataPlaceholder,
    tleEpoch: "2025-04-01T00:00:00Z",
    trackData: {
      color: "purple",
      rotation: 45,
      scale: { x: 0.9, y: 0.7 }
    },
    passPredictions: [
      { id: 1, rise: { date: "04 Apr", time: "14:20", az: "NE", deg: "41.3°" }, set: { date: "04 Apr", time: "14:38", az: "SE", deg: "37.8°" }, duration: "463.228", visibility: "R" },
      { id: 2, rise: { date: "04 Apr", time: "16:05", az: "N", deg: "54.9°" }, set: { date: "04 Apr", time: "16:25", az: "S", deg: "58.2°" }, duration: "595.467", visibility: "R" },
      { id: 3, rise: { date: "04 Apr", time: "17:50", az: "NW", deg: "27.6°" }, set: { date: "04 Apr", time: "18:08", az: "SW", deg: "31.9°" }, duration: "437.659", visibility: "R" },
      { id: 4, rise: { date: "05 Apr", time: "05:35", az: "E", deg: "44.2°" }, set: { date: "05 Apr", time: "05:55", az: "W", deg: "47.5°" }, duration: "541.893", visibility: "V" }
    ]
  },
  {
    id: "sat-4",
    name: "GCOM-W1 (SHIZUKU)",
    color: "#ff8c00",
    perigeeAltitude: 700,
    eccentricity: 0.05,
    inclination: 50,
    longitudeOfAscendingNode: 180,
    argumentOfPeriapsis: 270,
    speedMultiplier: 20,
    maxSensorBaseRadius: 0.9,
    maxSensorHeight: 2.3,
    orbitalData: OrbitalDataPlaceholder,
    telemetry: SatelliteTelemetryDataPlaceholder,
    tleEpoch: "2025-04-01T00:00:00Z",
    trackData: {
      color: "orange",
      rotation: -30,
      scale: { x: 0.85, y: 0.85 }
    },
    passPredictions: [
      { id: 1, rise: { date: "04 Apr", time: "14:50", az: "NE", deg: "38.4°" }, set: { date: "04 Apr", time: "15:07", az: "NW", deg: "42.7°" }, duration: "467.321", visibility: "R" },
      { id: 2, rise: { date: "04 Apr", time: "16:35", az: "E", deg: "53.8°" }, set: { date: "04 Apr", time: "16:55", az: "W", deg: "58.1°" }, duration: "583.754", visibility: "R" },
      { id: 3, rise: { date: "05 Apr", time: "04:20", az: "SE", deg: "32.2°" }, set: { date: "05 Apr", time: "04:37", az: "NE", deg: "36.5°" }, duration: "443.198", visibility: "V" },
      { id: 4, rise: { date: "05 Apr", time: "06:05", az: "S", deg: "48.9°" }, set: { date: "05 Apr", time: "06:25", az: "N", deg: "53.2°" }, duration: "563.587", visibility: "V" },
      { id: 5, rise: { date: "05 Apr", time: "07:50", az: "SW", deg: "29.7°" }, set: { date: "05 Apr", time: "08:07", az: "NW", deg: "34.0°" }, duration: "427.965", visibility: "V" },
    ]
  },
];

//** Initial ground stations (One as in the curretly logged IN) **//
const initialGroundStations = [
  { id: 1, name: "KLC", selected: true }
];

const loadInitialSatellites = async (): Promise<SatelliteData[]> => {
  const satellites = initialSatellitesData.map(satellite => {
    const result = calculateSatelliteOrbitalTrajectory(
      satellite.perigeeAltitude,
      satellite.eccentricity,
      satellite.inclination,
      satellite.longitudeOfAscendingNode,
      satellite.argumentOfPeriapsis,
      earthRadius,
      scaleFactor
    );

    const telemetry = getCurrentSatelliteTelemetry({
      ...satellite,
      orbitalData: {
        cartesianValues: result.cartesianValues,
        points3D: result.points3D,
        periodSeconds: result.periodSeconds,
      },
    });

    return {
      ...satellite,
      orbitalData: {
        cartesianValues: result.cartesianValues,
        points3D: result.points3D,
        periodSeconds: result.periodSeconds,
      },
      telemetry,
    };
  });

  return satellites;
};

//** Provider main functional component **//
export const SatelliteProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [satellites, setSatellites] = useState<SatelliteData[]>([]);
  const [selectedSatelliteId, setSelectedSatelliteId] = useState<string>("sat-1");
  const [groundStations, setGroundStations] = useState(initialGroundStations);

  const selectedSatellite = satellites.find(sat => sat.id === selectedSatelliteId) || null;
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const initializeSatellites = async () => {
      const initializedSatellites = await loadInitialSatellites();
      setSatellites(initializedSatellites);
      setLoading(false);
    };

    initializeSatellites();
  }, []);

  return (
    <SatelliteContext.Provider
      value={{
        satellites,
        setSatellites,
        selectedSatelliteId,
        setSelectedSatelliteId,
        selectedSatellite,
        groundStations,
        setGroundStations,
        loading,
      }}
    >
      {children}
    </SatelliteContext.Provider>
  );
};
