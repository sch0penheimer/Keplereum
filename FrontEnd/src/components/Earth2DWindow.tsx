
import { useEffect, useRef } from "react";
import SatelliteWindow from "./SatelliteWindow";
import { useSatelliteContext } from "@/contexts/SatelliteContext";

const Earth2DWindow = () => {
  const { selectedSatellite } = useSatelliteContext();
  const containerRef = useRef<HTMLDivElement>(null);
  
  //** Resize Hnadling for Proper Scaling **//
  useEffect(() => {
    const handleResize = () => {
      if (containerRef.current) {
      }
    };
    
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);
  
  return (
    <SatelliteWindow title="2D Earth Window" className="col-span-2 row-span-1">
      <div ref={containerRef} className="h-full w-full bg-[#121619] flex items-center justify-center text-gray-400 flex-col relative">
        <p>2D Earth Rendering Component Placeholder</p>
        {selectedSatellite && (
          <div className="absolute bottom-2 left-2 text-xs text-gray-400 bg-black/50 px-2 py-1 rounded">
            Tracking: {selectedSatellite.name}
          </div>
        )}
      </div>
    </SatelliteWindow>
  );
};

export default Earth2DWindow;
