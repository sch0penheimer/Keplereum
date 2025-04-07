import Header from "@/components/Header";
import InfoComponent from "@/components/InfoComponent";
import Earth2DWindow from "@/components/Earth2DWindow";
import ObjectList from "@/components/ObjectList";
import Earth3DWindow from "@/components/Earth3DWindow";
import TrackingTool1 from "@/components/TrackingTool1";
import TrackingTool2 from "@/components/TrackingTool2";
import { useSatelliteContext } from "@/contexts/SatelliteContext";

const Index = () => {
  const { loading } = useSatelliteContext();

  if (loading) {
    return (
      <div className="flex items-center justify-center h-screen bg-satellite-dark">
        <h1 className="text-white text-xl">Loading Satellites...</h1>
      </div>
    );
  }

  return (
    <div className="flex flex-col h-screen overflow-hidden bg-satellite-dark">
      <Header />
      
      <main className="flex-1 overflow-hidden p-2">
        <div className="grid grid-cols-4 grid-rows-2 gap-2 h-full">
          {/* Top Row */}
          <InfoComponent />
          <Earth2DWindow />
          <ObjectList />
          
          {/* Bottom Row */}
          <Earth3DWindow />
          <TrackingTool1 />
          <TrackingTool2 />
        </div>
      </main>
    </div>
  );
};

export default Index;
