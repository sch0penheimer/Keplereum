
import React, { createContext, useContext, useState, useEffect, ReactNode } from "react";
import {
  BlockTransaction,
  Block,
  Validator,
  NetworkStats,
  BlockchainContextType
} from "@/types/blockchain";

const BlockchainContext = createContext<BlockchainContextType | undefined>(undefined);


/**
 * Temporary Function to generate Dummy / Mock Validators 
 * @param id
 */
const generateMockValidator = (id: number): Validator => {
  const names = ["Satellite Alpha", "Satellite Beta", "Satellite Gamma", "Satellite Delta", "Satellite Epsilon"];
  return {
    address: `0x${Math.random().toString(16).substring(2, 42)}`,
    name: names[id % names.length],
    blocksValidated: Math.floor(Math.random() * 1000) + 100,
    isActive: Math.random() > 0.2,
  };
};

/**
 * Temporary Function to generate Dummy / Mock Transactions
 * @param blockNumber
 * @param id
 */
const generateMockTransaction = (blockNumber: number, id: number): BlockTransaction => {
  const now = new Date();
  const timestamp = new Date(now.getTime() - Math.random() * 3600000);
  const status = Math.random() > 0.1 ? 'confirmed' : 'pending';
  
  return {
    id: `0x${Math.random().toString(16).substring(2, 42)}`,
    hash: `0x${Math.random().toString(16).substring(2, 42)}`,
    from: `0x${Math.random().toString(16).substring(2, 42)}`,
    to: `0x${Math.random().toString(16).substring(2, 42)}`,
    amount: parseFloat((Math.random() * 10).toFixed(4)),
    fee: parseFloat((Math.random() * 0.1).toFixed(5)),
    status: status,
    timestamp: timestamp,
    gasPrice: Math.floor(Math.random() * 100) + 10,
    gasLimit: 21000,
    gasUsed: status === 'confirmed' ? Math.floor(Math.random() * 21000) : 0,
  };
};

/**
 * Temporary Function to generate Dummy / Mock Blocks
 * @param blockNumber 
 */
const generateMockBlock = (blockNumber: number): Block => {
  const transactionCount = Math.floor(Math.random() * 200) + 50;
  const transactions = Array.from({ length: transactionCount }, (_, i) => 
    generateMockTransaction(blockNumber, i)
  );
  
  const now = new Date();
  const timestamp = new Date(now.getTime() - (891695 - blockNumber) * 12000);
  
  return {
    number: blockNumber,
    hash: `0x${Math.random().toString(16).substring(2, 42)}`,
    timestamp: timestamp,
    validator: `0x${Math.random().toString(16).substring(2, 42)}`,
    size: parseFloat((Math.random() * 2 + 0.5).toFixed(2)),
    gasUsed: Math.floor(Math.random() * 8000000) + 2000000,
    gasLimit: 10000000,
    transactions: transactions,
    transactionCount: transactions.length,
    totalFees: transactions.reduce((sum, tx) => sum + tx.fee, 0),
  };
};

export const BlockchainProvider: React.FC<{children: ReactNode}> = ({ children }) => {
  const [blocks, setBlocks] = useState<Block[]>([]);
  const [pendingTransactions, setPendingTransactions] = useState<BlockTransaction[]>([]);
  const [validators, setValidators] = useState<Validator[]>([]);
  const [loading, setLoading] = useState(true);
  const [selectedBlock, setSelectedBlock] = useState<Block | null>(null);
  const [networkStats, setNetworkStats] = useState<NetworkStats>({
    avgBlockTime: 10.1,
    pendingTransactions: 0,
    activeValidators: 0,
    totalBlocks: 0,
    gasPrice: {
      low: 1,
      medium: 2,
      high: 4,
    },
  });

  useEffect(() => {
    //** Generate mock blocks **//
    const mockBlocks = Array.from({ length: 10 }, (_, i) => 
      generateMockBlock(891695 - i)
    );
    
    //** Generate pending transactions **//
    const mockPendingTxs = Array.from({ length: 50 }, (_, i) => 
      generateMockTransaction(0, i)
    ).filter(tx => tx.status === 'pending');
    
    //** Generate validators **//
    const mockValidators = Array.from({ length: 6 }, (_, i) => 
      generateMockValidator(i)
    );
    
    setBlocks(mockBlocks);
    setPendingTransactions(mockPendingTxs);
    setValidators(mockValidators);
    setNetworkStats(prev => ({
      ...prev,
      pendingTransactions: mockPendingTxs.length,
      activeValidators: mockValidators.filter(v => v.isActive).length,
      totalBlocks: 891695,
    }));
    setLoading(false);
  }, []);

  //** New blocks Simulation **//
  useEffect(() => {
    if (loading) return;
    
    const interval = setInterval(() => {
      const latestBlockNumber = blocks.length > 0 ? blocks[0].number + 1 : 891696;
      const newBlock = generateMockBlock(latestBlockNumber);
      
      setBlocks(prev => [newBlock, ...prev.slice(0, 9)]);
      
      //** Update pending transactions, removing those included in the new block **//
      const newPendingTxIDs = new Set(newBlock.transactions.map(tx => tx.id));
      setPendingTransactions(prev => 
        prev.filter(tx => !newPendingTxIDs.has(tx.id))
          .concat(Array.from({ length: Math.floor(Math.random() * 10) }, (_, i) => 
            generateMockTransaction(0, i)
          ).filter(tx => tx.status === 'pending'))
      );
      
      //** Update network stats **//
      setNetworkStats(prev => ({
        ...prev,
        avgBlockTime: parseFloat((Math.random() * 2 + 9).toFixed(1)),
        totalBlocks: prev.totalBlocks + 1,
      }));
    }, 60000); //* --> minute *//
    
    return () => clearInterval(interval);
  }, [blocks, loading]);

  const value = {
    blocks,
    latestBlock: blocks.length > 0 ? blocks[0] : null,
    pendingTransactions,
    validators,
    networkStats,
    loading,
    selectedBlock,
    setSelectedBlock,
  };

  return (
    <BlockchainContext.Provider value={value}>
      {children}
    </BlockchainContext.Provider>
  );
};

export const useBlockchainContext = () => {
  const context = useContext(BlockchainContext);
  if (context === undefined) {
    throw new Error('useBlockchainContext must be used within a BlockchainProvider');
  }
  return context;
};
