
import { useState, useEffect } from "react";
import { useSatelliteContext } from "@/contexts/SatelliteContext";
import { Satellite } from "lucide-react";

const Header = () => {
  const { selectedSatellite } = useSatelliteContext();
  const [currentTime, setCurrentTime] = useState(new Date());
  
  useEffect(() => {
    const intervalId = setInterval(() => {
      setCurrentTime(new Date());
    }, 1000);
    
    return () => clearInterval(intervalId);
  }, []);
  
  const formattedTime = currentTime.toLocaleTimeString('en-US', {
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
    hour12: true
  });
  
  const formattedDate = currentTime.toLocaleDateString('en-US', {
    year: 'numeric',
    month: 'short',
    day: 'numeric'
  });
  
  return (
    <header className="bg-satellite-dark-header py-2 px-4 border-b border-satellite-border flex justify-between items-center">
      <div className="flex items-center gap-2">
        <Satellite className="w-6 h-6 text-satellite-accent" />
        <div className="w-1 h-10 bg-satellite-accent rounded-full animate-pulse ml-1"></div>
        <h1 className="text-white text-xl font-bold pl-1">Keplereum</h1>
      </div>
      
      <div className="flex items-center gap-4">
        <div className="w-4 h-4 bg-satellite-accent rounded-full animate-pulse"></div>
        {selectedSatellite && (
          <div className="px-3 py-1 bg-satellite-accent/20 rounded-full text-sm text-satellite-accent border border-satellite-accent/30">
            {selectedSatellite.name}
          </div>
        )}
        
        <div className="text-sm text-gray-400">
          <div className="text-right">{formattedTime}</div>
          <div className="text-right text-xs opacity-80">{formattedDate}</div>
        </div>
      </div>
    </header>
  );
};

export default Header;
