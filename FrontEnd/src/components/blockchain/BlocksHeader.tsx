
import React from 'react';
import { useBlockchainContext } from '@/contexts/BlockchainContext';
import { useNavigate } from 'react-router-dom';
import { Box } from 'lucide-react';
import { Separator } from "@/components/ui/separator";

const BlocksHeader = () => {
  const { blocks, pendingTransactions } = useBlockchainContext();
  const navigate = useNavigate();

  const handleBlockClick = (blockNumber: number) => {
    navigate(`/blockchain/block/${blockNumber}`);
  };

  // Calculate a number of pending blocks based on pending transactions
  const pendingBlocksCount = Math.ceil(pendingTransactions.length / 50);
  const pendingBlocks = Array.from({ length: pendingBlocksCount }, (_, i) => ({
    id: `pending-${i}`,
    transactionCount: Math.min(50, pendingTransactions.length - i * 50),
    validator: pendingTransactions[0]?.from || "Unknown",
    timestamp: new Date()
  }));

  return (
    <div className="w-full overflow-x-auto bg-satellite-dark-header p-4">
      <div className="flex space-x-6">
        {/* Pending Blocks Section */}
        <div className="flex-1 pr-6">
          <div className="text-white text-sm font-medium mb-3">Pending Blocks</div>
          <div className="flex space-x-4 overflow-x-auto">
            {pendingBlocks.map((block, index) => {
              // Calculate fill percentage based on transaction count (1-50)
              const fillPercentage = Math.min(100, (block.transactionCount / 50) * 100);
              const minutesAgo = Math.floor(Math.random() * 10) + 1; // Random time for pending blocks
              
              return (
                <div key={block.id} className="min-w-[150px] cursor-pointer">
                  <div className="bg-satellite-dark border border-satellite-border rounded-md overflow-hidden transition-transform hover:scale-105">
                    <div className="relative h-40 w-full">
                      {/* Block Icon Container */}
                      <div className="absolute inset-0 flex items-center justify-center">
                        <Box className="h-14 w-14 text-satellite-highlight opacity-70" />
                      </div>
                      
                      {/* Fill Indicator */}
                      <div 
                        className="absolute bottom-0 w-full bg-satellite-highlight/20" 
                        style={{ height: `${fillPercentage}%` }}
                      ></div>
                      
                      {/* Information Overlay */}
                      <div className="absolute bottom-0 left-0 right-0 p-3 text-center">
                        <div className="text-white text-sm font-medium">
                          {block.transactionCount} txns
                        </div>
                        <div className="text-xs text-white/70">
                          ~{minutesAgo}m
                        </div>
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
        <div className="flex-1">
          <div className="text-white text-sm font-medium mb-3">Latest Blocks</div>
          <div className="flex space-x-4 overflow-x-auto">
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
                  <div className="bg-satellite-dark border border-satellite-border rounded-md overflow-hidden transition-transform hover:scale-105">
                    <div className="relative h-40 w-full">
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
