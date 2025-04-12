
import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { X, FileText, ArrowLeftRight, Clock, CheckCircle, AlertCircle } from 'lucide-react';
import { useBlockchainContext, BlockTransaction } from '@/contexts/BlockchainContext';
import BlocksHeader from '@/components/blockchain/BlocksHeader';

const TransactionDetails = () => {
  const { transactionId } = useParams();
  const { blocks, pendingTransactions } = useBlockchainContext();
  const navigate = useNavigate();
  const [transaction, setTransaction] = useState<BlockTransaction | null>(null);
  
  useEffect(() => {
    // Find transaction in blocks or pending transactions
    let foundTransaction: BlockTransaction | null = null;
    
    // First check in pending transactions
    const pendingTx = pendingTransactions.find(tx => tx.id === transactionId);
    if (pendingTx) {
      foundTransaction = pendingTx;
    } else {
      // Then check in blocks
      for (const block of blocks) {
        const blockTx = block.transactions.find(tx => tx.id === transactionId);
        if (blockTx) {
          foundTransaction = blockTx;
          break;
        }
      }
    }
    
    if (foundTransaction) {
      setTransaction(foundTransaction);
    } else {
      navigate('/blockchain');
    }
  }, [transactionId, blocks, pendingTransactions, navigate]);
  
  const handleClose = () => {
    navigate('/blockchain');
  };
  
  if (!transaction) {
    return null;
  }
  
  // Calculate time ago
  const minutesAgo = Math.floor((new Date().getTime() - transaction.timestamp.getTime()) / 60000);
  
  // Find block containing transaction if confirmed
  const containingBlock = transaction.status === 'confirmed' 
    ? blocks.find(block => block.transactions.some(tx => tx.id === transactionId))
    : null;
  
  return (
    <div className="flex flex-col h-screen overflow-hidden bg-satellite-dark">
      <main className="flex-1 overflow-hidden">
        <BlocksHeader />
        
        <div className="p-4">
          <div className="bg-satellite-dark border border-satellite-border rounded">
            <div className="p-4 flex justify-between items-center">
              <div className="flex items-center">
                <FileText className="text-satellite-accent mr-2" size={24} />
                <h2 className="text-2xl text-white">
                  Transaction <span className="text-satellite-accent font-mono text-lg ml-2">{transaction.hash.substring(0, 10)}...</span>
                </h2>
              </div>
              <button 
                onClick={handleClose}
                className="text-gray-400 hover:text-white"
              >
                <X size={24} />
              </button>
            </div>
            
            <div className="border-t border-satellite-border p-4">
              {/* Status */}
              <div className="mb-6 bg-satellite-dark-header p-3 rounded-md flex items-center">
                {transaction.status === 'confirmed' ? (
                  <>
                    <CheckCircle className="text-green-500 mr-2" size={20} />
                    <span className="text-white">Confirmed</span>
                    <span className="text-gray-400 ml-2 text-sm">
                      in Block <span className="text-satellite-accent">#{containingBlock?.number}</span>
                    </span>
                  </>
                ) : (
                  <>
                    <Clock className="text-yellow-500 mr-2" size={20} />
                    <span className="text-white">Pending</span>
                    <span className="text-gray-400 ml-2 text-sm">
                      waiting to be included in a block
                    </span>
                  </>
                )}
              </div>
              
              {/* Main transaction details */}
              <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
                <div className="space-y-6">
                  {/* Transaction overview */}
                  <div className="bg-satellite-dark-header p-4 rounded-md">
                    <h3 className="text-white text-lg mb-4">Overview</h3>
                    <div className="space-y-3">
                      <div className="grid grid-cols-3 gap-2">
                        <div className="text-gray-400 text-sm">Transaction Hash</div>
                        <div className="col-span-2 text-white text-sm font-mono break-all">
                          {transaction.hash}
                        </div>
                      </div>
                      
                      <div className="grid grid-cols-3 gap-2">
                        <div className="text-gray-400 text-sm">Status</div>
                        <div className="col-span-2">
                          {transaction.status === 'confirmed' ? (
                            <div className="bg-green-900/40 text-green-500 text-xs rounded px-2 py-1 inline-block">
                              Confirmed
                            </div>
                          ) : (
                            <div className="bg-yellow-900/40 text-yellow-500 text-xs rounded px-2 py-1 inline-block">
                              Pending
                            </div>
                          )}
                        </div>
                      </div>
                      
                      <div className="grid grid-cols-3 gap-2">
                        <div className="text-gray-400 text-sm">Timestamp</div>
                        <div className="col-span-2 text-white text-sm">
                          {transaction.timestamp.toLocaleString()} 
                          <span className="text-gray-500 text-xs ml-1">
                            ({minutesAgo} minutes ago)
                          </span>
                        </div>
                      </div>
                      
                      {containingBlock && (
                        <div className="grid grid-cols-3 gap-2">
                          <div className="text-gray-400 text-sm">Block</div>
                          <div className="col-span-2 text-white text-sm">
                            <span 
                              className="text-satellite-accent cursor-pointer hover:underline"
                              onClick={() => navigate(`/blockchain/block/${containingBlock.number}`)}
                            >
                              #{containingBlock.number}
                            </span>
                          </div>
                        </div>
                      )}
                    </div>
                  </div>
                  
                  {/* Transaction details */}
                  <div className="bg-satellite-dark-header p-4 rounded-md">
                    <h3 className="text-white text-lg mb-4">Value/Fee Details</h3>
                    <div className="space-y-3">
                      <div className="grid grid-cols-3 gap-2">
                        <div className="text-gray-400 text-sm">Value</div>
                        <div className="col-span-2 text-white text-sm">
                          {transaction.amount.toFixed(4)} 
                          <span className="text-xs ml-1">SAT</span>
                          <span className="text-green-500 ml-2">
                            (${(transaction.amount * 150).toFixed(2)})
                          </span>
                        </div>
                      </div>
                      
                      <div className="grid grid-cols-3 gap-2">
                        <div className="text-gray-400 text-sm">Transaction Fee</div>
                        <div className="col-span-2 text-white text-sm">
                          {transaction.fee.toFixed(5)} 
                          <span className="text-xs ml-1">SAT</span>
                          <span className="text-green-500 ml-2">
                            (${(transaction.fee * 150).toFixed(2)})
                          </span>
                        </div>
                      </div>
                      
                      <div className="grid grid-cols-3 gap-2">
                        <div className="text-gray-400 text-sm">Gas Price</div>
                        <div className="col-span-2 text-white text-sm">
                          {transaction.gasPrice} 
                          <span className="text-xs ml-1">GWEI</span>
                        </div>
                      </div>
                      
                      <div className="grid grid-cols-3 gap-2">
                        <div className="text-gray-400 text-sm">Gas Limit</div>
                        <div className="col-span-2 text-white text-sm">
                          {transaction.gasLimit}
                        </div>
                      </div>
                      
                      {transaction.status === 'confirmed' && (
                        <div className="grid grid-cols-3 gap-2">
                          <div className="text-gray-400 text-sm">Gas Used</div>
                          <div className="col-span-2 text-white text-sm">
                            {transaction.gasUsed} 
                            <span className="text-gray-500 text-xs ml-1">
                              ({((transaction.gasUsed / transaction.gasLimit) * 100).toFixed(1)}%)
                            </span>
                          </div>
                        </div>
                      )}
                    </div>
                  </div>
                </div>
                
                <div className="space-y-6">
                  {/* From/To */}
                  <div className="bg-satellite-dark-header p-4 rounded-md">
                    <h3 className="text-white text-lg mb-4">From/To</h3>
                    
                    <div className="space-y-6">
                      <div>
                        <div className="text-gray-400 text-sm mb-1">From:</div>
                        <div className="bg-satellite-dark p-3 rounded-md border border-satellite-border">
                          <div className="text-white font-mono text-sm break-all">
                            {transaction.from}
                          </div>
                        </div>
                      </div>
                      
                      <div className="flex justify-center">
                        <ArrowLeftRight className="text-satellite-accent" size={24} />
                      </div>
                      
                      <div>
                        <div className="text-gray-400 text-sm mb-1">To:</div>
                        <div className="bg-satellite-dark p-3 rounded-md border border-satellite-border">
                          <div className="text-white font-mono text-sm break-all">
                            {transaction.to}
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  
                  {/* Transaction Lifecycle */}
                  <div className="bg-satellite-dark-header p-4 rounded-md">
                    <h3 className="text-white text-lg mb-4">Transaction Lifecycle</h3>
                    
                    <div className="relative pt-1">
                      <div className="flex mb-2 items-center justify-between">
                        <div className="flex items-center">
                          <div className="text-satellite-accent bg-satellite-accent/20 rounded-full h-6 w-6 flex items-center justify-center mr-2">
                            1
                          </div>
                          <div className="text-white text-sm">Created</div>
                        </div>
                        <div className="text-xs text-gray-400">
                          {minutesAgo + 2} minutes ago
                        </div>
                      </div>
                      
                      <div className="flex mb-2 items-center justify-between">
                        <div className="flex items-center">
                          <div className="text-satellite-accent bg-satellite-accent/20 rounded-full h-6 w-6 flex items-center justify-center mr-2">
                            2
                          </div>
                          <div className="text-white text-sm">Submitted to Memory Pool</div>
                        </div>
                        <div className="text-xs text-gray-400">
                          {minutesAgo} minutes ago
                        </div>
                      </div>
                      
                      {transaction.status === 'confirmed' ? (
                        <>
                          <div className="flex mb-2 items-center justify-between">
                            <div className="flex items-center">
                              <div className="text-satellite-accent bg-satellite-accent/20 rounded-full h-6 w-6 flex items-center justify-center mr-2">
                                3
                              </div>
                              <div className="text-white text-sm">Included in Block #{containingBlock?.number}</div>
                            </div>
                            <div className="text-xs text-gray-400">
                              {Math.max(1, minutesAgo - 1)} minutes ago
                            </div>
                          </div>
                          
                          <div className="flex mb-2 items-center justify-between">
                            <div className="flex items-center">
                              <div className="text-green-500 bg-green-900/40 rounded-full h-6 w-6 flex items-center justify-center mr-2">
                                ✓
                              </div>
                              <div className="text-white text-sm">Confirmed</div>
                            </div>
                            <div className="text-xs text-gray-400">
                              {Math.max(1, minutesAgo - 2)} minutes ago
                            </div>
                          </div>
                        </>
                      ) : (
                        <div className="flex mb-2 items-center justify-between">
                          <div className="flex items-center">
                            <div className="text-yellow-500 bg-yellow-900/40 rounded-full h-6 w-6 flex items-center justify-center mr-2">
                              <Clock size={14} />
                            </div>
                            <div className="text-white text-sm">Waiting for inclusion in block</div>
                          </div>
                          <div className="text-xs text-gray-400">
                            Pending
                          </div>
                        </div>
                      )}
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

export default TransactionDetails;
