
import React from 'react';
import BlockchainWindow from './BlockchainWindow';
import { useBlockchainContext } from '@/contexts/BlockchainContext';

const TransactionFees = () => {
  const { networkStats } = useBlockchainContext();
  const { gasPrice } = networkStats;

  return (
    <BlockchainWindow title="TRANSACTION FEES" className="h-full">
      <div className="p-2 h-full flex flex-col">
        <div className="grid grid-cols-4 gap-1 h-full">
          <div className="bg-satellite-dark-header rounded p-2 flex flex-col items-center justify-center">
            <div className="text-center text-xs text-white mb-1">No Priority</div>
            <div className="text-center text-xl text-white font-semibold">1 <span className="text-xs">sat/vB</span></div>
            <div className="text-center text-sm text-green-500">$0.12</div>
          </div>

          <div className="col-span-3 grid grid-cols-3 gap-1">
            <div className="bg-gradient-to-r from-green-800 to-green-700 rounded p-2 flex flex-col items-center justify-center">
              <div className="text-center text-xs text-white mb-1">Low Priority</div>
              <div className="text-center text-xl text-white font-semibold">
                {gasPrice.low} <span className="text-xs">sat/vB</span>
              </div>
              <div className="text-center text-sm text-green-500">$0.12</div>
            </div>
            
            <div className="bg-gradient-to-r from-green-700 to-green-600 rounded p-2 flex flex-col items-center justify-center">
              <div className="text-center text-xs text-white mb-1">Medium Priority</div>
              <div className="text-center text-xl text-white font-semibold">
                {gasPrice.medium} <span className="text-xs">sat/vB</span>
              </div>
              <div className="text-center text-sm text-green-500">$0.23</div>
            </div>
            
            <div className="bg-gradient-to-r from-green-600 to-green-500 rounded p-2 flex flex-col items-center justify-center">
              <div className="text-center text-xs text-white mb-1">High Priority</div>
              <div className="text-center text-xl text-white font-semibold">
                {gasPrice.high} <span className="text-xs">sat/vB</span>
              </div>
              <div className="text-center text-sm text-green-500">$0.47</div>
            </div>
          </div>
        </div>
      </div>
    </BlockchainWindow>
  );
};

export default TransactionFees;
