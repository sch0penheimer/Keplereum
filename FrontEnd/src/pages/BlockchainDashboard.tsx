
import React from 'react';
import BlocksHeader from '@/components/blockchain/BlocksHeader';
import TransactionFees from '@/components/blockchain/TransactionFees';
import BlockchainStats from '@/components/blockchain/BlockchainStats';
import MemoryVisualization from '@/components/blockchain/MemoryVisualization';
import MemoryUsage from '@/components/blockchain/MemoryUsage';
import { useBlockchainContext } from '@/contexts/BlockchainContext';

const BlockchainDashboard = () => {
  const { loading } = useBlockchainContext();

  if (loading) {
    return (
      <div className="flex items-center justify-center h-screen bg-satellite-dark">
        <h1 className="text-white text-xl">Loading Blockchain Data...</h1>
      </div>
    );
  }

  return (
    <div className="flex flex-col h-screen overflow-hidden bg-satellite-dark">
      <main className="flex-1 overflow-hidden flex flex-col">
        {/* Give more vertical space to the blocks header */}
        <div className="h-60">
          <BlocksHeader />
        </div>
        
        <div className="grid grid-cols-2 gap-2 p-2 flex-1 overflow-auto">
          <div className="col-span-1">
            <TransactionFees />
          </div>
          
          <div className="col-span-1">
            <BlockchainStats />
          </div>
          
          <div className="col-span-1">
            <MemoryVisualization />
          </div>
          
          <div className="col-span-1">
            <MemoryUsage />
          </div>
        </div>
      </main>
    </div>
  );
};

export default BlockchainDashboard;
