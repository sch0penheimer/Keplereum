
import React from 'react';
import BlockchainWindow from './BlockchainWindow';
import { useBlockchainContext } from '@/contexts/BlockchainContext';

const BlockchainStats = () => {
  const { networkStats } = useBlockchainContext();
  
  return (
    <BlockchainWindow title="BLOCKCHAIN STATS" className="h-full">
      <div className="p-4 h-full flex flex-col justify-center">
        <div className="grid grid-cols-3 gap-4">
          <div className="text-center">
            <div className="text-2xl text-satellite-accent font-bold">
              ~{networkStats.avgBlockTime} minutes
            </div>
            <div className="text-xs text-gray-400 mt-1">Average block time</div>
            <div className="flex items-center justify-center mt-2">
              <span className="text-red-500 text-sm font-medium">↑ 0.72 %</span>
              <span className="text-xs text-gray-500 ml-2">Previous: <span className="text-green-500">↓ 6.81 %</span></span>
            </div>
          </div>
          
          <div className="h-full border-l border-r border-satellite-border flex flex-col items-center justify-center">
            <div className="w-full bg-satellite-accent h-2 rounded-full mb-2">
              <div className="h-full rounded-full bg-gradient-to-r from-blue-500 to-green-400" style={{ width: '65%' }}></div>
            </div>
            <div className="text-center mt-1">
              <div className="text-xs text-gray-400">Difficulty Adjustment</div>
              <div className="text-xs text-gray-500 mt-1">difficulty / halving</div>
            </div>
          </div>
          
          <div className="text-center">
            <div className="text-2xl text-satellite-accent font-bold">
              In ~9 days
            </div>
            <div className="text-xs text-gray-400 mt-1">Next difficulty change</div>
            <div className="text-xs text-gray-500 mt-2">April 19 at 5:49 PM</div>
          </div>
        </div>
      </div>
    </BlockchainWindow>
  );
};

export default BlockchainStats;
