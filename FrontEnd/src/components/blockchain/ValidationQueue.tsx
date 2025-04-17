
import React from 'react';
import BlockchainWindow from './BlockchainWindow';
import { useBlockchainContext } from '@/contexts/BlockchainContext';
import { Satellite, CircleDot, CheckCircle2 } from 'lucide-react';

const ValidationQueue = () => {
  const { validators } = useBlockchainContext();
  const activeValidators = validators.filter(v => v.isActive);
  
  // Find current validator (for demo, we'll use the first active one)
  const currentValidatorIndex = 0;
  
  return (
    <BlockchainWindow title="AUTHORITY / VALIDATION QUEUE" className="h-full">
      <div className="p-3 h-full">
        <div className="grid grid-cols-5 gap-2 h-full">
          {activeValidators.map((validator, index) => {
            const isCurrentValidator = index === currentValidatorIndex;
            const isNextValidator = index === (currentValidatorIndex + 1) % activeValidators.length;
            
            return (
              <div
                key={validator.address}
                className={`relative flex flex-col items-center justify-center p-3 rounded transition-all duration-300 ${
                  isCurrentValidator 
                    ? 'bg-gradient-to-r from-green-800 to-green-700' 
                    : isNextValidator
                    ? 'bg-gradient-to-r from-blue-800 to-blue-700'
                    : 'bg-satellite-dark'
                }`}
              >
                <div className="absolute top-2 right-2">
                  {isCurrentValidator ? (
                    <CheckCircle2 className="h-4 w-4 text-green-400" />
                  ) : isNextValidator ? (
                    <CircleDot className="h-4 w-4 text-blue-400" />
                  ) : (
                    <CircleDot className="h-4 w-4 text-gray-400" />
                  )}
                </div>
                
                <Satellite 
                  className={`mb-2 h-8 w-8 ${
                    isCurrentValidator 
                      ? 'text-green-400' 
                      : isNextValidator
                      ? 'text-blue-400'
                      : 'text-gray-400'
                  }`}
                />
                
                <div className="text-xs font-medium mb-1 text-center">
                  {validator.name}
                </div>
                
                <div className="text-xs text-gray-400 mb-1 text-center">
                  {isCurrentValidator ? 'Currently Validating' : isNextValidator ? 'Next Validator' : 'Waiting'}
                </div>
                
                <div className="text-[10px] text-gray-500 text-center">
                  {`${validator.blocksValidated} blocks`}
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </BlockchainWindow>
  );
};

export default ValidationQueue;
