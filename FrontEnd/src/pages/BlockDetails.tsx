
import { useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { X, ArrowRight } from 'lucide-react';
import { useBlockchainContext } from '@/contexts/BlockchainContext';
import BlocksHeader from '@/components/blockchain/BlocksHeader';

const BlockDetails = () => {
  const { blockNumber } = useParams<{ blockNumber: string }>();
  const { blocks } = useBlockchainContext();
  const navigate = useNavigate();
  
  // Convert blockNumber to a number for comparison
  const blockNum = blockNumber ? parseInt(blockNumber, 10) : null;
  const block = blocks.find(b => b.number === blockNum);
  
  useEffect(() => {
    // Only redirect if we have blocks loaded and the requested block doesn't exist
    if (!block && blocks.length > 0 && blockNum !== null && !isNaN(blockNum)) {
      console.log("Block not found, redirecting to blockchain dashboard");
      navigate('/blockchain');
    }
  }, [block, blocks, blockNum, navigate]);
  
  if (!block) {
    return (
      <div className="flex flex-col h-screen bg-satellite-dark">
        <BlocksHeader />
        <div className="flex items-center justify-center flex-1">
          <h1 className="text-white text-xl">Loading Block Data...</h1>
        </div>
      </div>
    );
  }
  
  const handleClose = () => {
    navigate('/blockchain');
  };
  
  const handleTransactionClick = (txId: string) => {
    navigate(`/blockchain/transaction/${txId}`);
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
              <h3 className="text-xl text-white mb-4">Transactions ({block.transactionCount})</h3>
              <div className="max-h-80 overflow-y-auto pr-2">
                <div className="space-y-2">
                  {block.transactions.slice(0, 20).map((tx) => (
                    <div 
                      key={tx.id} 
                      className="bg-satellite-dark-header border border-satellite-border rounded p-3 hover:bg-satellite-dark-header/80 transition-all cursor-pointer"
                      onClick={() => handleTransactionClick(tx.id)}
                    >
                      <div className="grid grid-cols-7 gap-2">
                        <div className="col-span-2">
                          <div className="text-xs text-satellite-accent font-mono truncate">
                            {tx.hash.substring(0, 10)}...
                          </div>
                          <div className="text-xs text-gray-400 mt-1">
                            {Math.floor(Math.random() * 10) + 1}m ago
                          </div>
                        </div>
                        
                        <div className="col-span-3 flex items-center">
                          <div className="text-xs text-white/70 truncate w-24">
                            {tx.from.substring(0, 7)}...
                          </div>
                          <ArrowRight className="h-3 w-3 mx-2 text-satellite-accent" />
                          <div className="text-xs text-white/70 truncate w-24">
                            {tx.to.substring(0, 7)}...
                          </div>
                        </div>
                        
                        <div className="col-span-2 text-right">
                          <div className="text-xs text-green-500">
                            {tx.amount.toFixed(4)} SAT
                          </div>
                          <div className="text-xs text-gray-400 mt-1">
                            Fee: {tx.fee.toFixed(5)}
                          </div>
                        </div>
                      </div>
                    </div>
                  ))}
                  
                  {block.transactionCount > 20 && (
                    <div className="text-center text-white/50 text-sm py-2">
                      + {block.transactionCount - 20} more transactions
                    </div>
                  )}
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