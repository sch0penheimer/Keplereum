
import React, { useState, useEffect, useRef } from "react";
import SatelliteWindow from "./SatelliteWindow";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { useSatelliteContext } from "@/contexts/SatelliteContext";

const PolarPlotContent = () => {
  const { selectedSatellite } = useSatelliteContext();
  const [trackPosition, setTrackPosition] = useState({ top: "40%", left: "60%" });
  const animationFrameRef = useRef<number>();
  
  useEffect(() => {
    if (!selectedSatellite || !selectedSatellite.trackData) return;
    
    let angle = 0;
    const track = selectedSatellite.trackData;
    const radius = 35;
    
    const updatePosition = () => {
      //* Calculate position based on circular orbit *//
      angle += 0.01;
      const radians = angle * Math.PI / 180;
      
      //* Adjust radius and center position based on the track data *//
      const adjustedRadius = radius * (track.scale.x + track.scale.y) / 2;
      
      //* Calculate new position *//
      const top = `${50 - Math.cos(radians) * adjustedRadius}%`;
      const left = `${50 + Math.sin(radians) * adjustedRadius}%`;
      
      setTrackPosition({ top, left });
      
      animationFrameRef.current = requestAnimationFrame(updatePosition);
    };
    
    updatePosition();
    
    return () => {
      if (animationFrameRef.current) {
        cancelAnimationFrame(animationFrameRef.current);
      }
    };
  }, [selectedSatellite]);
  
  if (!selectedSatellite || !selectedSatellite.trackData) {
    return (
      <div className="h-full flex items-center justify-center">
        <p className="text-sm text-gray-500">No satellite track data available</p>
      </div>
    );
  }
  
  const track = selectedSatellite.trackData;
  
  return (
    <div className="relative h-full w-full bg-black overflow-hidden">
      <div className="absolute inset-0 flex items-center justify-center">
        {/* Concentric circles */}
        {[0, 1, 2, 3].map((index) => (
          <div 
            key={index}
            className="absolute border border-gray-700 rounded-full"
            style={{ 
              width: `${(index + 1) * 25}%`, 
              height: `${(index + 1) * 25}%`,
              opacity: 0.7
            }}
          />
        ))}
        
        {/* Radial lines for directions */}
        {["N", "NE", "E", "SE", "S", "SW", "W", "NW"].map((dir, i) => {
          const angle = i * 45;
          return (
            <div 
              key={dir}
              className="absolute h-full w-[1px] bg-gray-700 origin-bottom"
              style={{ 
                transform: `translateX(-50%) rotate(${angle}deg)`,
                opacity: 0.7
              }}
            />
          );
        })}
        
        {/* Direction labels */}
        {[
          { label: "N", top: "2%", left: "50%" },
          { label: "NE", top: "15%", left: "85%" },
          { label: "E", top: "50%", left: "98%" },
          { label: "SE", top: "85%", left: "85%" },
          { label: "S", top: "98%", left: "50%" },
          { label: "SW", top: "85%", left: "15%" },
          { label: "W", top: "50%", left: "2%" },
          { label: "NW", top: "15%", left: "15%" },
        ].map((item) => (
          <div 
            key={item.label}
            className="absolute text-xs text-gray-400"
            style={{ 
              top: item.top, 
              left: item.left,
              transform: "translate(-50%, -50%)"
            }}
          >
            {item.label}
          </div>
        ))}
        
        {/* Elevation labels */}
        <div className="absolute text-[10px] text-gray-500 top-[25%] right-[46%]">30°</div>
        <div className="absolute text-[10px] text-gray-500 top-[50%] right-[46%]">60°</div>
        <div className="absolute text-[10px] text-gray-500 top-[75%] right-[46%]">90°</div>

        {/* Satellite track based on selected satellite */}
        <div 
          className="absolute w-[80%] h-[80%] rounded-full border-2 border-dashed" 
          style={{ 
            borderColor: track.color,
            opacity: 0.4,
            transform: `rotate(${track.rotation}deg) scale(${track.scale.x}, ${track.scale.y})` 
          }} 
        />
        
        {/* Secondary track for visual interest */}
        <div 
          className="absolute w-[60%] h-[70%] rounded-full border-2 border-dashed" 
          style={{ 
            borderColor: track.color,
            opacity: 0.3,
            transform: `rotate(${-track.rotation}deg) scale(${track.scale.y}, ${track.scale.x})` 
          }} 
        />
        
        {/* Current position indicator with dynamic position */}
        <div 
          className="absolute h-2 w-2 rounded-full animate-pulse"
          style={{ 
            backgroundColor: track.color,
            top: trackPosition.top,
            left: trackPosition.left,
            transition: "top 0.5s ease, left 0.5s ease"
          }}
        />
      </div>
      
      <div className="absolute bottom-2 left-2 text-xs text-gray-400">
        Satellite: {selectedSatellite.name}
      </div>
    </div>
  );
};

const TrackingTool1 = () => {
  const { selectedSatellite } = useSatelliteContext();
  
  return (
    <SatelliteWindow 
      title={`Tracking Tool (${selectedSatellite ? selectedSatellite.name : "No Selection"})`} 
      className="col-span-1 row-span-2"
    >
      <Tabs defaultValue="polar" className="h-full flex flex-col">
        <TabsList className="bg-satellite-header rounded-none border-b border-satellite-border">
          <TabsTrigger value="basic" className="text-xs">Basic</TabsTrigger>
          <TabsTrigger value="polar" className="text-xs">Polar Plot</TabsTrigger>
          <TabsTrigger value="pass" className="text-xs">Pass Predictions</TabsTrigger>
        </TabsList>
        <TabsContent value="basic" className="flex-1 p-2">
          <p className="text-sm">Basic tracking information for {selectedSatellite?.name || "No Selection"}</p>
        </TabsContent>
        <TabsContent value="polar" className="flex-1 m-0 p-0 data-[state=active]:h-full">
          <PolarPlotContent />
        </TabsContent>
        <TabsContent value="pass" className="flex-1 p-2">
          <p className="text-sm">Pass predictions would go here.</p>
        </TabsContent>
      </Tabs>
    </SatelliteWindow>
  );
};

export default TrackingTool1;
