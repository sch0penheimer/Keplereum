
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
  const names = ["Alpha-TCF1", "Beta-CX", "Gamma-II", "Delta", "Epsilon-XIX"];
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
  const isAlertTx = Math.random() > 0.3;

  // Base transaction
  const baseTx: BlockTransaction = {
    id: `0x${id.toString(16).padStart(64, '0')}`,
    hash: `0x${Math.random().toString(16).substring(2, 66)}`,
    from: `0x${Math.random().toString(16).substring(2, 42)}`,
    to: `0x${Math.random().toString(16).substring(2, 42)}`,
    amount: parseFloat((Math.random() * 10).toFixed(4)),
    fee: parseFloat((Math.random() * 0.1).toFixed(5)),
    status: status,
    timestamp: timestamp,
    gasPrice: Math.floor(Math.random() * 100) + 10,
    gasLimit: 21000,
    gasUsed: status === 'confirmed' ? Math.floor(Math.random() * 21000) : 0,
    blockNumber: status === 'confirmed' ? blockNumber : null,
  };

  if (!isAlertTx) return baseTx;

  // Alert-specific data
  const alertTypes = ['FIRE', 'CYCLONE', 'TSUNAMI'];
  const actions: Array<BlockTransaction['action']> = ['SWITCH_ORBIT', 'SWITCH_SENSOR'];
  
  const txType = Math.random() > 0.5 ? 'ALERT_SUBMISSION' : 
                 Math.random() > 0.5 ? 'ALERT_CONFIRMATION' : 'VALIDATOR_ACTION';

  switch (txType) {
    case 'ALERT_SUBMISSION':
      return {
        ...baseTx,
        to: '0xAlertContractAddress',
        alertType: alertTypes[Math.floor(Math.random() * alertTypes.length)],
        latitude: parseFloat((Math.random() * 180 - 90).toFixed(6)),
        longitude: parseFloat((Math.random() * 360 - 180).toFixed(6)),
      };

    case 'ALERT_CONFIRMATION':
      return {
        ...baseTx,
        to: '0xAlertContractAddress',
        confirmsAlertId: `0x${Math.random().toString(16).substring(2, 66)}`,
      };

    case 'VALIDATOR_ACTION':
      return {
        ...baseTx,
        from: '0xValidatorAddress',
        to: '0xActionContractAddress',
        action: actions[Math.floor(Math.random() * actions.length)],
      };

    default:
      return baseTx;
  }
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
    parentHash: `0x${Math.random().toString(16).substring(2, 42)}`,
    sha3uncles: `0x${Math.random().toString(16).substring(2, 66)}`,
    transactionRoot: `0x${Math.random().toString(16).substring(2, 66)}`,
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

const generateNetworkStats = (): NetworkStats => {
  const baseDifficulty = Math.floor(Math.random() * 100000) + 50000;
  const hashRate = Math.floor(Math.random() * 500) + 100;
  
  return {
    avgBlockTime: parseFloat((Math.random() * 2 + 9).toFixed(1)),
    pendingTransactions: Math.floor(Math.random() * 200),
    activeValidators: Math.floor(Math.random() * 5) + 3,
    totalBlocks: 891695 + Math.floor(Math.random() * 100),
    difficulty: baseDifficulty,
    difficultyPercent: Math.min(100, Math.floor((baseDifficulty / 150000) * 100)),
    hashRate: hashRate,
    hashRateChange: parseFloat((Math.random() * 10 - 5).toFixed(2)), // -5% to +5%
    latency: Math.floor(Math.random() * 150) + 50, // 50-200ms
    latencyChange: parseFloat((Math.random() * 20 - 10).toFixed(2)), // -10% to +10%
    gasPrice: {
      low: Math.floor(Math.random() * 3) + 1,
      medium: Math.floor(Math.random() * 5) + 3,
      high: Math.floor(Math.random() * 10) + 6,
    },
  };
};

export const BlockchainProvider: React.FC<{children: ReactNode}> = ({ children }) => {
  const [blocks, setBlocks] = useState<Block[]>([]);
  const [pendingTransactions, setPendingTransactions] = useState<BlockTransaction[]>([]);
  const [validators, setValidators] = useState<Validator[]>([]);
  const [loading, setLoading] = useState(true);
  const [selectedBlock, setSelectedBlock] = useState<Block | null>(null);
  const [networkStats, setNetworkStats] = useState<NetworkStats>(generateNetworkStats());

  useEffect(() => {
    const mockBlocks = Array.from({ length: 10 }, (_, i) => 
      generateMockBlock(891695 - i)
    );
    
    const mockPendingTxs = Array.from({ length: 50 }, (_, i) => 
      generateMockTransaction(0, i)
    ).filter(tx => tx.status === 'pending');
    
    const mockValidators = Array.from({ length: 6 }, (_, i) => 
      generateMockValidator(i)
    );
    
    setBlocks(mockBlocks);
    setPendingTransactions(mockPendingTxs);
    setValidators(mockValidators);
    setNetworkStats(generateNetworkStats());
    setLoading(false);
  }, []);

  useEffect(() => {
    if (loading) return;
    
    const blockInterval = setInterval(() => {
      const latestBlockNumber = blocks.length > 0 ? blocks[0].number + 1 : 891696;
      const newBlock = generateMockBlock(latestBlockNumber);
      
      setBlocks(prev => [newBlock, ...prev.slice(0, 9)]);
      
      const newPendingTxIDs = new Set(newBlock.transactions.map(tx => tx.id));
      setPendingTransactions(prev => 
        prev.filter(tx => !newPendingTxIDs.has(tx.id))
          .concat(Array.from({ length: Math.floor(Math.random() * 10) }, (_, i) => 
            generateMockTransaction(0, i)
          ).filter(tx => tx.status === 'pending'))
      );
    }, 60000); // New block every minute

    const statsInterval = setInterval(() => {
      setNetworkStats(prev => ({
        ...generateNetworkStats(),
        totalBlocks: prev.totalBlocks + 1
      }));
    }, 15000); // Update stats every 15 seconds

    return () => {
      clearInterval(blockInterval);
      clearInterval(statsInterval);
    };
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
