
import React, { useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { X } from 'lucide-react';
import { useBlockchainContext } from '@/contexts/BlockchainContext';
import BlocksHeader from '@/components/blockchain/BlocksHeader';

const BlockDetails = () => {
  const { blockNumber } = useParams();
  const { blocks } = useBlockchainContext();
  const navigate = useNavigate();
  
  const block = blocks.find(b => b.number === Number(blockNumber));
  
  useEffect(() => {
    if (!block) {
      navigate('/blockchain');
    }
  }, [block, navigate]);
  
  if (!block) {
    return null;
  }
  
  const handleClose = () => {
    navigate('/blockchain');
  };
  
  // Calculate time ago
  const minutesAgo = Math.floor((new Date().getTime() - block.timestamp.getTime()) / 60000);
  
  return (
    <div className="flex flex-col h-screen overflow-hidden bg-satellite-dark">
      <main className="flex-1 overflow-hidden">
        <BlocksHeader />
        
        <div className="p-4">
          <div className="bg-satellite-dark border border-satellite-border rounded">
            <div className="p-4 flex justify-between items-center">
              <h2 className="text-2xl text-white">
                Block <span className="text-satellite-accent">{block.number}</span>
              </h2>
              <button 
                onClick={handleClose}
                className="text-gray-400 hover:text-white"
              >
                <X size={24} />
              </button>
            </div>
            
            <div className="border-t border-satellite-border p-4 grid grid-cols-2 gap-4">
              <div className="space-y-4">
                <div className="grid grid-cols-3 gap-y-4">
                  <div className="col-span-1 text-gray-400 text-sm">Hash</div>
                  <div className="col-span-2 text-white text-sm font-mono">
                    {block.hash.substring(0, 15)}...
                  </div>
                  
                  <div className="col-span-1 text-gray-400 text-sm">Timestamp</div>
                  <div className="col-span-2 text-white text-sm">
                    {block.timestamp.toLocaleString()} <span className="text-gray-500 text-xs ml-1">({minutesAgo} minutes ago)</span>
                  </div>
                  
                  <div className="col-span-1 text-gray-400 text-sm">Size</div>
                  <div className="col-span-2 text-white text-sm">
                    {block.size} MB
                  </div>
                  
                  <div className="col-span-1 text-gray-400 text-sm">Weight</div>
                  <div className="col-span-2 text-white text-sm">
                    {Math.floor(block.size * 2.5)} MWU
                  </div>
                  
                  <div className="col-span-1 text-gray-400 text-sm">Health</div>
                  <div className="col-span-2">
                    <div className="bg-green-700 text-white text-xs rounded px-2 py-1 inline-block">
                      100%
                    </div>
                  </div>
                </div>
              </div>
              
              <div className="space-y-4">
                <div className="grid grid-cols-3 gap-y-4">
                  <div className="col-span-1 text-gray-400 text-sm">Fee span</div>
                  <div className="col-span-2 text-white text-sm">
                    1 - 100 <span className="text-xs">sat/vB</span>
                  </div>
                  
                  <div className="col-span-1 text-gray-400 text-sm">Median fee</div>
                  <div className="col-span-2 text-white text-sm">
                    ~2 <span className="text-xs">sat/vB</span> <span className="text-green-500 ml-1">$0.23</span>
                  </div>
                  
                  <div className="col-span-1 text-gray-400 text-sm">Total fees</div>
                  <div className="col-span-2 text-white text-sm">
                    {block.totalFees.toFixed(3)} <span className="text-xs">ETH</span> <span className="text-green-500 ml-1">${(block.totalFees * 2800).toFixed(2)}</span>
                  </div>
                  
                  <div className="col-span-1 text-gray-400 text-sm">Subsidy + fees</div>
                  <div className="col-span-2 text-white text-sm">
                    {(block.totalFees + 2).toFixed(3)} <span className="text-xs">ETH</span> <span className="text-green-500 ml-1">${((block.totalFees + 2) * 2800).toFixed(2)}</span>
                  </div>
                  
                  <div className="col-span-1 text-gray-400 text-sm">Validator</div>
                  <div className="col-span-2 flex items-center">
                    <div className="w-4 h-4 bg-blue-500 rounded-full mr-2"></div>
                    <span className="text-white text-sm">Satellite Alpha</span>
                  </div>
                </div>
              </div>
            </div>
            
            <div className="p-4 border-t border-satellite-border">
              <div className="grid grid-cols-2 gap-4">
                <div>
                  <h3 className="text-xl text-white mb-4">Expected Block</h3>
                  <div className="w-full h-60 bg-satellite-dark-header rounded">
                    <div className="w-full h-full flex flex-wrap content-start overflow-hidden p-2">
                      {Array.from({ length: 100 }).map((_, i) => {
                        const size = Math.random();
                        let cellSize = 'w-2 h-2';
                        let color = 'bg-green-800';
                        
                        if (size > 0.98) {
                          cellSize = 'w-12 h-12';
                          color = 'bg-blue-500';
                        } else if (size > 0.95) {
                          cellSize = 'w-8 h-8';
                          color = 'bg-green-600';
                        } else if (size > 0.85) {
                          cellSize = 'w-4 h-4';
                          color = 'bg-green-700';
                        }
                        
                        return (
                          <div 
                            key={i} 
                            className={`${cellSize} ${color} rounded m-[1px]`}
                          />
                        );
                      })}
                    </div>
                  </div>
                </div>
                
                <div>
                  <h3 className="text-xl text-white mb-4">Actual Block</h3>
                  <div className="w-full h-60 bg-satellite-dark-header rounded">
                    <div className="w-full h-full flex flex-wrap content-start overflow-hidden p-2">
                      {Array.from({ length: 100 }).map((_, i) => {
                        const size = Math.random();
                        let cellSize = 'w-2 h-2';
                        let color = 'bg-green-800';
                        
                        if (size > 0.98) {
                          cellSize = 'w-12 h-12';
                          color = 'bg-blue-500';
                        } else if (size > 0.95) {
                          cellSize = 'w-8 h-8';
                          color = 'bg-green-600';
                        } else if (size > 0.85) {
                          cellSize = 'w-4 h-4';
                          color = 'bg-green-700';
                        }
                        
                        return (
                          <div 
                            key={i} 
                            className={`${cellSize} ${color} rounded m-[1px]`}
                          />
                        );
                      })}
                    </div>
                  </div>
                  
                  <div className="mt-4 bg-satellite-dark-header p-3 rounded">
                    <div className="grid grid-cols-2 gap-2 text-sm">
                      <div className="text-gray-400">Transaction</div>
                      <div className="text-satellite-accent font-mono">529c11c...166f245f</div>
                      
                      <div className="text-gray-400">Confirmed</div>
                      <div className="text-white">After 7 minutes</div>
                      
                      <div className="text-gray-400">Amount</div>
                      <div className="text-white">0.14089144 <span className="text-xs">ETH</span></div>
                      
                      <div className="text-gray-400">Fee</div>
                      <div className="text-white">9.924 <span className="text-xs">sat</span> <span className="text-green-500 ml-1">$8.26</span></div>
                    </div>
                    
                    <div className="mt-2 flex justify-end space-x-2">
                      <div className="bg-blue-900/50 text-blue-400 text-xs rounded px-2 py-1">RBF enabled</div>
                      <div className="bg-blue-900/50 text-blue-400 text-xs rounded px-2 py-1">Version 2</div>
                      <div className="bg-blue-900/50 text-blue-400 text-xs rounded px-2 py-1">Consolidation</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
};

export default BlockDetails;
