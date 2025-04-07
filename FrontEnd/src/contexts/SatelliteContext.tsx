
import React, { createContext, useContext, useState, ReactNode } from 'react';

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
  telemetry?: {
    [key: string]: string | number;
  }[];
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
  glbPath?:string;
}

interface SatelliteContextType {
  satellites: SatelliteData[];
  selectedSatelliteId: string;
  setSelectedSatelliteId: (id: string) => void;
  selectedSatellite: SatelliteData | null;
  groundStations: { id: number; name: string; selected: boolean }[];
  setGroundStations: React.Dispatch<React.SetStateAction<{ id: number; name: string; selected: boolean }[]>>;
}

export const SatelliteContext = createContext<SatelliteContextType>({
  satellites: [],
  selectedSatelliteId: '',
  setSelectedSatelliteId: () => {},
  selectedSatellite: null,
  groundStations: [],
  setGroundStations: () => {},
});

export const useSatelliteContext = () => useContext(SatelliteContext);

// Initial satellite data with all the information needed by components
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
    telemetry: [
      { property: "TLE Age [days]", value: "0.00783712139845" },
      { property: "Latitude [deg]", value: "-11.296602968423622" },
      { property: "Longitude [deg]", value: "81.50832996989672" },
      { property: "Altitude [m]", value: "707755.7803755393" },
      { property: "Position: J2000", value: "" },
      { property: "x [m]", value: "6873754.353686401" },
      { property: "y [m]", value: "1010064.48018664" },
      { property: "z [m]", value: "-1389098.6635943877" },
      { property: "Velocity: J2000", value: "" },
      { property: "dx/dt [m/s]", value: "-1274.1465604729071" },
      { property: "dy/dt [m/s]", value: "-1287.115418884501" },
      { property: "dz/dt [m/s]", value: "-7279.2769434856" },
      { property: "Keplarian Elem. (Osc / J...)", value: "" },
      { property: "Semimajor axis (a) [m]", value: "7086190.836970952" },
      { property: "Eccentricity (e)", value: "0.00101686175972369" },
      { property: "Inclination (i) [deg]", value: "98.17833532275806" },
      { property: "RAAN [deg]", value: "-169.99389095640146" },
    ],
    trackData: {
      color: "green",
      rotation: 30,
      scale: { x: 0.8, y: 1 }
    },
    passPredictions: [
      { id: 1, rise: { date: "04 Apr", time: "15:30", az: "E", deg: "47.5°" }, set: { date: "04 Apr", time: "15:45", az: "N", deg: "32.1°" }, duration: "407.553", visibility: "R" },
      { id: 2, rise: { date: "04 Apr", time: "17:12", az: "SSE", deg: "63.2°" }, set: { date: "04 Apr", time: "17:24", az: "NNW", deg: "54.7°" }, duration: "574.124", visibility: "R" },
      { id: 3, rise: { date: "04 Apr", time: "19:05", az: "NE", deg: "28.9°" }, set: { date: "04 Apr", time: "19:22", az: "NW", deg: "37.2°" }, duration: "420.155", visibility: "R" },
      { id: 4, rise: { date: "05 Apr", time: "05:30", az: "ENE", deg: "42.3°" }, set: { date: "05 Apr", time: "05:48", az: "ESE", deg: "39.6°" }, duration: "245.183", visibility: "V" },
      { id: 5, rise: { date: "05 Apr", time: "07:15", az: "NNE", deg: "57.1°" }, set: { date: "05 Apr", time: "07:35", az: "S", deg: "62.8°" }, duration: "569.649", visibility: "V" },
      { id: 6, rise: { date: "05 Apr", time: "09:00", az: "N", deg: "31.4°" }, set: { date: "05 Apr", time: "09:18", az: "WSW", deg: "27.9°" }, duration: "485.170", visibility: "V" },
    ],
    glbPath:"main-sat.glb"
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
    telemetry: [
      { property: "TLE Age [days]", value: "0.00654328574231" },
      { property: "Latitude [deg]", value: "-9.187354298743521" },
      { property: "Longitude [deg]", value: "85.32784326784532" },
      { property: "Altitude [m]", value: "705325.4587235623" },
      { property: "Position: J2000", value: "" },
      { property: "x [m]", value: "6823124.234769401" },
      { property: "y [m]", value: "1104364.98714664" },
      { property: "z [m]", value: "-1289645.9475943877" },
      { property: "Velocity: J2000", value: "" },
      { property: "dx/dt [m/s]", value: "-1298.7645604729071" },
      { property: "dy/dt [m/s]", value: "-1302.385418884501" },
      { property: "dz/dt [m/s]", value: "-7298.2865434856" },
      { property: "Keplarian Elem. (Osc / J...)", value: "" },
      { property: "Semimajor axis (a) [m]", value: "7082165.834770952" },
      { property: "Eccentricity (e)", value: "0.00098746175972369" },
      { property: "Inclination (i) [deg]", value: "98.21763532275806" },
      { property: "RAAN [deg]", value: "-170.12389095640146" },
    ],
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
    telemetry: [
      { property: "TLE Age [days]", value: "0.00894726139845" },
      { property: "Latitude [deg]", value: "-12.156602968423622" },
      { property: "Longitude [deg]", value: "79.87832996989672" },
      { property: "Altitude [m]", value: "703755.7803755393" },
      { property: "Position: J2000", value: "" },
      { property: "x [m]", value: "6873754.353686401" },
      { property: "y [m]", value: "1010064.48018664" },
      { property: "z [m]", value: "-1389098.6635943877" },
      { property: "Velocity: J2000", value: "" },
      { property: "dx/dt [m/s]", value: "-1289.1465604729071" },
      { property: "dy/dt [m/s]", value: "-1276.115418884501" },
      { property: "dz/dt [m/s]", value: "-7274.2769434856" },
      { property: "Keplarian Elem. (Osc / J...)", value: "" },
      { property: "Semimajor axis (a) [m]", value: "7056190.836970952" },
      { property: "Eccentricity (e)", value: "0.00112686175972369" },
      { property: "Inclination (i) [deg]", value: "97.98833532275806" },
      { property: "RAAN [deg]", value: "-168.99389095640146" },
    ],
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
    name: "CLOUDSAT",
    color: "#00ff00",
    perigeeAltitude: 500,
    eccentricity: 0.1,
    inclination: 45,
    longitudeOfAscendingNode: 0,
    argumentOfPeriapsis: 0,
    speedMultiplier: 20,
    maxSensorBaseRadius: 0.9,
    maxSensorHeight: 2.3,
    telemetry: [
      { property: "TLE Age [days]", value: "0.00642712139845" },
      { property: "Latitude [deg]", value: "-10.876602968423622" },
      { property: "Longitude [deg]", value: "82.10832996989672" },
      { property: "Altitude [m]", value: "712755.7803755393" },
      { property: "Position: J2000", value: "" },
      { property: "x [m]", value: "6913754.353686401" },
      { property: "y [m]", value: "1020064.48018664" },
      { property: "z [m]", value: "-1369098.6635943877" },
      { property: "Velocity: J2000", value: "" },
      { property: "dx/dt [m/s]", value: "-1264.1465604729071" },
      { property: "dy/dt [m/s]", value: "-1297.115418884501" },
      { property: "dz/dt [m/s]", value: "-7259.2769434856" },
      { property: "Keplarian Elem. (Osc / J...)", value: "" },
      { property: "Semimajor axis (a) [m]", value: "7176190.836970952" },
      { property: "Eccentricity (e)", value: "0.00091686175972369" },
      { property: "Inclination (i) [deg]", value: "98.27833532275806" },
      { property: "RAAN [deg]", value: "-171.99389095640146" },
    ],
    trackData: {
      color: "cyan",
      rotation: 10,
      scale: { x: 0.75, y: 0.9 }
    },
    passPredictions: [
      { id: 1, rise: { date: "04 Apr", time: "15:10", az: "SE", deg: "49.7°" }, set: { date: "04 Apr", time: "15:27", az: "NE", deg: "43.2°" }, duration: "479.154", visibility: "R" },
      { id: 2, rise: { date: "04 Apr", time: "16:55", az: "S", deg: "36.1°" }, set: { date: "04 Apr", time: "17:12", az: "N", deg: "39.4°" }, duration: "435.627", visibility: "R" },
      { id: 3, rise: { date: "05 Apr", time: "04:40", az: "E", deg: "51.5°" }, set: { date: "05 Apr", time: "05:00", az: "W", deg: "55.8°" }, duration: "578.942", visibility: "V" },
      { id: 4, rise: { date: "05 Apr", time: "06:25", az: "NE", deg: "34.8°" }, set: { date: "05 Apr", time: "06:42", az: "SE", deg: "39.1°" }, duration: "451.376", visibility: "V" },
      { id: 5, rise: { date: "05 Apr", time: "08:10", az: "N", deg: "57.3°" }, set: { date: "05 Apr", time: "08:30", az: "S", deg: "61.6°" }, duration: "615.789", visibility: "V" },
    ]
  },
  {
    id: "sat-5",
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
    telemetry: [
      { property: "TLE Age [days]", value: "0.00983712139845" },
      { property: "Latitude [deg]", value: "-13.296602968423622" },
      { property: "Longitude [deg]", value: "78.50832996989672" },
      { property: "Altitude [m]", value: "698755.7803755393" },
      { property: "Position: J2000", value: "" },
      { property: "x [m]", value: "6773754.353686401" },
      { property: "y [m]", value: "1110064.48018664" },
      { property: "z [m]", value: "-1489098.6635943877" },
      { property: "Velocity: J2000", value: "" },
      { property: "dx/dt [m/s]", value: "-1284.1465604729071" },
      { property: "dy/dt [m/s]", value: "-1277.115418884501" },
      { property: "dz/dt [m/s]", value: "-7289.2769434856" },
      { property: "Keplarian Elem. (Osc / J...)", value: "" },
      { property: "Semimajor axis (a) [m]", value: "6986190.836970952" },
      { property: "Eccentricity (e)", value: "0.00121686175972369" },
      { property: "Inclination (i) [deg]", value: "97.87833532275806" },
      { property: "RAAN [deg]", value: "-168.79389095640146" },
    ],
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

const initialGroundStations = [
  { id: 1, name: "KLC", selected: true }
];

export const SatelliteProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [satellites] = useState<SatelliteData[]>(initialSatellitesData);
  const [selectedSatelliteId, setSelectedSatelliteId] = useState<string>("sat-1");
  const [groundStations, setGroundStations] = useState(initialGroundStations);

  const selectedSatellite = satellites.find(sat => sat.id === selectedSatelliteId) || null;

  return (
    <SatelliteContext.Provider 
      value={{ 
        satellites, 
        selectedSatelliteId, 
        setSelectedSatelliteId,
        selectedSatellite,
        groundStations,
        setGroundStations
      }}
    >
      {children}
    </SatelliteContext.Provider>
  );
};
