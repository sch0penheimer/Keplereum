// Code generated - DO NOT EDIT.
// This file is a generated binding and any manual changes will be lost.

package maneuver

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

// ManeuverMetaData contains all meta data concerning the Maneuver contract.
var ManeuverMetaData = &bind.MetaData{
	ABI: "[{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_alertSystemAddress\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"_registryAddress\",\"type\":\"address\"}],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"bytes32\",\"name\":\"maneuverHash\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"completionTime\",\"type\":\"uint256\"}],\"name\":\"ManeuverCompleted\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"bytes32\",\"name\":\"maneuverHash\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"satelliteId\",\"type\":\"bytes32\"}],\"name\":\"ManeuverExecuted\",\"type\":\"event\"},{\"inputs\":[],\"name\":\"alertSystem\",\"outputs\":[{\"internalType\":\"contractAlertSystem\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"maneuverHash\",\"type\":\"bytes32\"}],\"name\":\"completeManeuver\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"alertId\",\"type\":\"bytes32\"},{\"internalType\":\"bytes\",\"name\":\"parameters\",\"type\":\"bytes\"}],\"name\":\"executeManeuver\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"satelliteId\",\"type\":\"bytes32\"}],\"name\":\"getPendingManeuvers\",\"outputs\":[{\"internalType\":\"bytes32[]\",\"name\":\"\",\"type\":\"bytes32[]\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"maneuverHashes\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"name\":\"maneuvers\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"maneuverHash\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"alertId\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"satelliteId\",\"type\":\"bytes32\"},{\"internalType\":\"bytes\",\"name\":\"parameters\",\"type\":\"bytes\"},{\"internalType\":\"uint256\",\"name\":\"timestamp\",\"type\":\"uint256\"},{\"internalType\":\"bool\",\"name\":\"completed\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"satelliteRegistry\",\"outputs\":[{\"internalType\":\"contractSatelliteRegistry\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]",
	Bin: "0x6080604052348015600e575f5ffd5b50604051610d2f380380610d2f833981016040819052602b916075565b600280546001600160a01b039384166001600160a01b0319918216179091556003805492909316911617905560a1565b80516001600160a01b03811681146070575f5ffd5b919050565b5f5f604083850312156085575f5ffd5b608c83605b565b9150609860208401605b565b90509250929050565b610c81806100ae5f395ff3fe608060405234801561000f575f5ffd5b506004361061007a575f3560e01c8063a225d22d11610058578063a225d22d146100cf578063af773265146100f4578063c5e060b41461011f578063f73669b114610132575f5ffd5b80631d2836dc1461007e57806377b710bb1461009357806386b6c805146100bc575b5f5ffd5b61009161008c366004610933565b610153565b005b6100a66100a13660046109aa565b610411565b6040516100b391906109c1565b60405180910390f35b6100916100ca3660046109aa565b6106ff565b6100e26100dd3660046109aa565b610854565b6040516100b396959493929190610a03565b600354610107906001600160a01b031681565b6040516001600160a01b0390911681526020016100b3565b600254610107906001600160a01b031681565b6101456101403660046109aa565b610914565b6040519081526020016100b3565b60025460405163592ccf9760e01b8152600481018590525f916001600160a01b03169063592ccf9790602401602060405180830381865afa15801561019a573d5f5f3e3d5ffd5b505050506040513d601f19601f820116820180604052508101906101be9190610a5d565b9050806102025760405162461bcd60e51b815260206004820152600d60248201526c125b9d985b1a5908185b195c9d609a1b60448201526064015b60405180910390fd5b60025460405163146137e760e11b8152600481018690526001600160a01b03909116906328c26fce90602401602060405180830381865afa158015610249573d5f5f3e3d5ffd5b505050506040513d601f19601f8201168201806040525081019061026d9190610a74565b6102af5760405162461bcd60e51b8152602060048201526013602482015272105b195c9d081b9bdd081d985b1a59185d1959606a1b60448201526064016101f9565b60408051602081018690529081018290524260608201525f906080016040516020818303038152906040528051906020012090506040518060c0016040528082815260200186815260200183815260200185858080601f0160208091040260200160405190810160405280939291908181526020018383808284375f92018290525093855250504260208085019190915260409384018390528583528281529183902084518155918401516001830155509082015160028201556060820151600382019061037d9082610b32565b506080820151600482015560a0909101516005909101805460ff19169115159190911790556001805480820182555f919091527fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf60181905560405182815281907f98be6538d049735d11d241251dfbafefa2b15401289a7f62b4f34f7b8db2a44d9060200160405180910390a25050505050565b60605f805b600154811015610550575f5f5f6001848154811061043657610436610bed565b905f5260205f20015481526020019081526020015f206040518060c00160405290815f8201548152602001600182015481526020016002820154815260200160038201805461048490610aae565b80601f01602080910402602001604051908101604052809291908181526020018280546104b090610aae565b80156104fb5780601f106104d2576101008083540402835291602001916104fb565b820191905f5260205f20905b8154815290600101906020018083116104de57829003601f168201915b50505091835250506004820154602082015260059091015460ff1615156040918201528101519091508514801561053457508060a00151155b15610547578261054381610c01565b9350505b50600101610416565b505f8167ffffffffffffffff81111561056b5761056b610a9a565b604051908082528060200260200182016040528015610594578160200160208202803683370190505b5090505f805b6001548110156106f5575f5f5f600184815481106105ba576105ba610bed565b905f5260205f20015481526020019081526020015f206040518060c00160405290815f8201548152602001600182015481526020016002820154815260200160038201805461060890610aae565b80601f016020809104026020016040519081016040528092919081815260200182805461063490610aae565b801561067f5780601f106106565761010080835404028352916020019161067f565b820191905f5260205f20905b81548152906001019060200180831161066257829003601f168201915b50505091835250506004820154602082015260059091015460ff161515604091820152810151909150871480156106b857508060a00151155b156106ec57805f01518484815181106106d3576106d3610bed565b6020908102919091010152826106e881610c01565b9350505b5060010161059a565b5090949350505050565b5f8181526020819052604090208054821461074f5760405162461bcd60e51b815260206004820152601060248201526f24b73b30b634b21036b0b732babb32b960811b60448201526064016101f9565b60028101546003546040516358e2a8e560e11b81526004810183905233916001600160a01b03169063b1c551ca90602401602060405180830381865afa15801561079b573d5f5f3e3d5ffd5b505050506040513d601f19601f820116820180604052508101906107bf9190610c25565b6001600160a01b0316146108045760405162461bcd60e51b815260206004820152600c60248201526b155b985d5d1a1bdc9a5e995960a21b60448201526064016101f9565b60058201805460ff1916600117905560405183907fd4108d1d7cd9152c9fa207749b576b4bce8ce9741a4e492c740f28c6849a3fdf906108479042815260200190565b60405180910390a2505050565b5f6020819052908152604090208054600182015460028301546003840180549394929391929161088390610aae565b80601f01602080910402602001604051908101604052809291908181526020018280546108af90610aae565b80156108fa5780601f106108d1576101008083540402835291602001916108fa565b820191905f5260205f20905b8154815290600101906020018083116108dd57829003601f168201915b50505050600483015460059093015491929160ff16905086565b60018181548110610923575f80fd5b5f91825260209091200154905081565b5f5f5f60408486031215610945575f5ffd5b83359250602084013567ffffffffffffffff811115610962575f5ffd5b8401601f81018613610972575f5ffd5b803567ffffffffffffffff811115610988575f5ffd5b866020828401011115610999575f5ffd5b939660209190910195509293505050565b5f602082840312156109ba575f5ffd5b5035919050565b602080825282518282018190525f918401906040840190835b818110156109f85783518352602093840193909201916001016109da565b509095945050505050565b86815285602082015284604082015260c060608201525f84518060c0840152806020870160e085015e5f60e0828501015260e0601f19601f83011684010191505083608083015282151560a0830152979650505050505050565b5f60208284031215610a6d575f5ffd5b5051919050565b5f60208284031215610a84575f5ffd5b81518015158114610a93575f5ffd5b9392505050565b634e487b7160e01b5f52604160045260245ffd5b600181811c90821680610ac257607f821691505b602082108103610ae057634e487b7160e01b5f52602260045260245ffd5b50919050565b601f821115610b2d57805f5260205f20601f840160051c81016020851015610b0b5750805b601f840160051c820191505b81811015610b2a575f8155600101610b17565b50505b505050565b815167ffffffffffffffff811115610b4c57610b4c610a9a565b610b6081610b5a8454610aae565b84610ae6565b6020601f821160018114610b92575f8315610b7b5750848201515b5f19600385901b1c1916600184901b178455610b2a565b5f84815260208120601f198516915b82811015610bc15787850151825560209485019460019092019101610ba1565b5084821015610bde57868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b634e487b7160e01b5f52603260045260245ffd5b5f60018201610c1e57634e487b7160e01b5f52601160045260245ffd5b5060010190565b5f60208284031215610c35575f5ffd5b81516001600160a01b0381168114610a93575f5ffdfea26469706673582212206b2e2726bc98100edc6317df7d380844f9057b13b62872d9fbe66dd31832282364736f6c634300081d0033",
}

// ManeuverABI is the input ABI used to generate the binding from.
// Deprecated: Use ManeuverMetaData.ABI instead.
var ManeuverABI = ManeuverMetaData.ABI

// ManeuverBin is the compiled bytecode used for deploying new contracts.
// Deprecated: Use ManeuverMetaData.Bin instead.
var ManeuverBin = ManeuverMetaData.Bin

// DeployManeuver deploys a new Ethereum contract, binding an instance of Maneuver to it.
func DeployManeuver(auth *bind.TransactOpts, backend bind.ContractBackend, _alertSystemAddress common.Address, _registryAddress common.Address) (common.Address, *types.Transaction, *Maneuver, error) {
	parsed, err := ManeuverMetaData.GetAbi()
	if err != nil {
		return common.Address{}, nil, nil, err
	}
	if parsed == nil {
		return common.Address{}, nil, nil, errors.New("GetABI returned nil")
	}

	address, tx, contract, err := bind.DeployContract(auth, *parsed, common.FromHex(ManeuverBin), backend, _alertSystemAddress, _registryAddress)
	if err != nil {
		return common.Address{}, nil, nil, err
	}
	return address, tx, &Maneuver{ManeuverCaller: ManeuverCaller{contract: contract}, ManeuverTransactor: ManeuverTransactor{contract: contract}, ManeuverFilterer: ManeuverFilterer{contract: contract}}, nil
}

// Maneuver is an auto generated Go binding around an Ethereum contract.
type Maneuver struct {
	ManeuverCaller     // Read-only binding to the contract
	ManeuverTransactor // Write-only binding to the contract
	ManeuverFilterer   // Log filterer for contract events
}

// ManeuverCaller is an auto generated read-only Go binding around an Ethereum contract.
type ManeuverCaller struct {
	contract *bind.BoundContract // Generic contract wrapper for the low level calls
}

// ManeuverTransactor is an auto generated write-only Go binding around an Ethereum contract.
type ManeuverTransactor struct {
	contract *bind.BoundContract // Generic contract wrapper for the low level calls
}

// ManeuverFilterer is an auto generated log filtering Go binding around an Ethereum contract events.
type ManeuverFilterer struct {
	contract *bind.BoundContract // Generic contract wrapper for the low level calls
}

// ManeuverSession is an auto generated Go binding around an Ethereum contract,
// with pre-set call and transact options.
type ManeuverSession struct {
	Contract     *Maneuver         // Generic contract binding to set the session for
	CallOpts     bind.CallOpts     // Call options to use throughout this session
	TransactOpts bind.TransactOpts // Transaction auth options to use throughout this session
}

// ManeuverCallerSession is an auto generated read-only Go binding around an Ethereum contract,
// with pre-set call options.
type ManeuverCallerSession struct {
	Contract *ManeuverCaller // Generic contract caller binding to set the session for
	CallOpts bind.CallOpts   // Call options to use throughout this session
}

// ManeuverTransactorSession is an auto generated write-only Go binding around an Ethereum contract,
// with pre-set transact options.
type ManeuverTransactorSession struct {
	Contract     *ManeuverTransactor // Generic contract transactor binding to set the session for
	TransactOpts bind.TransactOpts   // Transaction auth options to use throughout this session
}

// ManeuverRaw is an auto generated low-level Go binding around an Ethereum contract.
type ManeuverRaw struct {
	Contract *Maneuver // Generic contract binding to access the raw methods on
}

// ManeuverCallerRaw is an auto generated low-level read-only Go binding around an Ethereum contract.
type ManeuverCallerRaw struct {
	Contract *ManeuverCaller // Generic read-only contract binding to access the raw methods on
}

// ManeuverTransactorRaw is an auto generated low-level write-only Go binding around an Ethereum contract.
type ManeuverTransactorRaw struct {
	Contract *ManeuverTransactor // Generic write-only contract binding to access the raw methods on
}

// NewManeuver creates a new instance of Maneuver, bound to a specific deployed contract.
func NewManeuver(address common.Address, backend bind.ContractBackend) (*Maneuver, error) {
	contract, err := bindManeuver(address, backend, backend, backend)
	if err != nil {
		return nil, err
	}
	return &Maneuver{ManeuverCaller: ManeuverCaller{contract: contract}, ManeuverTransactor: ManeuverTransactor{contract: contract}, ManeuverFilterer: ManeuverFilterer{contract: contract}}, nil
}

// NewManeuverCaller creates a new read-only instance of Maneuver, bound to a specific deployed contract.
func NewManeuverCaller(address common.Address, caller bind.ContractCaller) (*ManeuverCaller, error) {
	contract, err := bindManeuver(address, caller, nil, nil)
	if err != nil {
		return nil, err
	}
	return &ManeuverCaller{contract: contract}, nil
}

// NewManeuverTransactor creates a new write-only instance of Maneuver, bound to a specific deployed contract.
func NewManeuverTransactor(address common.Address, transactor bind.ContractTransactor) (*ManeuverTransactor, error) {
	contract, err := bindManeuver(address, nil, transactor, nil)
	if err != nil {
		return nil, err
	}
	return &ManeuverTransactor{contract: contract}, nil
}

// NewManeuverFilterer creates a new log filterer instance of Maneuver, bound to a specific deployed contract.
func NewManeuverFilterer(address common.Address, filterer bind.ContractFilterer) (*ManeuverFilterer, error) {
	contract, err := bindManeuver(address, nil, nil, filterer)
	if err != nil {
		return nil, err
	}
	return &ManeuverFilterer{contract: contract}, nil
}

// bindManeuver binds a generic wrapper to an already deployed contract.
func bindManeuver(address common.Address, caller bind.ContractCaller, transactor bind.ContractTransactor, filterer bind.ContractFilterer) (*bind.BoundContract, error) {
	parsed, err := ManeuverMetaData.GetAbi()
	if err != nil {
		return nil, err
	}
	return bind.NewBoundContract(address, *parsed, caller, transactor, filterer), nil
}

// Call invokes the (constant) contract method with params as input values and
// sets the output to result. The result type might be a single field for simple
// returns, a slice of interfaces for anonymous returns and a struct for named
// returns.
func (_Maneuver *ManeuverRaw) Call(opts *bind.CallOpts, result *[]interface{}, method string, params ...interface{}) error {
	return _Maneuver.Contract.ManeuverCaller.contract.Call(opts, result, method, params...)
}

// Transfer initiates a plain transaction to move funds to the contract, calling
// its default method if one is available.
func (_Maneuver *ManeuverRaw) Transfer(opts *bind.TransactOpts) (*types.Transaction, error) {
	return _Maneuver.Contract.ManeuverTransactor.contract.Transfer(opts)
}

// Transact invokes the (paid) contract method with params as input values.
func (_Maneuver *ManeuverRaw) Transact(opts *bind.TransactOpts, method string, params ...interface{}) (*types.Transaction, error) {
	return _Maneuver.Contract.ManeuverTransactor.contract.Transact(opts, method, params...)
}

// Call invokes the (constant) contract method with params as input values and
// sets the output to result. The result type might be a single field for simple
// returns, a slice of interfaces for anonymous returns and a struct for named
// returns.
func (_Maneuver *ManeuverCallerRaw) Call(opts *bind.CallOpts, result *[]interface{}, method string, params ...interface{}) error {
	return _Maneuver.Contract.contract.Call(opts, result, method, params...)
}

// Transfer initiates a plain transaction to move funds to the contract, calling
// its default method if one is available.
func (_Maneuver *ManeuverTransactorRaw) Transfer(opts *bind.TransactOpts) (*types.Transaction, error) {
	return _Maneuver.Contract.contract.Transfer(opts)
}

// Transact invokes the (paid) contract method with params as input values.
func (_Maneuver *ManeuverTransactorRaw) Transact(opts *bind.TransactOpts, method string, params ...interface{}) (*types.Transaction, error) {
	return _Maneuver.Contract.contract.Transact(opts, method, params...)
}

// AlertSystem is a free data retrieval call binding the contract method 0xc5e060b4.
//
// Solidity: function alertSystem() view returns(address)
func (_Maneuver *ManeuverCaller) AlertSystem(opts *bind.CallOpts) (common.Address, error) {
	var out []interface{}
	err := _Maneuver.contract.Call(opts, &out, "alertSystem")

	if err != nil {
		return *new(common.Address), err
	}

	out0 := *abi.ConvertType(out[0], new(common.Address)).(*common.Address)

	return out0, err

}

// AlertSystem is a free data retrieval call binding the contract method 0xc5e060b4.
//
// Solidity: function alertSystem() view returns(address)
func (_Maneuver *ManeuverSession) AlertSystem() (common.Address, error) {
	return _Maneuver.Contract.AlertSystem(&_Maneuver.CallOpts)
}

// AlertSystem is a free data retrieval call binding the contract method 0xc5e060b4.
//
// Solidity: function alertSystem() view returns(address)
func (_Maneuver *ManeuverCallerSession) AlertSystem() (common.Address, error) {
	return _Maneuver.Contract.AlertSystem(&_Maneuver.CallOpts)
}

// GetPendingManeuvers is a free data retrieval call binding the contract method 0x77b710bb.
//
// Solidity: function getPendingManeuvers(bytes32 satelliteId) view returns(bytes32[])
func (_Maneuver *ManeuverCaller) GetPendingManeuvers(opts *bind.CallOpts, satelliteId [32]byte) ([][32]byte, error) {
	var out []interface{}
	err := _Maneuver.contract.Call(opts, &out, "getPendingManeuvers", satelliteId)

	if err != nil {
		return *new([][32]byte), err
	}

	out0 := *abi.ConvertType(out[0], new([][32]byte)).(*[][32]byte)

	return out0, err

}

// GetPendingManeuvers is a free data retrieval call binding the contract method 0x77b710bb.
//
// Solidity: function getPendingManeuvers(bytes32 satelliteId) view returns(bytes32[])
func (_Maneuver *ManeuverSession) GetPendingManeuvers(satelliteId [32]byte) ([][32]byte, error) {
	return _Maneuver.Contract.GetPendingManeuvers(&_Maneuver.CallOpts, satelliteId)
}

// GetPendingManeuvers is a free data retrieval call binding the contract method 0x77b710bb.
//
// Solidity: function getPendingManeuvers(bytes32 satelliteId) view returns(bytes32[])
func (_Maneuver *ManeuverCallerSession) GetPendingManeuvers(satelliteId [32]byte) ([][32]byte, error) {
	return _Maneuver.Contract.GetPendingManeuvers(&_Maneuver.CallOpts, satelliteId)
}

// ManeuverHashes is a free data retrieval call binding the contract method 0xf73669b1.
//
// Solidity: function maneuverHashes(uint256 ) view returns(bytes32)
func (_Maneuver *ManeuverCaller) ManeuverHashes(opts *bind.CallOpts, arg0 *big.Int) ([32]byte, error) {
	var out []interface{}
	err := _Maneuver.contract.Call(opts, &out, "maneuverHashes", arg0)

	if err != nil {
		return *new([32]byte), err
	}

	out0 := *abi.ConvertType(out[0], new([32]byte)).(*[32]byte)

	return out0, err

}

// ManeuverHashes is a free data retrieval call binding the contract method 0xf73669b1.
//
// Solidity: function maneuverHashes(uint256 ) view returns(bytes32)
func (_Maneuver *ManeuverSession) ManeuverHashes(arg0 *big.Int) ([32]byte, error) {
	return _Maneuver.Contract.ManeuverHashes(&_Maneuver.CallOpts, arg0)
}

// ManeuverHashes is a free data retrieval call binding the contract method 0xf73669b1.
//
// Solidity: function maneuverHashes(uint256 ) view returns(bytes32)
func (_Maneuver *ManeuverCallerSession) ManeuverHashes(arg0 *big.Int) ([32]byte, error) {
	return _Maneuver.Contract.ManeuverHashes(&_Maneuver.CallOpts, arg0)
}

// Maneuvers is a free data retrieval call binding the contract method 0xa225d22d.
//
// Solidity: function maneuvers(bytes32 ) view returns(bytes32 maneuverHash, bytes32 alertId, bytes32 satelliteId, bytes parameters, uint256 timestamp, bool completed)
func (_Maneuver *ManeuverCaller) Maneuvers(opts *bind.CallOpts, arg0 [32]byte) (struct {
	ManeuverHash [32]byte
	AlertId      [32]byte
	SatelliteId  [32]byte
	Parameters   []byte
	Timestamp    *big.Int
	Completed    bool
}, error) {
	var out []interface{}
	err := _Maneuver.contract.Call(opts, &out, "maneuvers", arg0)

	outstruct := new(struct {
		ManeuverHash [32]byte
		AlertId      [32]byte
		SatelliteId  [32]byte
		Parameters   []byte
		Timestamp    *big.Int
		Completed    bool
	})
	if err != nil {
		return *outstruct, err
	}

	outstruct.ManeuverHash = *abi.ConvertType(out[0], new([32]byte)).(*[32]byte)
	outstruct.AlertId = *abi.ConvertType(out[1], new([32]byte)).(*[32]byte)
	outstruct.SatelliteId = *abi.ConvertType(out[2], new([32]byte)).(*[32]byte)
	outstruct.Parameters = *abi.ConvertType(out[3], new([]byte)).(*[]byte)
	outstruct.Timestamp = *abi.ConvertType(out[4], new(*big.Int)).(**big.Int)
	outstruct.Completed = *abi.ConvertType(out[5], new(bool)).(*bool)

	return *outstruct, err

}

// Maneuvers is a free data retrieval call binding the contract method 0xa225d22d.
//
// Solidity: function maneuvers(bytes32 ) view returns(bytes32 maneuverHash, bytes32 alertId, bytes32 satelliteId, bytes parameters, uint256 timestamp, bool completed)
func (_Maneuver *ManeuverSession) Maneuvers(arg0 [32]byte) (struct {
	ManeuverHash [32]byte
	AlertId      [32]byte
	SatelliteId  [32]byte
	Parameters   []byte
	Timestamp    *big.Int
	Completed    bool
}, error) {
	return _Maneuver.Contract.Maneuvers(&_Maneuver.CallOpts, arg0)
}

// Maneuvers is a free data retrieval call binding the contract method 0xa225d22d.
//
// Solidity: function maneuvers(bytes32 ) view returns(bytes32 maneuverHash, bytes32 alertId, bytes32 satelliteId, bytes parameters, uint256 timestamp, bool completed)
func (_Maneuver *ManeuverCallerSession) Maneuvers(arg0 [32]byte) (struct {
	ManeuverHash [32]byte
	AlertId      [32]byte
	SatelliteId  [32]byte
	Parameters   []byte
	Timestamp    *big.Int
	Completed    bool
}, error) {
	return _Maneuver.Contract.Maneuvers(&_Maneuver.CallOpts, arg0)
}

// SatelliteRegistry is a free data retrieval call binding the contract method 0xaf773265.
//
// Solidity: function satelliteRegistry() view returns(address)
func (_Maneuver *ManeuverCaller) SatelliteRegistry(opts *bind.CallOpts) (common.Address, error) {
	var out []interface{}
	err := _Maneuver.contract.Call(opts, &out, "satelliteRegistry")

	if err != nil {
		return *new(common.Address), err
	}

	out0 := *abi.ConvertType(out[0], new(common.Address)).(*common.Address)

	return out0, err

}

// SatelliteRegistry is a free data retrieval call binding the contract method 0xaf773265.
//
// Solidity: function satelliteRegistry() view returns(address)
func (_Maneuver *ManeuverSession) SatelliteRegistry() (common.Address, error) {
	return _Maneuver.Contract.SatelliteRegistry(&_Maneuver.CallOpts)
}

// SatelliteRegistry is a free data retrieval call binding the contract method 0xaf773265.
//
// Solidity: function satelliteRegistry() view returns(address)
func (_Maneuver *ManeuverCallerSession) SatelliteRegistry() (common.Address, error) {
	return _Maneuver.Contract.SatelliteRegistry(&_Maneuver.CallOpts)
}

// CompleteManeuver is a paid mutator transaction binding the contract method 0x86b6c805.
//
// Solidity: function completeManeuver(bytes32 maneuverHash) returns()
func (_Maneuver *ManeuverTransactor) CompleteManeuver(opts *bind.TransactOpts, maneuverHash [32]byte) (*types.Transaction, error) {
	return _Maneuver.contract.Transact(opts, "completeManeuver", maneuverHash)
}

// CompleteManeuver is a paid mutator transaction binding the contract method 0x86b6c805.
//
// Solidity: function completeManeuver(bytes32 maneuverHash) returns()
func (_Maneuver *ManeuverSession) CompleteManeuver(maneuverHash [32]byte) (*types.Transaction, error) {
	return _Maneuver.Contract.CompleteManeuver(&_Maneuver.TransactOpts, maneuverHash)
}

// CompleteManeuver is a paid mutator transaction binding the contract method 0x86b6c805.
//
// Solidity: function completeManeuver(bytes32 maneuverHash) returns()
func (_Maneuver *ManeuverTransactorSession) CompleteManeuver(maneuverHash [32]byte) (*types.Transaction, error) {
	return _Maneuver.Contract.CompleteManeuver(&_Maneuver.TransactOpts, maneuverHash)
}

// ExecuteManeuver is a paid mutator transaction binding the contract method 0x1d2836dc.
//
// Solidity: function executeManeuver(bytes32 alertId, bytes parameters) returns()
func (_Maneuver *ManeuverTransactor) ExecuteManeuver(opts *bind.TransactOpts, alertId [32]byte, parameters []byte) (*types.Transaction, error) {
	return _Maneuver.contract.Transact(opts, "executeManeuver", alertId, parameters)
}

// ExecuteManeuver is a paid mutator transaction binding the contract method 0x1d2836dc.
//
// Solidity: function executeManeuver(bytes32 alertId, bytes parameters) returns()
func (_Maneuver *ManeuverSession) ExecuteManeuver(alertId [32]byte, parameters []byte) (*types.Transaction, error) {
	return _Maneuver.Contract.ExecuteManeuver(&_Maneuver.TransactOpts, alertId, parameters)
}

// ExecuteManeuver is a paid mutator transaction binding the contract method 0x1d2836dc.
//
// Solidity: function executeManeuver(bytes32 alertId, bytes parameters) returns()
func (_Maneuver *ManeuverTransactorSession) ExecuteManeuver(alertId [32]byte, parameters []byte) (*types.Transaction, error) {
	return _Maneuver.Contract.ExecuteManeuver(&_Maneuver.TransactOpts, alertId, parameters)
}

// ManeuverManeuverCompletedIterator is returned from FilterManeuverCompleted and is used to iterate over the raw logs and unpacked data for ManeuverCompleted events raised by the Maneuver contract.
type ManeuverManeuverCompletedIterator struct {
	Event *ManeuverManeuverCompleted // Event containing the contract specifics and raw log

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
func (it *ManeuverManeuverCompletedIterator) Next() bool {
	// If the iterator failed, stop iterating
	if it.fail != nil {
		return false
	}
	// If the iterator completed, deliver directly whatever's available
	if it.done {
		select {
		case log := <-it.logs:
			it.Event = new(ManeuverManeuverCompleted)
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
		it.Event = new(ManeuverManeuverCompleted)
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
func (it *ManeuverManeuverCompletedIterator) Error() error {
	return it.fail
}

// Close terminates the iteration process, releasing any pending underlying
// resources.
func (it *ManeuverManeuverCompletedIterator) Close() error {
	it.sub.Unsubscribe()
	return nil
}

// ManeuverManeuverCompleted represents a ManeuverCompleted event raised by the Maneuver contract.
type ManeuverManeuverCompleted struct {
	ManeuverHash   [32]byte
	CompletionTime *big.Int
	Raw            types.Log // Blockchain specific contextual infos
}

// FilterManeuverCompleted is a free log retrieval operation binding the contract event 0xd4108d1d7cd9152c9fa207749b576b4bce8ce9741a4e492c740f28c6849a3fdf.
//
// Solidity: event ManeuverCompleted(bytes32 indexed maneuverHash, uint256 completionTime)
func (_Maneuver *ManeuverFilterer) FilterManeuverCompleted(opts *bind.FilterOpts, maneuverHash [][32]byte) (*ManeuverManeuverCompletedIterator, error) {

	var maneuverHashRule []interface{}
	for _, maneuverHashItem := range maneuverHash {
		maneuverHashRule = append(maneuverHashRule, maneuverHashItem)
	}

	logs, sub, err := _Maneuver.contract.FilterLogs(opts, "ManeuverCompleted", maneuverHashRule)
	if err != nil {
		return nil, err
	}
	return &ManeuverManeuverCompletedIterator{contract: _Maneuver.contract, event: "ManeuverCompleted", logs: logs, sub: sub}, nil
}

// WatchManeuverCompleted is a free log subscription operation binding the contract event 0xd4108d1d7cd9152c9fa207749b576b4bce8ce9741a4e492c740f28c6849a3fdf.
//
// Solidity: event ManeuverCompleted(bytes32 indexed maneuverHash, uint256 completionTime)
func (_Maneuver *ManeuverFilterer) WatchManeuverCompleted(opts *bind.WatchOpts, sink chan<- *ManeuverManeuverCompleted, maneuverHash [][32]byte) (event.Subscription, error) {

	var maneuverHashRule []interface{}
	for _, maneuverHashItem := range maneuverHash {
		maneuverHashRule = append(maneuverHashRule, maneuverHashItem)
	}

	logs, sub, err := _Maneuver.contract.WatchLogs(opts, "ManeuverCompleted", maneuverHashRule)
	if err != nil {
		return nil, err
	}
	return event.NewSubscription(func(quit <-chan struct{}) error {
		defer sub.Unsubscribe()
		for {
			select {
			case log := <-logs:
				// New log arrived, parse the event and forward to the user
				event := new(ManeuverManeuverCompleted)
				if err := _Maneuver.contract.UnpackLog(event, "ManeuverCompleted", log); err != nil {
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

// ParseManeuverCompleted is a log parse operation binding the contract event 0xd4108d1d7cd9152c9fa207749b576b4bce8ce9741a4e492c740f28c6849a3fdf.
//
// Solidity: event ManeuverCompleted(bytes32 indexed maneuverHash, uint256 completionTime)
func (_Maneuver *ManeuverFilterer) ParseManeuverCompleted(log types.Log) (*ManeuverManeuverCompleted, error) {
	event := new(ManeuverManeuverCompleted)
	if err := _Maneuver.contract.UnpackLog(event, "ManeuverCompleted", log); err != nil {
		return nil, err
	}
	event.Raw = log
	return event, nil
}

// ManeuverManeuverExecutedIterator is returned from FilterManeuverExecuted and is used to iterate over the raw logs and unpacked data for ManeuverExecuted events raised by the Maneuver contract.
type ManeuverManeuverExecutedIterator struct {
	Event *ManeuverManeuverExecuted // Event containing the contract specifics and raw log

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
func (it *ManeuverManeuverExecutedIterator) Next() bool {
	// If the iterator failed, stop iterating
	if it.fail != nil {
		return false
	}
	// If the iterator completed, deliver directly whatever's available
	if it.done {
		select {
		case log := <-it.logs:
			it.Event = new(ManeuverManeuverExecuted)
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
		it.Event = new(ManeuverManeuverExecuted)
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
func (it *ManeuverManeuverExecutedIterator) Error() error {
	return it.fail
}

// Close terminates the iteration process, releasing any pending underlying
// resources.
func (it *ManeuverManeuverExecutedIterator) Close() error {
	it.sub.Unsubscribe()
	return nil
}

// ManeuverManeuverExecuted represents a ManeuverExecuted event raised by the Maneuver contract.
type ManeuverManeuverExecuted struct {
	ManeuverHash [32]byte
	SatelliteId  [32]byte
	Raw          types.Log // Blockchain specific contextual infos
}

// FilterManeuverExecuted is a free log retrieval operation binding the contract event 0x98be6538d049735d11d241251dfbafefa2b15401289a7f62b4f34f7b8db2a44d.
//
// Solidity: event ManeuverExecuted(bytes32 indexed maneuverHash, bytes32 satelliteId)
func (_Maneuver *ManeuverFilterer) FilterManeuverExecuted(opts *bind.FilterOpts, maneuverHash [][32]byte) (*ManeuverManeuverExecutedIterator, error) {

	var maneuverHashRule []interface{}
	for _, maneuverHashItem := range maneuverHash {
		maneuverHashRule = append(maneuverHashRule, maneuverHashItem)
	}

	logs, sub, err := _Maneuver.contract.FilterLogs(opts, "ManeuverExecuted", maneuverHashRule)
	if err != nil {
		return nil, err
	}
	return &ManeuverManeuverExecutedIterator{contract: _Maneuver.contract, event: "ManeuverExecuted", logs: logs, sub: sub}, nil
}

// WatchManeuverExecuted is a free log subscription operation binding the contract event 0x98be6538d049735d11d241251dfbafefa2b15401289a7f62b4f34f7b8db2a44d.
//
// Solidity: event ManeuverExecuted(bytes32 indexed maneuverHash, bytes32 satelliteId)
func (_Maneuver *ManeuverFilterer) WatchManeuverExecuted(opts *bind.WatchOpts, sink chan<- *ManeuverManeuverExecuted, maneuverHash [][32]byte) (event.Subscription, error) {

	var maneuverHashRule []interface{}
	for _, maneuverHashItem := range maneuverHash {
		maneuverHashRule = append(maneuverHashRule, maneuverHashItem)
	}

	logs, sub, err := _Maneuver.contract.WatchLogs(opts, "ManeuverExecuted", maneuverHashRule)
	if err != nil {
		return nil, err
	}
	return event.NewSubscription(func(quit <-chan struct{}) error {
		defer sub.Unsubscribe()
		for {
			select {
			case log := <-logs:
				// New log arrived, parse the event and forward to the user
				event := new(ManeuverManeuverExecuted)
				if err := _Maneuver.contract.UnpackLog(event, "ManeuverExecuted", log); err != nil {
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

// ParseManeuverExecuted is a log parse operation binding the contract event 0x98be6538d049735d11d241251dfbafefa2b15401289a7f62b4f34f7b8db2a44d.
//
// Solidity: event ManeuverExecuted(bytes32 indexed maneuverHash, bytes32 satelliteId)
func (_Maneuver *ManeuverFilterer) ParseManeuverExecuted(log types.Log) (*ManeuverManeuverExecuted, error) {
	event := new(ManeuverManeuverExecuted)
	if err := _Maneuver.contract.UnpackLog(event, "ManeuverExecuted", log); err != nil {
		return nil, err
	}
	event.Raw = log
	return event, nil
}
