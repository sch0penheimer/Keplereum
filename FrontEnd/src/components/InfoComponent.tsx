
import { useState, useEffect } from "react";
import SatelliteWindow from "./SatelliteWindow";
import { useSatelliteContext } from "@/contexts/SatelliteContext";
import {
  getCurrentSatelliteTelemetry
} from "@/utils/satelliteUtils";
import SatellitePreviewer from "./SatellitePreviewer";

const InfoComponent = () => {
  const { selectedSatellite, setSatellites} = useSatelliteContext();
  const [telemetryData, setTelemetryData] = useState<{ property: string; value: string | number }[]>([]);
  
  useEffect(() => {
    let intervalId: NodeJS.Timeout | null = null;

    if (selectedSatellite) {
      intervalId = setInterval(() => {
        const updatedTelemetry = getCurrentSatelliteTelemetry(selectedSatellite);

        setSatellites((prevSatellites) =>
          prevSatellites.map((satellite) =>
            satellite.id === selectedSatellite.id
              ? { ...satellite, telemetry: updatedTelemetry }
              : satellite
          )
        );

        setTelemetryData(updatedTelemetry);
      }, 200);
    }

    return () => {
      if (intervalId) clearInterval(intervalId);
    };
  }, [selectedSatellite, setSatellites]);

  return (
    <SatelliteWindow title={selectedSatellite.name} className="col-span-1 row-span-1">
      <div className="h-full overflow-y-auto">
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
