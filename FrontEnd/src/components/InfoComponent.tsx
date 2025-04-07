
import React, { useState, useEffect } from "react";
import SatelliteWindow from "./SatelliteWindow";
import { useSatelliteContext } from "@/contexts/SatelliteContext";
import SatellitePreviewer from "./SatellitePreviewer";

const InfoComponent = () => {
  const { selectedSatellite } = useSatelliteContext();
  const [telemetryData, setTelemetryData] = useState<{property: string, value: string | number}[]>([]);
  
  //** Updating Telemetry Data at 500ms intervals to simulate real-time updates **//
  useEffect(() => {
    if (!selectedSatellite) return;
    
    setTelemetryData(selectedSatellite.telemetry || []);
    
    const interval = setInterval(() => {
      if (selectedSatellite && selectedSatellite.telemetry) {
        //** Clone telemetry data and update values to simulate real-time changes **/
        const updatedTelemetry = selectedSatellite.telemetry.map(item => {
          if (typeof item.value === 'string' && !isNaN(parseFloat(item.value as string))) {
            //** For numeric values stored as strings, added small variations **//
            const value = parseFloat(item.value as string);
            const variation = value * 0.0005 * (Math.random() > 0.5 ? 1 : -1);
            return {
              ...item,
              value: (value + variation).toFixed(12)
            };
          } else if (typeof item.value === 'number') {
            const variation = item.value * 0.0005 * (Math.random() > 0.5 ? 1 : -1);
            return {
              ...item,
              value: parseFloat((item.value + variation).toFixed(12))
            };
          }
          return item;
        });
        
        setTelemetryData(updatedTelemetry);
      }
    }, 500);
    
    return () => clearInterval(interval);
  }, [selectedSatellite]);
  
  if (!selectedSatellite) {
    return (
      <SatelliteWindow title="Satellite Information" className="col-span-1 row-span-1">
        <div className="h-full flex items-center justify-center">
          <p className="text-sm text-gray-500">No satellite selected</p>
        </div>
      </SatelliteWindow>
    );
  }

  return (
    <SatelliteWindow title={selectedSatellite.name} className="col-span-1 row-span-1">
      <div className="h-full overflow-y-auto">
      <SatellitePreviewer/>
        <table className="satellite-table w-full">
          <thead>
            <tr>
              <th className="w-1/2">Property</th>
              <th className="w-1/2">Value</th>
            </tr>
          </thead>
          <tbody>
            {telemetryData.map((item, index) => (
              <tr key={index}>
                <td className={item.property.toString().includes(":") ? "font-bold" : ""}>
                  {item.property}
                </td>
                <td className={item.value ? "font-mono" : ""}>
                  {item.value ? item.value.toString() : ""}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </SatelliteWindow>
  );
};

export default InfoComponent;
