import React, { useState, useEffect } from "react";
import SatelliteWindow from "./SatelliteWindow";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { Input } from "@/components/ui/input";
import { Checkbox } from "@/components/ui/checkbox";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Button } from "@/components/ui/button";
import { useSatelliteContext } from "@/contexts/SatelliteContext";

import {
  Pagination,
  PaginationContent,
  PaginationItem,
  PaginationLink,
  PaginationNext,
  PaginationPrevious,
} from "@/components/ui/pagination";

const ITEMS_PER_PAGE = 4;

const PassPredictionsContent = () => {
  const { selectedSatellite } = useSatelliteContext();
  const [page, setPage] = useState(1);
  const [azimuthFormat, setAzimuthFormat] = useState("compass");
  const [visibleOnly, setVisibleOnly] = useState(false);
  const [passPredictions, setPassPredictions] = useState<any[]>([]);
  const [timeSpan, setTimeSpan] = useState(10);
  const [timeStep, setTimeStep] = useState(60);
  const [nextUpdateTime, setNextUpdateTime] = useState(0);
  
  useEffect(() => {
    if (!selectedSatellite || !selectedSatellite.passPredictions) return;
    
    setPassPredictions(selectedSatellite.passPredictions);
    
    const interval = setInterval(() => {
      if (selectedSatellite.passPredictions) {
        const timeElapsed = timeStep;
        
        const updatedPredictions = selectedSatellite.passPredictions.map(pass => {
          const riseDate = new Date(`2025-${pass.rise.date.replace(' ', '-')} ${pass.rise.time}`);
          const setDate = new Date(`2025-${pass.set.date.replace(' ', '-')} ${pass.set.time}`);
          
          riseDate.setSeconds(riseDate.getSeconds() - timeElapsed);
          setDate.setSeconds(setDate.getSeconds() - timeElapsed);
          
          const formatDate = (date: Date) => {
            const day = date.getDate().toString().padStart(2, '0');
            const month = date.toLocaleString('en-US', { month: 'short' });
            return `${day} ${month}`;
          };
          
          const formatTime = (date: Date) => {
            return date.toLocaleTimeString('en-US', { 
              hour: '2-digit', 
              minute: '2-digit',
              hour12: false
            });
          };
          
          const varyAzimuth = (az: string, deg: string) => {
            if (az.length <= 3) {
              const degValue = parseFloat(deg);
              const newDegValue = degValue + (Math.random() * 2 - 1);
              return {
                az: az,
                deg: `${newDegValue.toFixed(1)}°`
              };
            }
            return { az, deg };
          };
          
          const riseAzimuth = varyAzimuth(pass.rise.az, pass.rise.deg);
          const setAzimuth = varyAzimuth(pass.set.az, pass.set.deg);
          
          return {
            ...pass,
            rise: {
              date: formatDate(riseDate),
              time: formatTime(riseDate),
              az: riseAzimuth.az,
              deg: riseAzimuth.deg
            },
            set: {
              date: formatDate(setDate),
              time: formatTime(setDate),
              az: setAzimuth.az,
              deg: setAzimuth.deg
            }
          };
        });
        
        setPassPredictions(updatedPredictions);
        setNextUpdateTime(timeStep);
      }
    }, 5000);
    
    return () => clearInterval(interval);
  }, [selectedSatellite, timeStep]);
  
  useEffect(() => {
    if (nextUpdateTime <= 0) return;
    
    const timer = setInterval(() => {
      setNextUpdateTime(prev => Math.max(0, prev - 1));
    }, 1000);
    
    return () => clearInterval(timer);
  }, [nextUpdateTime]);
  
  if (!selectedSatellite || !passPredictions.length) {
    return (
      <div className="h-full flex items-center justify-center">
        <p className="text-sm text-gray-500">No pass prediction data available</p>
      </div>
    );
  }
  
  const filteredPasses = visibleOnly 
    ? passPredictions.filter(pass => pass.visibility === "V") 
    : passPredictions;
  
  const totalPages = Math.ceil(filteredPasses.length / ITEMS_PER_PAGE);
  const currentPageData = filteredPasses.slice(
    (page - 1) * ITEMS_PER_PAGE,
    page * ITEMS_PER_PAGE
  );
  
  return (
    <div className="p-2 h-full flex flex-col">
      <div className="mb-3 grid grid-cols-2 gap-3">
        <div>
          <Label htmlFor="timeSpan" className="mb-1 text-xs block">Time Span [Days]:</Label>
          <Input 
            id="timeSpan" 
            value={timeSpan} 
            onChange={(e) => setTimeSpan(parseInt(e.target.value) || 10)}
            className="h-8 text-sm" 
          />
        </div>
        <div>
          <Label htmlFor="timeStep" className="mb-1 text-xs block">Time Step [sec]:</Label>
          <Input 
            id="timeStep" 
            value={timeStep} 
            onChange={(e) => setTimeStep(parseInt(e.target.value) || 60)}
            className="h-8 text-sm" 
          />
        </div>
      </div>
      
      <div className="mb-3 flex items-center justify-between">
        <div className="flex items-center gap-2">
          <Checkbox 
            id="visibleOnly" 
            checked={visibleOnly} 
            onCheckedChange={(checked) => setVisibleOnly(checked as boolean)}
          />
          <Label htmlFor="visibleOnly" className="text-xs">Visible Only</Label>
        </div>
        
        <div className="flex items-center gap-2">
          <Label htmlFor="riseSet" className="text-xs">Rise/Set Azimuth:</Label>
          <Select 
            value={azimuthFormat} 
            onValueChange={setAzimuthFormat}
          >
            <SelectTrigger className="h-7 w-40 text-xs">
              <SelectValue placeholder="Select" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="compass">Compass Points</SelectItem>
              <SelectItem value="degrees">Degrees</SelectItem>
            </SelectContent>
          </Select>
        </div>
      </div>
      
      <div className="flex-1 overflow-auto">
        <table className="satellite-table w-full text-xs">
          <thead>
            <tr>
              <th className="w-8">#</th>
              <th>Rise Time</th>
              <th>Rise Az</th>
              <th>Set Time</th>
              <th>Set Az</th>
              <th>Duration</th>
              <th className="w-6">V</th>
            </tr>
          </thead>
          <tbody>
            {currentPageData.map((pass) => (
              <tr key={pass.id} className={pass.visibility === "V" ? "text-green-500" : ""}>
                <td>{pass.id}</td>
                <td>{`${pass.rise.date} ${pass.rise.time}`}</td>
                <td>{azimuthFormat === "compass" ? pass.rise.az : pass.rise.deg}</td>
                <td>{`${pass.set.date} ${pass.set.time}`}</td>
                <td>{azimuthFormat === "compass" ? pass.set.az : pass.set.deg}</td>
                <td>{pass.duration}</td>
                <td>{pass.visibility}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      
      {totalPages > 1 && (
        <div className="mt-3">
          <Pagination>
            <PaginationContent>
              <PaginationItem>
                <PaginationPrevious 
                  onClick={() => setPage(p => Math.max(1, p - 1))}
                  className={`cursor-pointer ${page === 1 ? 'opacity-50 cursor-not-allowed' : ''}`}
                />
              </PaginationItem>
              
              {Array.from({ length: totalPages }).map((_, i) => (
                <PaginationItem key={i}>
                  <PaginationLink 
                    isActive={page === i + 1}
                    onClick={() => setPage(i + 1)}
                    className="cursor-pointer"
                  >
                    {i + 1}
                  </PaginationLink>
                </PaginationItem>
              ))}
              
              <PaginationItem>
                <PaginationNext 
                  onClick={() => setPage(p => Math.min(totalPages, p + 1))}
                  className={`cursor-pointer ${page === totalPages ? 'opacity-50 cursor-not-allowed' : ''}`}
                />
              </PaginationItem>
            </PaginationContent>
          </Pagination>
        </div>
      )}
      
      <div className="mt-3 flex justify-between items-center">
        <div className="text-xs">
          Next update in: {nextUpdateTime}s
        </div>
        <Button variant="secondary" size="sm" className="text-xs">Go to Pass</Button>
      </div>
    </div>
  );
};

const TrackingTool2 = () => {
  const { selectedSatellite } = useSatelliteContext();
  
  return (
    <SatelliteWindow 
      title={`Tracking Tool (${selectedSatellite ? selectedSatellite.name : "No Selection"})`} 
      className="col-span-1 row-span-2"
    >
      <Tabs defaultValue="pass" className="h-full flex flex-col">
        <TabsList className="bg-satellite-header rounded-none border-b border-satellite-border">
          <TabsTrigger value="basic" className="text-xs">Basic</TabsTrigger>
          <TabsTrigger value="polar" className="text-xs">Polar Plot</TabsTrigger>
          <TabsTrigger value="pass" className="text-xs">Pass Predictions</TabsTrigger>
        </TabsList>
        <TabsContent value="basic" className="flex-1 p-2">
          <p className="text-sm">Basic tracking information for {selectedSatellite?.name || "No Selection"}</p>
        </TabsContent>
        <TabsContent value="polar" className="flex-1 p-2">
          <p className="text-sm">Polar plot for {selectedSatellite?.name || "No Selection"}</p>
        </TabsContent>
        <TabsContent value="pass" className="flex-1 m-0 p-0 data-[state=active]:h-full">
          <PassPredictionsContent />
        </TabsContent>
      </Tabs>
    </SatelliteWindow>
  );
};

export default TrackingTool2;
