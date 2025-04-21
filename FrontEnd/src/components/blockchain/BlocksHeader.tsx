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

  //** Calculate a number of pending blocks based on pending transactions **//
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

  // Get the block item component - used for both pending and validated blocks for consistency
  const BlockItem = ({ 
    type, 
    number = null, 
    transactionCount, 
    validator, 
    fillPercentage, 
    minutesAgo, 
    onClick = null 
  }) => {
    const isPending = type === 'pending';
    const colorClass = isPending ? 'text-satellite-highlight' : 'text-satellite-accent';
    const bgColorClass = isPending ? 'bg-satellite-highlight/20' : 'bg-satellite-accent/20';
    
    return (
      <div 
        className={`min-w-[150px] cursor-pointer h-full flex flex-col ${onClick ? '' : ''}`}
        onClick={onClick}
      >
        {/* Block number for validated blocks */}
        {!isPending && number !== null && (
          <div className="text-center text-satellite-accent mb-1">
            #{number}
          </div>
        )}
        
        {/* Block container */}
        <div className="bg-satellite-dark border border-satellite-border rounded-md transition-transform hover:scale-105 flex-1 flex flex-col">
          <div className="relative flex-1">
            {/* Block Icon Container - scaled with container */}
            <div className="absolute inset-0 flex items-center justify-center">
              <Box className={`w-[70%] h-[70%] ${colorClass} opacity-70`} />
            </div>

            {/* Fill Indicator */}
            <div
              className={`absolute bottom-0 w-full transition-all duration-500 ${bgColorClass}`}
              style={{ height: `${fillPercentage}%` }}
            ></div>

            {/* Information Overlay */}
            <div className="absolute inset-5 flex flex-col items-center justify-center text-center p-2">
              <div className="text-white text-sm font-medium">
                {transactionCount} txns
              </div>
              <div className="text-xs text-white/70">
                {isPending ? `~${minutesAgo}m` : `${minutesAgo}m ago`}
              </div>
            </div>
          </div>

          {/* Validator */}
          <div className="p-2 border-t border-satellite-border">
            <div className="text-xs text-white/70 truncate">
              {isPending ? 'Satellite: ' : 'Validator: '}{validator.substring(0, 10)}...
            </div>
          </div>
        </div>
      </div>
    );
  };

  return (
    <div className="w-full h-full bg-satellite-dark-header p-4 flex flex-col">
      <div className="flex flex-1 space-x-4 h-full">
        {/* Pending Blocks Section */}
        <div className="w-1/2 flex flex-col h-full">
          <div className="text-white text-sm font-medium mb-1">Pending Blocks</div>
          <div className="flex space-x-4 p-2 mt-7 flex-1 items-center justify-end">
            {pendingBlocks.map((block) => {
              const minutesAgo = Math.floor(Math.random() * 10) + 1;
              
              return (
                <BlockItem
                  key={block.id}
                  type="pending"
                  transactionCount={block.transactionCount}
                  validator={block.validator}
                  fillPercentage={block.fillPercentage}
                  minutesAgo={minutesAgo}
                />
              );
            })}
          </div>
        </div>
        
        {/* Separator */}
        <Separator orientation="vertical" className="h-auto my-2 border-dashed border-satellite-border" />
        
        {/* Validated Blocks Section */}
        <div className="w-1/2 flex flex-col h-full">
          <div className="text-white text-sm font-medium mb-1">Latest Blocks</div>
          <div className="flex space-x-4 p-2 flex-1 items-center">
            {blocks.slice(0, 6).map((block) => {
              const minutesAgo = Math.floor(
                (new Date().getTime() - block.timestamp.getTime()) / 60000
              );
              
              // Calculate fill percentage based on transaction count (relative to highest in recent blocks)
              const maxTxCount = Math.max(...blocks.slice(0, 6).map(b => b.transactionCount));
              const fillPercentage = Math.min(100, (block.transactionCount / maxTxCount) * 100);
              
              return (
                <BlockItem
                  key={block.number}
                  type="validated"
                  number={block.number}
                  transactionCount={block.transactionCount}
                  validator={block.validator}
                  fillPercentage={fillPercentage}
                  minutesAgo={minutesAgo}
                  onClick={() => handleBlockClick(block.number)}
                />
              );
            })}
          </div>
        </div>
      </div>
    </div>
  );
};

export default BlocksHeader;
