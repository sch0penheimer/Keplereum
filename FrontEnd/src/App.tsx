
import { Toaster } from "@/components/ui/toaster";
import { Toaster as Sonner } from "@/components/ui/sonner";
import { TooltipProvider } from "@/components/ui/tooltip";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Index from "./pages/Index";
import NotFound from "./pages/NotFound";
import BlockchainDashboard from "./pages/BlockchainDashboard";
import BlockDetails from "./pages/BlockDetails";
import TransactionDetails from "./pages/TransactionDetails";
import { SatelliteProvider } from "./contexts/SatelliteContext";
import { BlockchainProvider } from "./contexts/BlockchainContext";
import Header from "./components/Header";

const queryClient = new QueryClient();

const App = () => {
  return (
    <QueryClientProvider client={queryClient}>
      <TooltipProvider>
        <SatelliteProvider>
          <BlockchainProvider>
            <Toaster />
            <Sonner />
            <BrowserRouter>
              <div className="flex flex-col h-screen">
                <Header />
                <div className="flex-1 overflow-hidden">
                  <Routes>
                    <Route path="/" element={<Index />} />
                    <Route path="/blockchain" element={<BlockchainDashboard />} />
                    <Route path="/blockchain/block/:blockNumber" element={<BlockDetails />} />
                    <Route path="/blockchain/transaction/:transactionId" element={<TransactionDetails />} />
                    <Route path="*" element={<NotFound />} />
                  </Routes>
                </div>
              </div>
            </BrowserRouter>
          </BlockchainProvider>
        </SatelliteProvider>
      </TooltipProvider>
    </QueryClientProvider>
  );
};

export default App;


