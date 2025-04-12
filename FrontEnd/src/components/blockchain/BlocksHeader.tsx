
import { useState, useEffect } from 'react';
import { useBlockchainContext } from '@/contexts/BlockchainContext';
import { useNavigate } from 'react-router-dom';
import { Box } from 'lucide-react';
import { Separator } from "@/components/ui/separator";

const BlocksHeader = () => {
  const { blocks, pendingTransactions } = useBlockchainContext();
  const navigate = useNavigate();
  const [pendingBlocks, setPendingBlocks] = useState<Array<{
    id: string;
    transactionCount: number;
    validator: string;
    timestamp: Date;
    fillPercentage: number;
  }>>([]);

  const handleBlockClick = (blockNumber: number) => {
    navigate(`/blockchain/block/${blockNumber}`);
  };

  // Calculate a number of pending blocks based on pending transactions
  useEffect(() => {
    const pendingBlocksCount = Math.ceil(pendingTransactions.length / 50);
    const initialPendingBlocks = Array.from({ length: pendingBlocksCount }, (_, i) => ({
      id: `pending-${i}`,
      transactionCount: Math.min(50, pendingTransactions.length - i * 50),
      validator: pendingTransactions[0]?.from || "Unknown",
      timestamp: new Date(),
      fillPercentage: Math.min(100, (Math.min(50, pendingTransactions.length - i * 50) / 50) * 100)
    }));
    
    setPendingBlocks(initialPendingBlocks);
  }, [pendingTransactions]);

  // Simulate transaction progression and block validation
  useEffect(() => {
    const intervalId = setInterval(() => {
      setPendingBlocks(prev => {
        // First, increment the fillPercentage of each pending block
        const updatedBlocks = prev.map(block => ({
          ...block,
          fillPercentage: Math.min(100, block.fillPercentage + Math.random() * 5)
        }));
        
        // If the first block is full (100%), remove it (simulate it moving to blockchain)
        if (updatedBlocks.length > 0 && updatedBlocks[0].fillPercentage >= 100) {
          return updatedBlocks.slice(1);
        }
        
        return updatedBlocks;
      });
    }, 2000);
    
    return () => clearInterval(intervalId);
  }, []);

  return (
    <div className="w-full h-full bg-satellite-dark-header p-4 flex flex-col">
      <div className="flex flex-1 space-x-4">
        {/* Pending Blocks Section */}
        <div className="w-1/2 flex flex-col h-full">
          <div className="text-white text-sm font-medium mb-1">Pending Blocks</div>
          <div className="flex space-x-4 p-2 py-9 justify-end">
            {pendingBlocks.map((block, index) => {
              const minutesAgo = Math.floor(Math.random() * 10) + 1;

              return (
                <div key={block.id} className="min-w-[150px] cursor-pointer">
                  <div className="bg-satellite-dark border border-satellite-border rounded-md overflow-hidden transition-transform hover:scale-105 h-32 flex flex-col">
                    <div className="relative flex-1">
                      {/* Block Icon Container */}
                      <div className="absolute inset-0 flex items-center justify-center">
                        <Box className="h-14 w-14 text-satellite-highlight opacity-70" />
                      </div>

                      {/* Fill Indicator */}
                      <div
                        className="absolute bottom-0 w-full bg-satellite-highlight/20 transition-all duration-500"
                        style={{ height: `${block.fillPercentage}%` }}
                      ></div>

                      {/* Information Overlay */}
                      <div className="absolute bottom-0 left-0 right-0 p-3 text-center">
                        <div className="text-white text-sm font-medium">
                          {block.transactionCount} txns
                        </div>
                        <div className="text-xs text-white/70">~{minutesAgo}m</div>
                      </div>
                    </div>

                    {/* Validator */}
                    <div className="p-2 border-t border-satellite-border">
                      <div className="text-xs text-white/70 truncate">
                        Satellite: {block.validator.substring(0, 10)}...
                      </div>
                    </div>
                  </div>
                </div>
              );
            })}
          </div>
        </div>
        
        {/* Separator */}
        <Separator orientation="vertical" className="h-auto my-2 border-dashed border-satellite-border" />
        
        {/* Validated Blocks Section */}
        <div className="w-1/2 flex flex-col h-full">
          <div className="text-white text-sm font-medium mb-1">Latest Blocks</div>
            <div className="flex space-x-4 p-2">
              {blocks.slice(0, 6).map((block) => {
                const minutesAgo = Math.floor(
                  (new Date().getTime() - block.timestamp.getTime()) / 60000
                );
                
                // Calculate fill percentage based on transaction count (relative to highest in recent blocks)
                const maxTxCount = Math.max(...blocks.slice(0, 6).map(b => b.transactionCount));
                const fillPercentage = Math.min(100, (block.transactionCount / maxTxCount) * 100);
                
                return (
                  <div 
                    key={block.number} 
                    className="min-w-[150px] cursor-pointer"
                    onClick={() => handleBlockClick(block.number)}
                  >
                    <div className="text-center text-satellite-accent mb-1">
                      #{block.number}
                    </div>
                    <div className="bg-satellite-dark border border-satellite-border rounded-md overflow-hidden transition-transform hover:scale-105 h-32 flex flex-col">
                      <div className="relative flex-1">
                        {/* Block Icon Container */}
                        <div className="absolute inset-0 flex items-center justify-center">
                          <Box className="h-14 w-14 text-satellite-accent opacity-70" />
                        </div>
                        
                        {/* Fill Indicator */}
                        <div 
                          className="absolute bottom-0 w-full bg-satellite-accent/20" 
                          style={{ height: `${fillPercentage}%` }}
                        ></div>
                        
                        {/* Information Overlay */}
                        <div className="absolute bottom-0 left-0 right-0 p-3 text-center">
                          <div className="text-white text-sm font-medium">
                            {block.transactionCount} txns
                          </div>
                          <div className="text-xs text-white/70">
                            {minutesAgo}m ago
                          </div>
                        </div>
                      </div>
                      
                      {/* Validator */}
                      <div className="p-2 border-t border-satellite-border">
                        <div className="text-xs text-white/70 truncate">
                          Validator: {block.validator.substring(0, 10)}...
                        </div>
                      </div>
                    </div>
                  </div>
                );
              })}
            </div>
        </div>
      </div>
    </div>
  );
};

export default BlocksHeader;
