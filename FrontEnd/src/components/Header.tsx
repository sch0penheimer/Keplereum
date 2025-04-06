
import React, { useState, useEffect } from "react";
import { Satellite } from "lucide-react";

const Header = () => {
  const [currentTime, setCurrentTime] = useState(new Date());
  
  useEffect(() => {
    const intervalId = setInterval(() => {
      setCurrentTime(new Date());
    }, 1000);
    
    return () => clearInterval(intervalId);
  }, []);
  
  const formatDate = (date: Date) => {
    const options: Intl.DateTimeFormatOptions = {
      day: "2-digit", 
      month: "short", 
      year: "numeric",
      hour: "2-digit", 
      minute: "2-digit", 
      second: "2-digit", 
      fractionalSecondDigits: 3
    };
    
    const formatted = new Intl.DateTimeFormat('en-US', options).format(date);
    return `${formatted} UTC`;
  };
  
  return (
    <header className="bg-satellite-header h-12 px-4 flex items-center justify-between border-b border-satellite-border">
      <div className="flex items-center gap-2">
        <Satellite className="w-6 h-6 text-satellite-accent" />
        <h1 className="text-lg font-bold text-satellite-text">Keplereum</h1>
      </div>
      <div className="absolute left-1/2 transform -translate-x-1/2">
        <span className="text-sm text-satellite-text/70">{formatDate(currentTime)}</span>
      </div>
    </header>
  );
};

export default Header;
