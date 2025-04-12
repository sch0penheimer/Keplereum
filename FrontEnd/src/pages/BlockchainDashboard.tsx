
import BlocksHeader from '@/components/blockchain/BlocksHeader';
import TransactionFees from '@/components/blockchain/TransactionFees';
import BlockchainStats from '@/components/blockchain/BlockchainStats';
import TransactionMemoryPool from '@/components/blockchain/TransactionMemoryPool';
import MemoryUsage from '@/components/blockchain/MemoryUsage';
import { useBlockchainContext } from '@/contexts/BlockchainContext';

const BlockchainDashboard = () => {
  const { loading } = useBlockchainContext();

  if (loading) {
    return (
      <div className="flex items-center justify-center h-screen bg-satellite-dark">
        <h1 className="text-white text-xl">Loading Blockchain Data...</h1>
      </div>
    );
  }

  return (
    <div className="flex flex-col h-full bg-satellite-dark overflow-hidden">
        {/* 1/4 height for BlocksHeader */}
        <div className="h-1/4 flex-shrink-0">
          <BlocksHeader />
        </div>
        
        {/* 1/4 height for TransactionFees and BlockchainStats */}
        <div className="h-1/4 grid grid-cols-2 gap-2 p-2 flex-shrink-0">
          <div className="col-span-1 h-full">
            <TransactionFees />
          </div>
          
          <div className="col-span-1 h-full">
            <BlockchainStats />
          </div>
        </div>
        
        {/* 2/4 height for TransactionMemoryPool and MemoryUsage */}
        <div className="h-2/4 grid grid-cols-2 gap-2 p-2 flex-shrink-0 overflow-hidden">
          <div className="col-span-1 h-full overflow-hidden">
            <TransactionMemoryPool />
          </div>
          
          <div className="col-span-1 h-full overflow-hidden">
            <MemoryUsage />
          </div>
        </div>
    </div>
  );
};

export default BlockchainDashboard;
