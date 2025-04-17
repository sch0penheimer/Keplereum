
import BlockchainWindow from './BlockchainWindow';
import { ChartContainer } from "@/components/ui/chart";
import { AreaChart, Area, XAxis, YAxis, Tooltip, ResponsiveContainer } from 'recharts';
import { useBlockchainContext } from '@/contexts/BlockchainContext';

const MemoryUsage = () => {
  const { pendingTransactions } = useBlockchainContext();
  
  // Generate mock data for incoming transactions chart
  const generateChartData = () => {
    const data = [];
    const timestamps = ["21:45", "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30"];
    
    for (let i = 0; i < timestamps.length; i++) {
      const baseValue = 1000 + Math.random() * 1000;
      const peakValue = Math.random() > 0.7 ? baseValue + 800 : baseValue;
      
      data.push({
        time: timestamps[i],
        transactions: Math.floor(baseValue),
        peaks: Math.floor(peakValue),
      });
    }
    
    return data;
  };
  
  const data = generateChartData();
  
  // Calculate memory usage based on pending transactions //
  const memoryUsage = Math.floor(10.6 + (pendingTransactions.length * 0.02));
  const memoryPercentage = (memoryUsage / 300) * 100;
  
  return (
    <BlockchainWindow title="MEMORY USAGE & TRANSACTIONS" className="h-full">
        <div className="p-3 flex flex-col">
          <div className="grid grid-cols-3 gap-24 mb-3">
            <div className="bg-satellite-dark p-2 rounded">
              <div className="text-xs text-gray-400 mb-1">Minimum fee</div>
              <div className="text-xl text-white">1.00 <span className="text-xs">sat/vB</span></div>
            </div>
            
            <div className="bg-satellite-dark p-2 rounded">
              <div className="text-xs text-gray-400 mb-1">Memory Usage</div>
              <div className="text-sm text-white mb-1">{memoryUsage.toFixed(1)} MB / 300 MB</div>
              <div className="w-full bg-gray-700 rounded-full h-2">
                <div 
                  className="bg-satellite-accent h-2 rounded-full transition-all duration-500" 
                  style={{ width: `${memoryPercentage}%` }}
                ></div>
              </div>
            </div>
            
            <div className="bg-satellite-dark p-2 rounded">
              <div className="text-xs text-gray-400 mb-1">Unconfirmed</div>
              <div className="text-xl text-white">{pendingTransactions.length} <span className="text-xs">TXs</span></div>
            </div>
          </div>
          
          <div className="flex-grow">
            <div className="text-xs text-gray-400 mb-1">Incoming Transactions</div>
            <div className="h-56">
              <ResponsiveContainer width="100%" height="130%">
                <ChartContainer config={{
                  transactions: { color: "#3498DB" },
                  peaks: { color: "#f59e0b" }
                }}>
                  <AreaChart data={data} margin={{ top: 5, right: 5, bottom: 5, left: 5 }}>
                    <defs>
                      <linearGradient id="colorTransactions" x1="0" y1="0" x2="0" y2="1">
                        <stop offset="5%" stopColor="var(--color-transactions)" stopOpacity={0.8}/>
                        <stop offset="95%" stopColor="var(--color-transactions)" stopOpacity={0.1}/>
                      </linearGradient>
                      <linearGradient id="colorPeaks" x1="0" y1="0" x2="0" y2="1">
                        <stop offset="5%" stopColor="var(--color-peaks)" stopOpacity={0.8}/>
                        <stop offset="95%" stopColor="var(--color-peaks)" stopOpacity={0.1}/>
                      </linearGradient>
                    </defs>
                    <XAxis dataKey="time" axisLine={false} tickLine={false} tick={{ fontSize: 10, fill: '#9CA3AF' }} />
                    <YAxis hide={true} domain={[0, 2500]} />
                    <Tooltip 
                      contentStyle={{ backgroundColor: '#1A1F2C', borderColor: '#3D4852', borderRadius: '4px' }}
                      labelStyle={{ color: '#E2E8F0' }}
                      itemStyle={{ color: '#E2E8F0' }}
                    />
                    <Area type="monotone" dataKey="transactions" stroke="var(--color-transactions)" fillOpacity={1} fill="url(#colorTransactions)" />
                    <Area type="monotone" dataKey="peaks" stroke="var(--color-peaks)" fillOpacity={0.3} fill="url(#colorPeaks)" />
                  </AreaChart>
                </ChartContainer>
              </ResponsiveContainer>
            </div>
          </div>
        </div>
    </BlockchainWindow>
  );
};

export default MemoryUsage;
