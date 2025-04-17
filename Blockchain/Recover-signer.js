import { Web3 } from 'web3';

// Connect to your Geth node - consider using WebSocket for subscriptions
const web3 = new Web3('http://localhost:8545');

web3.eth.extend({
    property: 'clique',
    methods: [{
      name: 'getSnapshot',
      call: 'clique_getSnapshot',
      params: 1,
    }]
  });

async function getBlockSigner(blockNumber) {
    try {
        const blockParam = `0x${blockNumber.toString(16)}`;
        const snapshot = await web3.eth.clique.getSnapshot(blockParam);

        console.log(`Snapshot for block ${blockNumber}:`, snapshot);

        const signer = snapshot.recents[blockNumber];
        if (signer) {
            console.log(`Block ${blockNumber} was signed by ${signer}`);
            return signer;
        } else {
            console.warn(`No signer found for block ${blockNumber}`);
            return null;
        }
    } catch (error) {
        console.error(`Error getting signer for block ${blockNumber}:`, error.message);
        return null;
    }
}


async function monitorNewBlocks() {
    console.log('Starting block signer monitor...');
    
    // Get current block number to start from
    const lastBlock = await web3.eth.getBlockNumber();
    
    // Check existing blocks
    for (let i = 0; i <= lastBlock; i++) {
        await getBlockSigner(i);
    }
    
    // Use polling instead of subscriptions
    pollForNewBlocks();
}

function pollForNewBlocks() {
    let lastCheckedBlock = 0;
    
    // First, get the current block number
    web3.eth.getBlockNumber()
        .then(currentBlock => {
            lastCheckedBlock = currentBlock;
            
            // Then set up polling
            setInterval(async () => {
                try {
                    const currentBlock = await web3.eth.getBlockNumber();
                    
                    // Check for new blocks
                    for (let i = lastCheckedBlock + 1; i <= currentBlock; i++) {
                        await getBlockSigner(i);
                    }
                    
                    lastCheckedBlock = currentBlock;
                } catch (error) {
                    console.error('Error polling for new blocks:', error);
                }
            }, 5000); // Check every 5 seconds
        })
        .catch(console.error);
}

// Alternative approach: If you're using a Geth node, you might need to use the admin API
async function getSignerAlternative(blockNumber) {
    try {
        const block = await web3.eth.getBlock(blockNumber);
        
        // For Clique, the signer can be extracted from the extraData field
        if (block && block.extraData) {
            // The signer address is typically encoded in the extraData field
            // The exact extraction logic depends on your network's consensus rules
            console.log(`Block ${blockNumber} extraData: ${block.extraData}`);
            
            // Example extraction (adjust based on your Clique implementation):
            // const signer = '0x' + block.extraData.slice(66, 106);
            // console.log(`Block ${blockNumber} was signed by ${signer}`);
            // return signer;
        }
        
        return null;
    } catch (error) {
        console.error(`Error getting block ${blockNumber}:`, error.message);
        return null;
    }
}

monitorNewBlocks().catch(console.error);