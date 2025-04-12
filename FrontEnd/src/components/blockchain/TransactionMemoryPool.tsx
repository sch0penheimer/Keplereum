
import { useBlockchainContext } from '@/contexts/BlockchainContext';
import BlockchainWindow from './BlockchainWindow';
import { Clock, ArrowRight } from 'lucide-react';
import { useNavigate } from 'react-router-dom';
import { ScrollArea } from '@/components/ui/scroll-area';

const TransactionMemoryPool = () => {
  const { pendingTransactions } = useBlockchainContext();
  const navigate = useNavigate();
  
  const handleTransactionClick = (txId: string) => {
    navigate(`/blockchain/transaction/${txId}`);
  };
  
  return (
    <BlockchainWindow title="TRANSACTION MEMORY POOL" className="h-full">
      <div className="p-2 h-full flex flex-col">
        <div className="flex justify-between items-center mb-2">
          <div className="text-xs text-gray-400">Recent Transactions</div>
          <div className="text-xs text-white px-2 py-1 bg-satellite-dark-header rounded">
            {pendingTransactions.length} unconfirmed
          </div>
        </div>
        
        <ScrollArea className="flex-1">
          <div className="space-y-2 pr-2">
            {pendingTransactions.slice(0, 20).map((tx, index) => {
              const timeAgo = Math.floor(Math.random() * 10) + 1;
              
              return (
                <div 
                  key={tx.id} 
                  className="bg-satellite-dark border border-satellite-border rounded p-2 hover:bg-satellite-dark/80 transition-colors cursor-pointer"
                  onClick={() => handleTransactionClick(tx.id)}
                >
                  <div className="flex justify-between items-start">
                    <div className="text-xs text-satellite-accent font-mono truncate w-24">
                      {tx.hash.substring(0, 10)}...
                    </div>
                    <div className="flex items-center text-xs text-white/70">
                      <Clock className="h-3 w-3 mr-1" />
                      {timeAgo}m ago
                    </div>
                  </div>
                  
                  <div className="flex items-center mt-2 text-xs">
                    <div className="text-white/70 truncate w-20">
                      {tx.from.substring(0, 7)}...
                    </div>
                    <ArrowRight className="h-3 w-3 mx-2 text-satellite-accent" />
                    <div className="text-white/70 truncate w-20">
                      {tx.to.substring(0, 7)}...
                    </div>
                    <div className="ml-auto text-green-500 font-medium">
                      {tx.amount.toFixed(4)} SAT
                    </div>
                  </div>
                  
                  <div className="flex justify-between items-center mt-2 text-xs">
                    <div className="text-white/50">
                      Fee: <span className="text-satellite-accent">{tx.fee.toFixed(5)} SAT</span>
                    </div>
                    <div className="text-white/50">
                      Gas: {tx.gasLimit}
                    </div>
                  </div>
                </div>
              );
            })}
            
            {pendingTransactions.length === 0 && (
              <div className="text-center text-white/50 text-sm py-4">
                No pending transactions in memory pool
              </div>
            )}
          </div>
        </ScrollArea>
      </div>
    </BlockchainWindow>
  );
};

export default TransactionMemoryPool;
