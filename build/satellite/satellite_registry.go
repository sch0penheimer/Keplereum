// Code generated - DO NOT EDIT.
// This file is a generated binding and any manual changes will be lost.

package satellite

import (
	"errors"
	"math/big"
	"strings"

	ethereum "github.com/ethereum/go-ethereum"
	"github.com/ethereum/go-ethereum/accounts/abi"
	"github.com/ethereum/go-ethereum/accounts/abi/bind"
	"github.com/ethereum/go-ethereum/common"
	"github.com/ethereum/go-ethereum/core/types"
	"github.com/ethereum/go-ethereum/event"
)

// Reference imports to suppress errors if they are not otherwise used.
var (
	_ = errors.New
	_ = big.NewInt
	_ = strings.NewReader
	_ = ethereum.NotFound
	_ = bind.Bind
	_ = common.Big1
	_ = types.BloomLookup
	_ = event.NewSubscription
	_ = abi.ConvertType
)

// SatelliteMetaData contains all meta data concerning the Satellite contract.
var SatelliteMetaData = &bind.MetaData{
	ABI: "[{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"bytes32\",\"name\":\"satelliteId\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"position\",\"type\":\"bytes32\"}],\"name\":\"PositionUpdated\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"bytes32\",\"name\":\"satelliteId\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"address\",\"name\":\"publicKey\",\"type\":\"address\"}],\"name\":\"SatelliteRegistered\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"bytes32\",\"name\":\"satelliteId\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"active\",\"type\":\"bool\"}],\"name\":\"SatelliteStatusChanged\",\"type\":\"event\"},{\"inputs\":[],\"name\":\"getActiveSatellites\",\"outputs\":[{\"internalType\":\"bytes32[]\",\"name\":\"\",\"type\":\"bytes32[]\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"satelliteId\",\"type\":\"bytes32\"}],\"name\":\"getPublicKey\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"satelliteId\",\"type\":\"bytes32\"}],\"name\":\"registerSatellite\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"satelliteIds\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"name\":\"satellites\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"publicKey\",\"type\":\"address\"},{\"internalType\":\"bool\",\"name\":\"active\",\"type\":\"bool\"},{\"internalType\":\"uint256\",\"name\":\"lastPing\",\"type\":\"uint256\"},{\"internalType\":\"bytes32\",\"name\":\"currentPosition\",\"type\":\"bytes32\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"satelliteId\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"position\",\"type\":\"bytes32\"}],\"name\":\"updatePosition\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]",
	Bin: "0x6080604052348015600e575f5ffd5b506105c58061001c5f395ff3fe608060405234801561000f575f5ffd5b5060043610610060575f3560e01c80630c6ff4f21461006457806377db2264146100825780637d6d037f146100ef578063b1c551ca14610104578063dd4b1db014610144578063f5c7df5514610165575b5f5ffd5b61006c610178565b60405161007991906104ca565b60405180910390f35b6100c561009036600461050c565b5f602081905290815260409020805460018201546002909201546001600160a01b03821692600160a01b90920460ff16919084565b604080516001600160a01b0390951685529215156020850152918301526060820152608001610079565b6101026100fd366004610523565b6102c9565b005b61012c61011236600461050c565b5f908152602081905260409020546001600160a01b031690565b6040516001600160a01b039091168152602001610079565b61015761015236600461050c565b610375565b604051908152602001610079565b61010261017336600461050c565b610394565b60605f805b6001548110156101dd575f5f6001838154811061019c5761019c610543565b905f5260205f20015481526020019081526020015f205f0160149054906101000a900460ff16156101d557816101d181610557565b9250505b60010161017d565b505f8167ffffffffffffffff8111156101f8576101f861057b565b604051908082528060200260200182016040528015610221578160200160208202803683370190505b5090505f805b6001548110156102c0575f5f6001838154811061024657610246610543565b905f5260205f20015481526020019081526020015f205f0160149054906101000a900460ff16156102b8576001818154811061028457610284610543565b905f5260205f20015483838151811061029f5761029f610543565b6020908102919091010152816102b481610557565b9250505b600101610227565b50909392505050565b5f828152602081905260409020546001600160a01b031633146103225760405162461bcd60e51b815260206004820152600c60248201526b155b985d5d1a1bdc9a5e995960a21b60448201526064015b60405180910390fd5b5f82815260208181526040918290206002810184905542600190910155905182815283917f366756acc25b5ed18edefcc714be22bcca6abd57003678dcc4f01e7150f35985910160405180910390a25050565b60018181548110610384575f80fd5b5f91825260209091200154905081565b5f818152602081905260409020546001600160a01b0316156103f85760405162461bcd60e51b815260206004820152601c60248201527f536174656c6c69746520616c72656164792072656769737465726564000000006044820152606401610319565b604080516080810182523380825260016020808401828152428587019081525f606087018181528982528185528882209751885494511515600160a01b026001600160a81b03199095166001600160a01b0390911617939093178755905186850155905160029095019490945581548083018355919093527fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf601849055915191825282917fb37942e9232492780e2af1157219dfd1ca7fad2d23b0ad30ad6fce5bf83ec19b910160405180910390a250565b602080825282518282018190525f918401906040840190835b818110156105015783518352602093840193909201916001016104e3565b509095945050505050565b5f6020828403121561051c575f5ffd5b5035919050565b5f5f60408385031215610534575f5ffd5b50508035926020909101359150565b634e487b7160e01b5f52603260045260245ffd5b5f6001820161057457634e487b7160e01b5f52601160045260245ffd5b5060010190565b634e487b7160e01b5f52604160045260245ffdfea264697066735822122048667416ba03e8b6d8bd3b06daec9116f929e082bae825b757b69e7891449a8c64736f6c634300081d0033",
}

// SatelliteABI is the input ABI used to generate the binding from.
// Deprecated: Use SatelliteMetaData.ABI instead.
var SatelliteABI = SatelliteMetaData.ABI

// SatelliteBin is the compiled bytecode used for deploying new contracts.
// Deprecated: Use SatelliteMetaData.Bin instead.
var SatelliteBin = SatelliteMetaData.Bin

// DeploySatellite deploys a new Ethereum contract, binding an instance of Satellite to it.
func DeploySatellite(auth *bind.TransactOpts, backend bind.ContractBackend) (common.Address, *types.Transaction, *Satellite, error) {
	parsed, err := SatelliteMetaData.GetAbi()
	if err != nil {
		return common.Address{}, nil, nil, err
	}
	if parsed == nil {
		return common.Address{}, nil, nil, errors.New("GetABI returned nil")
	}

	address, tx, contract, err := bind.DeployContract(auth, *parsed, common.FromHex(SatelliteBin), backend)
	if err != nil {
		return common.Address{}, nil, nil, err
	}
	return address, tx, &Satellite{SatelliteCaller: SatelliteCaller{contract: contract}, SatelliteTransactor: SatelliteTransactor{contract: contract}, SatelliteFilterer: SatelliteFilterer{contract: contract}}, nil
}

// Satellite is an auto generated Go binding around an Ethereum contract.
type Satellite struct {
	SatelliteCaller     // Read-only binding to the contract
	SatelliteTransactor // Write-only binding to the contract
	SatelliteFilterer   // Log filterer for contract events
}

// SatelliteCaller is an auto generated read-only Go binding around an Ethereum contract.
type SatelliteCaller struct {
	contract *bind.BoundContract // Generic contract wrapper for the low level calls
}

// SatelliteTransactor is an auto generated write-only Go binding around an Ethereum contract.
type SatelliteTransactor struct {
	contract *bind.BoundContract // Generic contract wrapper for the low level calls
}

// SatelliteFilterer is an auto generated log filtering Go binding around an Ethereum contract events.
type SatelliteFilterer struct {
	contract *bind.BoundContract // Generic contract wrapper for the low level calls
}

// SatelliteSession is an auto generated Go binding around an Ethereum contract,
// with pre-set call and transact options.
type SatelliteSession struct {
	Contract     *Satellite        // Generic contract binding to set the session for
	CallOpts     bind.CallOpts     // Call options to use throughout this session
	TransactOpts bind.TransactOpts // Transaction auth options to use throughout this session
}

// SatelliteCallerSession is an auto generated read-only Go binding around an Ethereum contract,
// with pre-set call options.
type SatelliteCallerSession struct {
	Contract *SatelliteCaller // Generic contract caller binding to set the session for
	CallOpts bind.CallOpts    // Call options to use throughout this session
}

// SatelliteTransactorSession is an auto generated write-only Go binding around an Ethereum contract,
// with pre-set transact options.
type SatelliteTransactorSession struct {
	Contract     *SatelliteTransactor // Generic contract transactor binding to set the session for
	TransactOpts bind.TransactOpts    // Transaction auth options to use throughout this session
}

// SatelliteRaw is an auto generated low-level Go binding around an Ethereum contract.
type SatelliteRaw struct {
	Contract *Satellite // Generic contract binding to access the raw methods on
}

// SatelliteCallerRaw is an auto generated low-level read-only Go binding around an Ethereum contract.
type SatelliteCallerRaw struct {
	Contract *SatelliteCaller // Generic read-only contract binding to access the raw methods on
}

// SatelliteTransactorRaw is an auto generated low-level write-only Go binding around an Ethereum contract.
type SatelliteTransactorRaw struct {
	Contract *SatelliteTransactor // Generic write-only contract binding to access the raw methods on
}

// NewSatellite creates a new instance of Satellite, bound to a specific deployed contract.
func NewSatellite(address common.Address, backend bind.ContractBackend) (*Satellite, error) {
	contract, err := bindSatellite(address, backend, backend, backend)
	if err != nil {
		return nil, err
	}
	return &Satellite{SatelliteCaller: SatelliteCaller{contract: contract}, SatelliteTransactor: SatelliteTransactor{contract: contract}, SatelliteFilterer: SatelliteFilterer{contract: contract}}, nil
}

// NewSatelliteCaller creates a new read-only instance of Satellite, bound to a specific deployed contract.
func NewSatelliteCaller(address common.Address, caller bind.ContractCaller) (*SatelliteCaller, error) {
	contract, err := bindSatellite(address, caller, nil, nil)
	if err != nil {
		return nil, err
	}
	return &SatelliteCaller{contract: contract}, nil
}

// NewSatelliteTransactor creates a new write-only instance of Satellite, bound to a specific deployed contract.
func NewSatelliteTransactor(address common.Address, transactor bind.ContractTransactor) (*SatelliteTransactor, error) {
	contract, err := bindSatellite(address, nil, transactor, nil)
	if err != nil {
		return nil, err
	}
	return &SatelliteTransactor{contract: contract}, nil
}

// NewSatelliteFilterer creates a new log filterer instance of Satellite, bound to a specific deployed contract.
func NewSatelliteFilterer(address common.Address, filterer bind.ContractFilterer) (*SatelliteFilterer, error) {
	contract, err := bindSatellite(address, nil, nil, filterer)
	if err != nil {
		return nil, err
	}
	return &SatelliteFilterer{contract: contract}, nil
}

// bindSatellite binds a generic wrapper to an already deployed contract.
func bindSatellite(address common.Address, caller bind.ContractCaller, transactor bind.ContractTransactor, filterer bind.ContractFilterer) (*bind.BoundContract, error) {
	parsed, err := SatelliteMetaData.GetAbi()
	if err != nil {
		return nil, err
	}
	return bind.NewBoundContract(address, *parsed, caller, transactor, filterer), nil
}

// Call invokes the (constant) contract method with params as input values and
// sets the output to result. The result type might be a single field for simple
// returns, a slice of interfaces for anonymous returns and a struct for named
// returns.
func (_Satellite *SatelliteRaw) Call(opts *bind.CallOpts, result *[]interface{}, method string, params ...interface{}) error {
	return _Satellite.Contract.SatelliteCaller.contract.Call(opts, result, method, params...)
}

// Transfer initiates a plain transaction to move funds to the contract, calling
// its default method if one is available.
func (_Satellite *SatelliteRaw) Transfer(opts *bind.TransactOpts) (*types.Transaction, error) {
	return _Satellite.Contract.SatelliteTransactor.contract.Transfer(opts)
}

// Transact invokes the (paid) contract method with params as input values.
func (_Satellite *SatelliteRaw) Transact(opts *bind.TransactOpts, method string, params ...interface{}) (*types.Transaction, error) {
	return _Satellite.Contract.SatelliteTransactor.contract.Transact(opts, method, params...)
}

// Call invokes the (constant) contract method with params as input values and
// sets the output to result. The result type might be a single field for simple
// returns, a slice of interfaces for anonymous returns and a struct for named
// returns.
func (_Satellite *SatelliteCallerRaw) Call(opts *bind.CallOpts, result *[]interface{}, method string, params ...interface{}) error {
	return _Satellite.Contract.contract.Call(opts, result, method, params...)
}

// Transfer initiates a plain transaction to move funds to the contract, calling
// its default method if one is available.
func (_Satellite *SatelliteTransactorRaw) Transfer(opts *bind.TransactOpts) (*types.Transaction, error) {
	return _Satellite.Contract.contract.Transfer(opts)
}

// Transact invokes the (paid) contract method with params as input values.
func (_Satellite *SatelliteTransactorRaw) Transact(opts *bind.TransactOpts, method string, params ...interface{}) (*types.Transaction, error) {
	return _Satellite.Contract.contract.Transact(opts, method, params...)
}

// GetActiveSatellites is a free data retrieval call binding the contract method 0x0c6ff4f2.
//
// Solidity: function getActiveSatellites() view returns(bytes32[])
func (_Satellite *SatelliteCaller) GetActiveSatellites(opts *bind.CallOpts) ([][32]byte, error) {
	var out []interface{}
	err := _Satellite.contract.Call(opts, &out, "getActiveSatellites")

	if err != nil {
		return *new([][32]byte), err
	}

	out0 := *abi.ConvertType(out[0], new([][32]byte)).(*[][32]byte)

	return out0, err

}

// GetActiveSatellites is a free data retrieval call binding the contract method 0x0c6ff4f2.
//
// Solidity: function getActiveSatellites() view returns(bytes32[])
func (_Satellite *SatelliteSession) GetActiveSatellites() ([][32]byte, error) {
	return _Satellite.Contract.GetActiveSatellites(&_Satellite.CallOpts)
}

// GetActiveSatellites is a free data retrieval call binding the contract method 0x0c6ff4f2.
//
// Solidity: function getActiveSatellites() view returns(bytes32[])
func (_Satellite *SatelliteCallerSession) GetActiveSatellites() ([][32]byte, error) {
	return _Satellite.Contract.GetActiveSatellites(&_Satellite.CallOpts)
}

// GetPublicKey is a free data retrieval call binding the contract method 0xb1c551ca.
//
// Solidity: function getPublicKey(bytes32 satelliteId) view returns(address)
func (_Satellite *SatelliteCaller) GetPublicKey(opts *bind.CallOpts, satelliteId [32]byte) (common.Address, error) {
	var out []interface{}
	err := _Satellite.contract.Call(opts, &out, "getPublicKey", satelliteId)

	if err != nil {
		return *new(common.Address), err
	}

	out0 := *abi.ConvertType(out[0], new(common.Address)).(*common.Address)

	return out0, err

}

// GetPublicKey is a free data retrieval call binding the contract method 0xb1c551ca.
//
// Solidity: function getPublicKey(bytes32 satelliteId) view returns(address)
func (_Satellite *SatelliteSession) GetPublicKey(satelliteId [32]byte) (common.Address, error) {
	return _Satellite.Contract.GetPublicKey(&_Satellite.CallOpts, satelliteId)
}

// GetPublicKey is a free data retrieval call binding the contract method 0xb1c551ca.
//
// Solidity: function getPublicKey(bytes32 satelliteId) view returns(address)
func (_Satellite *SatelliteCallerSession) GetPublicKey(satelliteId [32]byte) (common.Address, error) {
	return _Satellite.Contract.GetPublicKey(&_Satellite.CallOpts, satelliteId)
}

// SatelliteIds is a free data retrieval call binding the contract method 0xdd4b1db0.
//
// Solidity: function satelliteIds(uint256 ) view returns(bytes32)
func (_Satellite *SatelliteCaller) SatelliteIds(opts *bind.CallOpts, arg0 *big.Int) ([32]byte, error) {
	var out []interface{}
	err := _Satellite.contract.Call(opts, &out, "satelliteIds", arg0)

	if err != nil {
		return *new([32]byte), err
	}

	out0 := *abi.ConvertType(out[0], new([32]byte)).(*[32]byte)

	return out0, err

}

// SatelliteIds is a free data retrieval call binding the contract method 0xdd4b1db0.
//
// Solidity: function satelliteIds(uint256 ) view returns(bytes32)
func (_Satellite *SatelliteSession) SatelliteIds(arg0 *big.Int) ([32]byte, error) {
	return _Satellite.Contract.SatelliteIds(&_Satellite.CallOpts, arg0)
}

// SatelliteIds is a free data retrieval call binding the contract method 0xdd4b1db0.
//
// Solidity: function satelliteIds(uint256 ) view returns(bytes32)
func (_Satellite *SatelliteCallerSession) SatelliteIds(arg0 *big.Int) ([32]byte, error) {
	return _Satellite.Contract.SatelliteIds(&_Satellite.CallOpts, arg0)
}

// Satellites is a free data retrieval call binding the contract method 0x77db2264.
//
// Solidity: function satellites(bytes32 ) view returns(address publicKey, bool active, uint256 lastPing, bytes32 currentPosition)
func (_Satellite *SatelliteCaller) Satellites(opts *bind.CallOpts, arg0 [32]byte) (struct {
	PublicKey       common.Address
	Active          bool
	LastPing        *big.Int
	CurrentPosition [32]byte
}, error) {
	var out []interface{}
	err := _Satellite.contract.Call(opts, &out, "satellites", arg0)

	outstruct := new(struct {
		PublicKey       common.Address
		Active          bool
		LastPing        *big.Int
		CurrentPosition [32]byte
	})
	if err != nil {
		return *outstruct, err
	}

	outstruct.PublicKey = *abi.ConvertType(out[0], new(common.Address)).(*common.Address)
	outstruct.Active = *abi.ConvertType(out[1], new(bool)).(*bool)
	outstruct.LastPing = *abi.ConvertType(out[2], new(*big.Int)).(**big.Int)
	outstruct.CurrentPosition = *abi.ConvertType(out[3], new([32]byte)).(*[32]byte)

	return *outstruct, err

}

// Satellites is a free data retrieval call binding the contract method 0x77db2264.
//
// Solidity: function satellites(bytes32 ) view returns(address publicKey, bool active, uint256 lastPing, bytes32 currentPosition)
func (_Satellite *SatelliteSession) Satellites(arg0 [32]byte) (struct {
	PublicKey       common.Address
	Active          bool
	LastPing        *big.Int
	CurrentPosition [32]byte
}, error) {
	return _Satellite.Contract.Satellites(&_Satellite.CallOpts, arg0)
}

// Satellites is a free data retrieval call binding the contract method 0x77db2264.
//
// Solidity: function satellites(bytes32 ) view returns(address publicKey, bool active, uint256 lastPing, bytes32 currentPosition)
func (_Satellite *SatelliteCallerSession) Satellites(arg0 [32]byte) (struct {
	PublicKey       common.Address
	Active          bool
	LastPing        *big.Int
	CurrentPosition [32]byte
}, error) {
	return _Satellite.Contract.Satellites(&_Satellite.CallOpts, arg0)
}

// RegisterSatellite is a paid mutator transaction binding the contract method 0xf5c7df55.
//
// Solidity: function registerSatellite(bytes32 satelliteId) returns()
func (_Satellite *SatelliteTransactor) RegisterSatellite(opts *bind.TransactOpts, satelliteId [32]byte) (*types.Transaction, error) {
	return _Satellite.contract.Transact(opts, "registerSatellite", satelliteId)
}

// RegisterSatellite is a paid mutator transaction binding the contract method 0xf5c7df55.
//
// Solidity: function registerSatellite(bytes32 satelliteId) returns()
func (_Satellite *SatelliteSession) RegisterSatellite(satelliteId [32]byte) (*types.Transaction, error) {
	return _Satellite.Contract.RegisterSatellite(&_Satellite.TransactOpts, satelliteId)
}

// RegisterSatellite is a paid mutator transaction binding the contract method 0xf5c7df55.
//
// Solidity: function registerSatellite(bytes32 satelliteId) returns()
func (_Satellite *SatelliteTransactorSession) RegisterSatellite(satelliteId [32]byte) (*types.Transaction, error) {
	return _Satellite.Contract.RegisterSatellite(&_Satellite.TransactOpts, satelliteId)
}

// UpdatePosition is a paid mutator transaction binding the contract method 0x7d6d037f.
//
// Solidity: function updatePosition(bytes32 satelliteId, bytes32 position) returns()
func (_Satellite *SatelliteTransactor) UpdatePosition(opts *bind.TransactOpts, satelliteId [32]byte, position [32]byte) (*types.Transaction, error) {
	return _Satellite.contract.Transact(opts, "updatePosition", satelliteId, position)
}

// UpdatePosition is a paid mutator transaction binding the contract method 0x7d6d037f.
//
// Solidity: function updatePosition(bytes32 satelliteId, bytes32 position) returns()
func (_Satellite *SatelliteSession) UpdatePosition(satelliteId [32]byte, position [32]byte) (*types.Transaction, error) {
	return _Satellite.Contract.UpdatePosition(&_Satellite.TransactOpts, satelliteId, position)
}

// UpdatePosition is a paid mutator transaction binding the contract method 0x7d6d037f.
//
// Solidity: function updatePosition(bytes32 satelliteId, bytes32 position) returns()
func (_Satellite *SatelliteTransactorSession) UpdatePosition(satelliteId [32]byte, position [32]byte) (*types.Transaction, error) {
	return _Satellite.Contract.UpdatePosition(&_Satellite.TransactOpts, satelliteId, position)
}

// SatellitePositionUpdatedIterator is returned from FilterPositionUpdated and is used to iterate over the raw logs and unpacked data for PositionUpdated events raised by the Satellite contract.
type SatellitePositionUpdatedIterator struct {
	Event *SatellitePositionUpdated // Event containing the contract specifics and raw log

	contract *bind.BoundContract // Generic contract to use for unpacking event data
	event    string              // Event name to use for unpacking event data

	logs chan types.Log        // Log channel receiving the found contract events
	sub  ethereum.Subscription // Subscription for errors, completion and termination
	done bool                  // Whether the subscription completed delivering logs
	fail error                 // Occurred error to stop iteration
}

// Next advances the iterator to the subsequent event, returning whether there
// are any more events found. In case of a retrieval or parsing error, false is
// returned and Error() can be queried for the exact failure.
func (it *SatellitePositionUpdatedIterator) Next() bool {
	// If the iterator failed, stop iterating
	if it.fail != nil {
		return false
	}
	// If the iterator completed, deliver directly whatever's available
	if it.done {
		select {
		case log := <-it.logs:
			it.Event = new(SatellitePositionUpdated)
			if err := it.contract.UnpackLog(it.Event, it.event, log); err != nil {
				it.fail = err
				return false
			}
			it.Event.Raw = log
			return true

		default:
			return false
		}
	}
	// Iterator still in progress, wait for either a data or an error event
	select {
	case log := <-it.logs:
		it.Event = new(SatellitePositionUpdated)
		if err := it.contract.UnpackLog(it.Event, it.event, log); err != nil {
			it.fail = err
			return false
		}
		it.Event.Raw = log
		return true

	case err := <-it.sub.Err():
		it.done = true
		it.fail = err
		return it.Next()
	}
}

// Error returns any retrieval or parsing error occurred during filtering.
func (it *SatellitePositionUpdatedIterator) Error() error {
	return it.fail
}

// Close terminates the iteration process, releasing any pending underlying
// resources.
func (it *SatellitePositionUpdatedIterator) Close() error {
	it.sub.Unsubscribe()
	return nil
}

// SatellitePositionUpdated represents a PositionUpdated event raised by the Satellite contract.
type SatellitePositionUpdated struct {
	SatelliteId [32]byte
	Position    [32]byte
	Raw         types.Log // Blockchain specific contextual infos
}

// FilterPositionUpdated is a free log retrieval operation binding the contract event 0x366756acc25b5ed18edefcc714be22bcca6abd57003678dcc4f01e7150f35985.
//
// Solidity: event PositionUpdated(bytes32 indexed satelliteId, bytes32 position)
func (_Satellite *SatelliteFilterer) FilterPositionUpdated(opts *bind.FilterOpts, satelliteId [][32]byte) (*SatellitePositionUpdatedIterator, error) {

	var satelliteIdRule []interface{}
	for _, satelliteIdItem := range satelliteId {
		satelliteIdRule = append(satelliteIdRule, satelliteIdItem)
	}

	logs, sub, err := _Satellite.contract.FilterLogs(opts, "PositionUpdated", satelliteIdRule)
	if err != nil {
		return nil, err
	}
	return &SatellitePositionUpdatedIterator{contract: _Satellite.contract, event: "PositionUpdated", logs: logs, sub: sub}, nil
}

// WatchPositionUpdated is a free log subscription operation binding the contract event 0x366756acc25b5ed18edefcc714be22bcca6abd57003678dcc4f01e7150f35985.
//
// Solidity: event PositionUpdated(bytes32 indexed satelliteId, bytes32 position)
func (_Satellite *SatelliteFilterer) WatchPositionUpdated(opts *bind.WatchOpts, sink chan<- *SatellitePositionUpdated, satelliteId [][32]byte) (event.Subscription, error) {

	var satelliteIdRule []interface{}
	for _, satelliteIdItem := range satelliteId {
		satelliteIdRule = append(satelliteIdRule, satelliteIdItem)
	}

	logs, sub, err := _Satellite.contract.WatchLogs(opts, "PositionUpdated", satelliteIdRule)
	if err != nil {
		return nil, err
	}
	return event.NewSubscription(func(quit <-chan struct{}) error {
		defer sub.Unsubscribe()
		for {
			select {
			case log := <-logs:
				// New log arrived, parse the event and forward to the user
				event := new(SatellitePositionUpdated)
				if err := _Satellite.contract.UnpackLog(event, "PositionUpdated", log); err != nil {
					return err
				}
				event.Raw = log

				select {
				case sink <- event:
				case err := <-sub.Err():
					return err
				case <-quit:
					return nil
				}
			case err := <-sub.Err():
				return err
			case <-quit:
				return nil
			}
		}
	}), nil
}

// ParsePositionUpdated is a log parse operation binding the contract event 0x366756acc25b5ed18edefcc714be22bcca6abd57003678dcc4f01e7150f35985.
//
// Solidity: event PositionUpdated(bytes32 indexed satelliteId, bytes32 position)
func (_Satellite *SatelliteFilterer) ParsePositionUpdated(log types.Log) (*SatellitePositionUpdated, error) {
	event := new(SatellitePositionUpdated)
	if err := _Satellite.contract.UnpackLog(event, "PositionUpdated", log); err != nil {
		return nil, err
	}
	event.Raw = log
	return event, nil
}

// SatelliteSatelliteRegisteredIterator is returned from FilterSatelliteRegistered and is used to iterate over the raw logs and unpacked data for SatelliteRegistered events raised by the Satellite contract.
type SatelliteSatelliteRegisteredIterator struct {
	Event *SatelliteSatelliteRegistered // Event containing the contract specifics and raw log

	contract *bind.BoundContract // Generic contract to use for unpacking event data
	event    string              // Event name to use for unpacking event data

	logs chan types.Log        // Log channel receiving the found contract events
	sub  ethereum.Subscription // Subscription for errors, completion and termination
	done bool                  // Whether the subscription completed delivering logs
	fail error                 // Occurred error to stop iteration
}

// Next advances the iterator to the subsequent event, returning whether there
// are any more events found. In case of a retrieval or parsing error, false is
// returned and Error() can be queried for the exact failure.
func (it *SatelliteSatelliteRegisteredIterator) Next() bool {
	// If the iterator failed, stop iterating
	if it.fail != nil {
		return false
	}
	// If the iterator completed, deliver directly whatever's available
	if it.done {
		select {
		case log := <-it.logs:
			it.Event = new(SatelliteSatelliteRegistered)
			if err := it.contract.UnpackLog(it.Event, it.event, log); err != nil {
				it.fail = err
				return false
			}
			it.Event.Raw = log
			return true

		default:
			return false
		}
	}
	// Iterator still in progress, wait for either a data or an error event
	select {
	case log := <-it.logs:
		it.Event = new(SatelliteSatelliteRegistered)
		if err := it.contract.UnpackLog(it.Event, it.event, log); err != nil {
			it.fail = err
			return false
		}
		it.Event.Raw = log
		return true

	case err := <-it.sub.Err():
		it.done = true
		it.fail = err
		return it.Next()
	}
}

// Error returns any retrieval or parsing error occurred during filtering.
func (it *SatelliteSatelliteRegisteredIterator) Error() error {
	return it.fail
}

// Close terminates the iteration process, releasing any pending underlying
// resources.
func (it *SatelliteSatelliteRegisteredIterator) Close() error {
	it.sub.Unsubscribe()
	return nil
}

// SatelliteSatelliteRegistered represents a SatelliteRegistered event raised by the Satellite contract.
type SatelliteSatelliteRegistered struct {
	SatelliteId [32]byte
	PublicKey   common.Address
	Raw         types.Log // Blockchain specific contextual infos
}

// FilterSatelliteRegistered is a free log retrieval operation binding the contract event 0xb37942e9232492780e2af1157219dfd1ca7fad2d23b0ad30ad6fce5bf83ec19b.
//
// Solidity: event SatelliteRegistered(bytes32 indexed satelliteId, address publicKey)
func (_Satellite *SatelliteFilterer) FilterSatelliteRegistered(opts *bind.FilterOpts, satelliteId [][32]byte) (*SatelliteSatelliteRegisteredIterator, error) {

	var satelliteIdRule []interface{}
	for _, satelliteIdItem := range satelliteId {
		satelliteIdRule = append(satelliteIdRule, satelliteIdItem)
	}

	logs, sub, err := _Satellite.contract.FilterLogs(opts, "SatelliteRegistered", satelliteIdRule)
	if err != nil {
		return nil, err
	}
	return &SatelliteSatelliteRegisteredIterator{contract: _Satellite.contract, event: "SatelliteRegistered", logs: logs, sub: sub}, nil
}

// WatchSatelliteRegistered is a free log subscription operation binding the contract event 0xb37942e9232492780e2af1157219dfd1ca7fad2d23b0ad30ad6fce5bf83ec19b.
//
// Solidity: event SatelliteRegistered(bytes32 indexed satelliteId, address publicKey)
func (_Satellite *SatelliteFilterer) WatchSatelliteRegistered(opts *bind.WatchOpts, sink chan<- *SatelliteSatelliteRegistered, satelliteId [][32]byte) (event.Subscription, error) {

	var satelliteIdRule []interface{}
	for _, satelliteIdItem := range satelliteId {
		satelliteIdRule = append(satelliteIdRule, satelliteIdItem)
	}

	logs, sub, err := _Satellite.contract.WatchLogs(opts, "SatelliteRegistered", satelliteIdRule)
	if err != nil {
		return nil, err
	}
	return event.NewSubscription(func(quit <-chan struct{}) error {
		defer sub.Unsubscribe()
		for {
			select {
			case log := <-logs:
				// New log arrived, parse the event and forward to the user
				event := new(SatelliteSatelliteRegistered)
				if err := _Satellite.contract.UnpackLog(event, "SatelliteRegistered", log); err != nil {
					return err
				}
				event.Raw = log

				select {
				case sink <- event:
				case err := <-sub.Err():
					return err
				case <-quit:
					return nil
				}
			case err := <-sub.Err():
				return err
			case <-quit:
				return nil
			}
		}
	}), nil
}

// ParseSatelliteRegistered is a log parse operation binding the contract event 0xb37942e9232492780e2af1157219dfd1ca7fad2d23b0ad30ad6fce5bf83ec19b.
//
// Solidity: event SatelliteRegistered(bytes32 indexed satelliteId, address publicKey)
func (_Satellite *SatelliteFilterer) ParseSatelliteRegistered(log types.Log) (*SatelliteSatelliteRegistered, error) {
	event := new(SatelliteSatelliteRegistered)
	if err := _Satellite.contract.UnpackLog(event, "SatelliteRegistered", log); err != nil {
		return nil, err
	}
	event.Raw = log
	return event, nil
}

// SatelliteSatelliteStatusChangedIterator is returned from FilterSatelliteStatusChanged and is used to iterate over the raw logs and unpacked data for SatelliteStatusChanged events raised by the Satellite contract.
type SatelliteSatelliteStatusChangedIterator struct {
	Event *SatelliteSatelliteStatusChanged // Event containing the contract specifics and raw log

	contract *bind.BoundContract // Generic contract to use for unpacking event data
	event    string              // Event name to use for unpacking event data

	logs chan types.Log        // Log channel receiving the found contract events
	sub  ethereum.Subscription // Subscription for errors, completion and termination
	done bool                  // Whether the subscription completed delivering logs
	fail error                 // Occurred error to stop iteration
}

// Next advances the iterator to the subsequent event, returning whether there
// are any more events found. In case of a retrieval or parsing error, false is
// returned and Error() can be queried for the exact failure.
func (it *SatelliteSatelliteStatusChangedIterator) Next() bool {
	// If the iterator failed, stop iterating
	if it.fail != nil {
		return false
	}
	// If the iterator completed, deliver directly whatever's available
	if it.done {
		select {
		case log := <-it.logs:
			it.Event = new(SatelliteSatelliteStatusChanged)
			if err := it.contract.UnpackLog(it.Event, it.event, log); err != nil {
				it.fail = err
				return false
			}
			it.Event.Raw = log
			return true

		default:
			return false
		}
	}
	// Iterator still in progress, wait for either a data or an error event
	select {
	case log := <-it.logs:
		it.Event = new(SatelliteSatelliteStatusChanged)
		if err := it.contract.UnpackLog(it.Event, it.event, log); err != nil {
			it.fail = err
			return false
		}
		it.Event.Raw = log
		return true

	case err := <-it.sub.Err():
		it.done = true
		it.fail = err
		return it.Next()
	}
}

// Error returns any retrieval or parsing error occurred during filtering.
func (it *SatelliteSatelliteStatusChangedIterator) Error() error {
	return it.fail
}

// Close terminates the iteration process, releasing any pending underlying
// resources.
func (it *SatelliteSatelliteStatusChangedIterator) Close() error {
	it.sub.Unsubscribe()
	return nil
}

// SatelliteSatelliteStatusChanged represents a SatelliteStatusChanged event raised by the Satellite contract.
type SatelliteSatelliteStatusChanged struct {
	SatelliteId [32]byte
	Active      bool
	Raw         types.Log // Blockchain specific contextual infos
}

// FilterSatelliteStatusChanged is a free log retrieval operation binding the contract event 0x73fda5115c2fd1b9c9bfbd9aa54ef717402a3dad02e7d887df3f6979223f7372.
//
// Solidity: event SatelliteStatusChanged(bytes32 indexed satelliteId, bool active)
func (_Satellite *SatelliteFilterer) FilterSatelliteStatusChanged(opts *bind.FilterOpts, satelliteId [][32]byte) (*SatelliteSatelliteStatusChangedIterator, error) {

	var satelliteIdRule []interface{}
	for _, satelliteIdItem := range satelliteId {
		satelliteIdRule = append(satelliteIdRule, satelliteIdItem)
	}

	logs, sub, err := _Satellite.contract.FilterLogs(opts, "SatelliteStatusChanged", satelliteIdRule)
	if err != nil {
		return nil, err
	}
	return &SatelliteSatelliteStatusChangedIterator{contract: _Satellite.contract, event: "SatelliteStatusChanged", logs: logs, sub: sub}, nil
}

// WatchSatelliteStatusChanged is a free log subscription operation binding the contract event 0x73fda5115c2fd1b9c9bfbd9aa54ef717402a3dad02e7d887df3f6979223f7372.
//
// Solidity: event SatelliteStatusChanged(bytes32 indexed satelliteId, bool active)
func (_Satellite *SatelliteFilterer) WatchSatelliteStatusChanged(opts *bind.WatchOpts, sink chan<- *SatelliteSatelliteStatusChanged, satelliteId [][32]byte) (event.Subscription, error) {

	var satelliteIdRule []interface{}
	for _, satelliteIdItem := range satelliteId {
		satelliteIdRule = append(satelliteIdRule, satelliteIdItem)
	}

	logs, sub, err := _Satellite.contract.WatchLogs(opts, "SatelliteStatusChanged", satelliteIdRule)
	if err != nil {
		return nil, err
	}
	return event.NewSubscription(func(quit <-chan struct{}) error {
		defer sub.Unsubscribe()
		for {
			select {
			case log := <-logs:
				// New log arrived, parse the event and forward to the user
				event := new(SatelliteSatelliteStatusChanged)
				if err := _Satellite.contract.UnpackLog(event, "SatelliteStatusChanged", log); err != nil {
					return err
				}
				event.Raw = log

				select {
				case sink <- event:
				case err := <-sub.Err():
					return err
				case <-quit:
					return nil
				}
			case err := <-sub.Err():
				return err
			case <-quit:
				return nil
			}
		}
	}), nil
}

// ParseSatelliteStatusChanged is a log parse operation binding the contract event 0x73fda5115c2fd1b9c9bfbd9aa54ef717402a3dad02e7d887df3f6979223f7372.
//
// Solidity: event SatelliteStatusChanged(bytes32 indexed satelliteId, bool active)
func (_Satellite *SatelliteFilterer) ParseSatelliteStatusChanged(log types.Log) (*SatelliteSatelliteStatusChanged, error) {
	event := new(SatelliteSatelliteStatusChanged)
	if err := _Satellite.contract.UnpackLog(event, "SatelliteStatusChanged", log); err != nil {
		return nil, err
	}
	event.Raw = log
	return event, nil
}
