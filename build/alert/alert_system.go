// Code generated - DO NOT EDIT.
// This file is a generated binding and any manual changes will be lost.

package alert

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

// AlertMetaData contains all meta data concerning the Alert contract.
var AlertMetaData = &bind.MetaData{
	ABI: "[{\"inputs\":[{\"internalType\":\"address\",\"name\":\"_registryAddress\",\"type\":\"address\"}],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"bytes32\",\"name\":\"alertId\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"satelliteId\",\"type\":\"bytes32\"}],\"name\":\"AlertCreated\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"bytes32\",\"name\":\"alertId\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"validatorId\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"bool\",\"name\":\"decision\",\"type\":\"bool\"}],\"name\":\"AlertValidated\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"bytes32\",\"name\":\"alertId\",\"type\":\"bytes32\"},{\"indexed\":false,\"internalType\":\"bytes32\",\"name\":\"satelliteId\",\"type\":\"bytes32\"}],\"name\":\"OrbitalChangeTriggered\",\"type\":\"event\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"alertIds\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"name\":\"alerts\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"alertId\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"satelliteId\",\"type\":\"bytes32\"},{\"internalType\":\"uint256\",\"name\":\"timestamp\",\"type\":\"uint256\"},{\"internalType\":\"bytes\",\"name\":\"coordinates\",\"type\":\"bytes\"},{\"internalType\":\"bytes32\",\"name\":\"alertType\",\"type\":\"bytes32\"},{\"internalType\":\"bytes\",\"name\":\"alertData\",\"type\":\"bytes\"},{\"internalType\":\"bool\",\"name\":\"executed\",\"type\":\"bool\"},{\"internalType\":\"uint256\",\"name\":\"validations\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"alertId\",\"type\":\"bytes32\"},{\"internalType\":\"bytes\",\"name\":\"coordinates\",\"type\":\"bytes\"},{\"internalType\":\"bytes32\",\"name\":\"alertType\",\"type\":\"bytes32\"},{\"internalType\":\"bytes\",\"name\":\"alertData\",\"type\":\"bytes\"}],\"name\":\"createAlert\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"alertId\",\"type\":\"bytes32\"}],\"name\":\"getAlertSatelliteId\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"alertId\",\"type\":\"bytes32\"}],\"name\":\"meetsValidationThreshold\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"satelliteRegistry\",\"outputs\":[{\"internalType\":\"contractSatelliteRegistry\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"alertId\",\"type\":\"bytes32\"},{\"internalType\":\"bool\",\"name\":\"decision\",\"type\":\"bool\"}],\"name\":\"validateAlert\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"validationThreshold\",\"outputs\":[{\"internalType\":\"uint8\",\"name\":\"\",\"type\":\"uint8\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"name\":\"validations\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"alertId\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"validatorId\",\"type\":\"bytes32\"},{\"internalType\":\"bool\",\"name\":\"isValid\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]",
	Bin: "0x60806040526003805460ff60a01b1916603360a01b1790553480156021575f5ffd5b50604051610ee7380380610ee7833981016040819052603e916062565b600380546001600160a01b0319166001600160a01b0392909216919091179055608d565b5f602082840312156071575f5ffd5b81516001600160a01b03811681146086575f5ffd5b9392505050565b610e4d8061009a5f395ff3fe608060405234801561000f575f5ffd5b5060043610610090575f3560e01c8063a27f17b211610063578063a27f17b214610156578063af77326514610169578063bce9252d14610194578063ccf07b6f146101bb578063eeb6babf146101ce575f5ffd5b80630df6fbbc1461009457806328c26fce146100ee5780634955750414610111578063592ccf9714610126575b5f5ffd5b6100cc6100a23660046109a5565b600260208181525f9384526040808520909152918352912080546001820154919092015460ff1683565b6040805193845260208401929092521515908201526060015b60405180910390f35b6101016100fc3660046109c5565b6101f4565b60405190151581526020016100e5565b61012461011f366004610a21565b6102af565b005b6101486101343660046109c5565b5f9081526020819052604090206001015490565b6040519081526020016100e5565b610124610164366004610aa3565b6104a3565b60035461017c906001600160a01b031681565b6040516001600160a01b0390911681526020016100e5565b6101a76101a23660046109c5565b610631565b6040516100e5989796959493929190610b03565b6101486101c93660046109c5565b610783565b6003546101e290600160a01b900460ff1681565b60405160ff90911681526020016100e5565b5f5f60035f9054906101000a90046001600160a01b03166001600160a01b0316630c6ff4f26040518163ffffffff1660e01b81526004015f60405180830381865afa158015610245573d5f5f3e3d5ffd5b505050506040513d5f823e601f3d908101601f1916820160405261026c9190810190610b6d565b516003545f85815260208190526040902060070154919250600160a01b900460ff1690829061029c906064610c49565b6102a69190610c66565b10159392505050565b5f6102b9336107a2565b9050806103175760405162461bcd60e51b815260206004820152602160248201527f53656e646572206e6f742061207265676973746572656420736174656c6c69746044820152606560f81b60648201526084015b60405180910390fd5b60405180610100016040528088815260200182815260200142815260200187878080601f0160208091040260200160405190810160405280939291908181526020018383808284375f92019190915250505090825250602080820187905260408051601f870183900483028101830182528681529201919086908690819084018382808284375f920182905250938552505050602080830182905260409283018290528a82528181529082902083518155908301516001820155908201516002820155606082015160038201906103ee9082610d08565b506080820151600482015560a0820151600582019061040d9082610d08565b5060c082015160068201805460ff191691151591909117905560e0909101516007909101556001805480820182555f919091527fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf60187905560405181815287907f6e5023c18c841992dfbc0d01c3cf9c496273f3c424db438d5ff42cc051bcfe699060200160405180910390a250505050505050565b5f6104ad336107a2565b9050806105085760405162461bcd60e51b8152602060048201526024808201527f56616c696461746f72206e6f742061207265676973746572656420736174656c6044820152636c69746560e01b606482015260840161030e565b5f83815260208190526040902054831461055b5760405162461bcd60e51b8152602060048201526014602482015273105b195c9d08191bd95cc81b9bdd08195e1a5cdd60621b604482015260640161030e565b604080516060810182528481526020808201848152851580158486019081525f898152600280865287822089835290955295909520935184559051600184015592519101805460ff19169115159190911790556105d9575f8381526020819052604081206007018054600192906105d3908490610dc3565b90915550505b60408051828152831515602082015284917fdcb26ca2544f5bef639f92dc7d97cde86edc85eb28a0f2a84c2d30b47257c302910160405180910390a261061e836101f4565b1561062c5761062c836108ec565b505050565b5f6020819052908152604090208054600182015460028301546003840180549394929391929161066090610c85565b80601f016020809104026020016040519081016040528092919081815260200182805461068c90610c85565b80156106d75780601f106106ae576101008083540402835291602001916106d7565b820191905f5260205f20905b8154815290600101906020018083116106ba57829003601f168201915b5050505050908060040154908060050180546106f290610c85565b80601f016020809104026020016040519081016040528092919081815260200182805461071e90610c85565b80156107695780601f1061074057610100808354040283529160200191610769565b820191905f5260205f20905b81548152906001019060200180831161074c57829003601f168201915b505050506006830154600790930154919260ff1691905088565b60018181548110610792575f80fd5b5f91825260209091200154905081565b5f5f60035f9054906101000a90046001600160a01b03166001600160a01b0316630c6ff4f26040518163ffffffff1660e01b81526004015f60405180830381865afa1580156107f3573d5f5f3e3d5ffd5b505050506040513d5f823e601f3d908101601f1916820160405261081a9190810190610b6d565b90505f5b81518110156108e3575f82828151811061083a5761083a610dd6565b60209081029190910101516003546040516358e2a8e560e11b8152600481018390529192505f916001600160a01b039091169063b1c551ca90602401602060405180830381865afa158015610891573d5f5f3e3d5ffd5b505050506040513d601f19601f820116820180604052508101906108b59190610dea565b9050856001600160a01b0316816001600160a01b0316036108d95750949350505050565b505060010161081e565b505f9392505050565b5f8181526020819052604090206006015460ff161561094d5760405162461bcd60e51b815260206004820152601f60248201527f4f72626974616c206368616e676520616c726561647920657865637574656400604482015260640161030e565b5f818152602081815260409182902060068101805460ff191660019081179091550154915191825282917f81572d6c1fa03021dc3b4027db165f27d517c9cab060f25c9266d46c397df04c910160405180910390a250565b5f5f604083850312156109b6575f5ffd5b50508035926020909101359150565b5f602082840312156109d5575f5ffd5b5035919050565b5f5f83601f8401126109ec575f5ffd5b50813567ffffffffffffffff811115610a03575f5ffd5b602083019150836020828501011115610a1a575f5ffd5b9250929050565b5f5f5f5f5f5f60808789031215610a36575f5ffd5b86359550602087013567ffffffffffffffff811115610a53575f5ffd5b610a5f89828a016109dc565b90965094505060408701359250606087013567ffffffffffffffff811115610a85575f5ffd5b610a9189828a016109dc565b979a9699509497509295939492505050565b5f5f60408385031215610ab4575f5ffd5b8235915060208301358015158114610aca575f5ffd5b809150509250929050565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b88815287602082015286604082015261010060608201525f610b29610100830188610ad5565b86608084015282810360a0840152610b418187610ad5565b94151560c0840152505060e001529695505050505050565b634e487b7160e01b5f52604160045260245ffd5b5f60208284031215610b7d575f5ffd5b815167ffffffffffffffff811115610b93575f5ffd5b8201601f81018413610ba3575f5ffd5b805167ffffffffffffffff811115610bbd57610bbd610b59565b8060051b604051601f19603f830116810181811067ffffffffffffffff82111715610bea57610bea610b59565b604052918252602081840181019290810187841115610c07575f5ffd5b6020850194505b83851015610c2a57845180825260209586019590935001610c0e565b509695505050505050565b634e487b7160e01b5f52601160045260245ffd5b8082028115828204841417610c6057610c60610c35565b92915050565b5f82610c8057634e487b7160e01b5f52601260045260245ffd5b500490565b600181811c90821680610c9957607f821691505b602082108103610cb757634e487b7160e01b5f52602260045260245ffd5b50919050565b601f82111561062c57805f5260205f20601f840160051c81016020851015610ce25750805b601f840160051c820191505b81811015610d01575f8155600101610cee565b5050505050565b815167ffffffffffffffff811115610d2257610d22610b59565b610d3681610d308454610c85565b84610cbd565b6020601f821160018114610d68575f8315610d515750848201515b5f19600385901b1c1916600184901b178455610d01565b5f84815260208120601f198516915b82811015610d975787850151825560209485019460019092019101610d77565b5084821015610db457868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b80820180821115610c6057610c60610c35565b634e487b7160e01b5f52603260045260245ffd5b5f60208284031215610dfa575f5ffd5b81516001600160a01b0381168114610e10575f5ffd5b939250505056fea2646970667358221220fcd2be6e9b95d63bcc68aed98efdbe7d83ee6738a756512ab93d593998a4718064736f6c634300081d0033",
}

// AlertABI is the input ABI used to generate the binding from.
// Deprecated: Use AlertMetaData.ABI instead.
var AlertABI = AlertMetaData.ABI

// AlertBin is the compiled bytecode used for deploying new contracts.
// Deprecated: Use AlertMetaData.Bin instead.
var AlertBin = AlertMetaData.Bin

// DeployAlert deploys a new Ethereum contract, binding an instance of Alert to it.
func DeployAlert(auth *bind.TransactOpts, backend bind.ContractBackend, _registryAddress common.Address) (common.Address, *types.Transaction, *Alert, error) {
	parsed, err := AlertMetaData.GetAbi()
	if err != nil {
		return common.Address{}, nil, nil, err
	}
	if parsed == nil {
		return common.Address{}, nil, nil, errors.New("GetABI returned nil")
	}

	address, tx, contract, err := bind.DeployContract(auth, *parsed, common.FromHex(AlertBin), backend, _registryAddress)
	if err != nil {
		return common.Address{}, nil, nil, err
	}
	return address, tx, &Alert{AlertCaller: AlertCaller{contract: contract}, AlertTransactor: AlertTransactor{contract: contract}, AlertFilterer: AlertFilterer{contract: contract}}, nil
}

// Alert is an auto generated Go binding around an Ethereum contract.
type Alert struct {
	AlertCaller     // Read-only binding to the contract
	AlertTransactor // Write-only binding to the contract
	AlertFilterer   // Log filterer for contract events
}

// AlertCaller is an auto generated read-only Go binding around an Ethereum contract.
type AlertCaller struct {
	contract *bind.BoundContract // Generic contract wrapper for the low level calls
}

// AlertTransactor is an auto generated write-only Go binding around an Ethereum contract.
type AlertTransactor struct {
	contract *bind.BoundContract // Generic contract wrapper for the low level calls
}

// AlertFilterer is an auto generated log filtering Go binding around an Ethereum contract events.
type AlertFilterer struct {
	contract *bind.BoundContract // Generic contract wrapper for the low level calls
}

// AlertSession is an auto generated Go binding around an Ethereum contract,
// with pre-set call and transact options.
type AlertSession struct {
	Contract     *Alert            // Generic contract binding to set the session for
	CallOpts     bind.CallOpts     // Call options to use throughout this session
	TransactOpts bind.TransactOpts // Transaction auth options to use throughout this session
}

// AlertCallerSession is an auto generated read-only Go binding around an Ethereum contract,
// with pre-set call options.
type AlertCallerSession struct {
	Contract *AlertCaller  // Generic contract caller binding to set the session for
	CallOpts bind.CallOpts // Call options to use throughout this session
}

// AlertTransactorSession is an auto generated write-only Go binding around an Ethereum contract,
// with pre-set transact options.
type AlertTransactorSession struct {
	Contract     *AlertTransactor  // Generic contract transactor binding to set the session for
	TransactOpts bind.TransactOpts // Transaction auth options to use throughout this session
}

// AlertRaw is an auto generated low-level Go binding around an Ethereum contract.
type AlertRaw struct {
	Contract *Alert // Generic contract binding to access the raw methods on
}

// AlertCallerRaw is an auto generated low-level read-only Go binding around an Ethereum contract.
type AlertCallerRaw struct {
	Contract *AlertCaller // Generic read-only contract binding to access the raw methods on
}

// AlertTransactorRaw is an auto generated low-level write-only Go binding around an Ethereum contract.
type AlertTransactorRaw struct {
	Contract *AlertTransactor // Generic write-only contract binding to access the raw methods on
}

// NewAlert creates a new instance of Alert, bound to a specific deployed contract.
func NewAlert(address common.Address, backend bind.ContractBackend) (*Alert, error) {
	contract, err := bindAlert(address, backend, backend, backend)
	if err != nil {
		return nil, err
	}
	return &Alert{AlertCaller: AlertCaller{contract: contract}, AlertTransactor: AlertTransactor{contract: contract}, AlertFilterer: AlertFilterer{contract: contract}}, nil
}

// NewAlertCaller creates a new read-only instance of Alert, bound to a specific deployed contract.
func NewAlertCaller(address common.Address, caller bind.ContractCaller) (*AlertCaller, error) {
	contract, err := bindAlert(address, caller, nil, nil)
	if err != nil {
		return nil, err
	}
	return &AlertCaller{contract: contract}, nil
}

// NewAlertTransactor creates a new write-only instance of Alert, bound to a specific deployed contract.
func NewAlertTransactor(address common.Address, transactor bind.ContractTransactor) (*AlertTransactor, error) {
	contract, err := bindAlert(address, nil, transactor, nil)
	if err != nil {
		return nil, err
	}
	return &AlertTransactor{contract: contract}, nil
}

// NewAlertFilterer creates a new log filterer instance of Alert, bound to a specific deployed contract.
func NewAlertFilterer(address common.Address, filterer bind.ContractFilterer) (*AlertFilterer, error) {
	contract, err := bindAlert(address, nil, nil, filterer)
	if err != nil {
		return nil, err
	}
	return &AlertFilterer{contract: contract}, nil
}

// bindAlert binds a generic wrapper to an already deployed contract.
func bindAlert(address common.Address, caller bind.ContractCaller, transactor bind.ContractTransactor, filterer bind.ContractFilterer) (*bind.BoundContract, error) {
	parsed, err := AlertMetaData.GetAbi()
	if err != nil {
		return nil, err
	}
	return bind.NewBoundContract(address, *parsed, caller, transactor, filterer), nil
}

// Call invokes the (constant) contract method with params as input values and
// sets the output to result. The result type might be a single field for simple
// returns, a slice of interfaces for anonymous returns and a struct for named
// returns.
func (_Alert *AlertRaw) Call(opts *bind.CallOpts, result *[]interface{}, method string, params ...interface{}) error {
	return _Alert.Contract.AlertCaller.contract.Call(opts, result, method, params...)
}

// Transfer initiates a plain transaction to move funds to the contract, calling
// its default method if one is available.
func (_Alert *AlertRaw) Transfer(opts *bind.TransactOpts) (*types.Transaction, error) {
	return _Alert.Contract.AlertTransactor.contract.Transfer(opts)
}

// Transact invokes the (paid) contract method with params as input values.
func (_Alert *AlertRaw) Transact(opts *bind.TransactOpts, method string, params ...interface{}) (*types.Transaction, error) {
	return _Alert.Contract.AlertTransactor.contract.Transact(opts, method, params...)
}

// Call invokes the (constant) contract method with params as input values and
// sets the output to result. The result type might be a single field for simple
// returns, a slice of interfaces for anonymous returns and a struct for named
// returns.
func (_Alert *AlertCallerRaw) Call(opts *bind.CallOpts, result *[]interface{}, method string, params ...interface{}) error {
	return _Alert.Contract.contract.Call(opts, result, method, params...)
}

// Transfer initiates a plain transaction to move funds to the contract, calling
// its default method if one is available.
func (_Alert *AlertTransactorRaw) Transfer(opts *bind.TransactOpts) (*types.Transaction, error) {
	return _Alert.Contract.contract.Transfer(opts)
}

// Transact invokes the (paid) contract method with params as input values.
func (_Alert *AlertTransactorRaw) Transact(opts *bind.TransactOpts, method string, params ...interface{}) (*types.Transaction, error) {
	return _Alert.Contract.contract.Transact(opts, method, params...)
}

// AlertIds is a free data retrieval call binding the contract method 0xccf07b6f.
//
// Solidity: function alertIds(uint256 ) view returns(bytes32)
func (_Alert *AlertCaller) AlertIds(opts *bind.CallOpts, arg0 *big.Int) ([32]byte, error) {
	var out []interface{}
	err := _Alert.contract.Call(opts, &out, "alertIds", arg0)

	if err != nil {
		return *new([32]byte), err
	}

	out0 := *abi.ConvertType(out[0], new([32]byte)).(*[32]byte)

	return out0, err

}

// AlertIds is a free data retrieval call binding the contract method 0xccf07b6f.
//
// Solidity: function alertIds(uint256 ) view returns(bytes32)
func (_Alert *AlertSession) AlertIds(arg0 *big.Int) ([32]byte, error) {
	return _Alert.Contract.AlertIds(&_Alert.CallOpts, arg0)
}

// AlertIds is a free data retrieval call binding the contract method 0xccf07b6f.
//
// Solidity: function alertIds(uint256 ) view returns(bytes32)
func (_Alert *AlertCallerSession) AlertIds(arg0 *big.Int) ([32]byte, error) {
	return _Alert.Contract.AlertIds(&_Alert.CallOpts, arg0)
}

// Alerts is a free data retrieval call binding the contract method 0xbce9252d.
//
// Solidity: function alerts(bytes32 ) view returns(bytes32 alertId, bytes32 satelliteId, uint256 timestamp, bytes coordinates, bytes32 alertType, bytes alertData, bool executed, uint256 validations)
func (_Alert *AlertCaller) Alerts(opts *bind.CallOpts, arg0 [32]byte) (struct {
	AlertId     [32]byte
	SatelliteId [32]byte
	Timestamp   *big.Int
	Coordinates []byte
	AlertType   [32]byte
	AlertData   []byte
	Executed    bool
	Validations *big.Int
}, error) {
	var out []interface{}
	err := _Alert.contract.Call(opts, &out, "alerts", arg0)

	outstruct := new(struct {
		AlertId     [32]byte
		SatelliteId [32]byte
		Timestamp   *big.Int
		Coordinates []byte
		AlertType   [32]byte
		AlertData   []byte
		Executed    bool
		Validations *big.Int
	})
	if err != nil {
		return *outstruct, err
	}

	outstruct.AlertId = *abi.ConvertType(out[0], new([32]byte)).(*[32]byte)
	outstruct.SatelliteId = *abi.ConvertType(out[1], new([32]byte)).(*[32]byte)
	outstruct.Timestamp = *abi.ConvertType(out[2], new(*big.Int)).(**big.Int)
	outstruct.Coordinates = *abi.ConvertType(out[3], new([]byte)).(*[]byte)
	outstruct.AlertType = *abi.ConvertType(out[4], new([32]byte)).(*[32]byte)
	outstruct.AlertData = *abi.ConvertType(out[5], new([]byte)).(*[]byte)
	outstruct.Executed = *abi.ConvertType(out[6], new(bool)).(*bool)
	outstruct.Validations = *abi.ConvertType(out[7], new(*big.Int)).(**big.Int)

	return *outstruct, err

}

// Alerts is a free data retrieval call binding the contract method 0xbce9252d.
//
// Solidity: function alerts(bytes32 ) view returns(bytes32 alertId, bytes32 satelliteId, uint256 timestamp, bytes coordinates, bytes32 alertType, bytes alertData, bool executed, uint256 validations)
func (_Alert *AlertSession) Alerts(arg0 [32]byte) (struct {
	AlertId     [32]byte
	SatelliteId [32]byte
	Timestamp   *big.Int
	Coordinates []byte
	AlertType   [32]byte
	AlertData   []byte
	Executed    bool
	Validations *big.Int
}, error) {
	return _Alert.Contract.Alerts(&_Alert.CallOpts, arg0)
}

// Alerts is a free data retrieval call binding the contract method 0xbce9252d.
//
// Solidity: function alerts(bytes32 ) view returns(bytes32 alertId, bytes32 satelliteId, uint256 timestamp, bytes coordinates, bytes32 alertType, bytes alertData, bool executed, uint256 validations)
func (_Alert *AlertCallerSession) Alerts(arg0 [32]byte) (struct {
	AlertId     [32]byte
	SatelliteId [32]byte
	Timestamp   *big.Int
	Coordinates []byte
	AlertType   [32]byte
	AlertData   []byte
	Executed    bool
	Validations *big.Int
}, error) {
	return _Alert.Contract.Alerts(&_Alert.CallOpts, arg0)
}

// GetAlertSatelliteId is a free data retrieval call binding the contract method 0x592ccf97.
//
// Solidity: function getAlertSatelliteId(bytes32 alertId) view returns(bytes32)
func (_Alert *AlertCaller) GetAlertSatelliteId(opts *bind.CallOpts, alertId [32]byte) ([32]byte, error) {
	var out []interface{}
	err := _Alert.contract.Call(opts, &out, "getAlertSatelliteId", alertId)

	if err != nil {
		return *new([32]byte), err
	}

	out0 := *abi.ConvertType(out[0], new([32]byte)).(*[32]byte)

	return out0, err

}

// GetAlertSatelliteId is a free data retrieval call binding the contract method 0x592ccf97.
//
// Solidity: function getAlertSatelliteId(bytes32 alertId) view returns(bytes32)
func (_Alert *AlertSession) GetAlertSatelliteId(alertId [32]byte) ([32]byte, error) {
	return _Alert.Contract.GetAlertSatelliteId(&_Alert.CallOpts, alertId)
}

// GetAlertSatelliteId is a free data retrieval call binding the contract method 0x592ccf97.
//
// Solidity: function getAlertSatelliteId(bytes32 alertId) view returns(bytes32)
func (_Alert *AlertCallerSession) GetAlertSatelliteId(alertId [32]byte) ([32]byte, error) {
	return _Alert.Contract.GetAlertSatelliteId(&_Alert.CallOpts, alertId)
}

// MeetsValidationThreshold is a free data retrieval call binding the contract method 0x28c26fce.
//
// Solidity: function meetsValidationThreshold(bytes32 alertId) view returns(bool)
func (_Alert *AlertCaller) MeetsValidationThreshold(opts *bind.CallOpts, alertId [32]byte) (bool, error) {
	var out []interface{}
	err := _Alert.contract.Call(opts, &out, "meetsValidationThreshold", alertId)

	if err != nil {
		return *new(bool), err
	}

	out0 := *abi.ConvertType(out[0], new(bool)).(*bool)

	return out0, err

}

// MeetsValidationThreshold is a free data retrieval call binding the contract method 0x28c26fce.
//
// Solidity: function meetsValidationThreshold(bytes32 alertId) view returns(bool)
func (_Alert *AlertSession) MeetsValidationThreshold(alertId [32]byte) (bool, error) {
	return _Alert.Contract.MeetsValidationThreshold(&_Alert.CallOpts, alertId)
}

// MeetsValidationThreshold is a free data retrieval call binding the contract method 0x28c26fce.
//
// Solidity: function meetsValidationThreshold(bytes32 alertId) view returns(bool)
func (_Alert *AlertCallerSession) MeetsValidationThreshold(alertId [32]byte) (bool, error) {
	return _Alert.Contract.MeetsValidationThreshold(&_Alert.CallOpts, alertId)
}

// SatelliteRegistry is a free data retrieval call binding the contract method 0xaf773265.
//
// Solidity: function satelliteRegistry() view returns(address)
func (_Alert *AlertCaller) SatelliteRegistry(opts *bind.CallOpts) (common.Address, error) {
	var out []interface{}
	err := _Alert.contract.Call(opts, &out, "satelliteRegistry")

	if err != nil {
		return *new(common.Address), err
	}

	out0 := *abi.ConvertType(out[0], new(common.Address)).(*common.Address)

	return out0, err

}

// SatelliteRegistry is a free data retrieval call binding the contract method 0xaf773265.
//
// Solidity: function satelliteRegistry() view returns(address)
func (_Alert *AlertSession) SatelliteRegistry() (common.Address, error) {
	return _Alert.Contract.SatelliteRegistry(&_Alert.CallOpts)
}

// SatelliteRegistry is a free data retrieval call binding the contract method 0xaf773265.
//
// Solidity: function satelliteRegistry() view returns(address)
func (_Alert *AlertCallerSession) SatelliteRegistry() (common.Address, error) {
	return _Alert.Contract.SatelliteRegistry(&_Alert.CallOpts)
}

// ValidationThreshold is a free data retrieval call binding the contract method 0xeeb6babf.
//
// Solidity: function validationThreshold() view returns(uint8)
func (_Alert *AlertCaller) ValidationThreshold(opts *bind.CallOpts) (uint8, error) {
	var out []interface{}
	err := _Alert.contract.Call(opts, &out, "validationThreshold")

	if err != nil {
		return *new(uint8), err
	}

	out0 := *abi.ConvertType(out[0], new(uint8)).(*uint8)

	return out0, err

}

// ValidationThreshold is a free data retrieval call binding the contract method 0xeeb6babf.
//
// Solidity: function validationThreshold() view returns(uint8)
func (_Alert *AlertSession) ValidationThreshold() (uint8, error) {
	return _Alert.Contract.ValidationThreshold(&_Alert.CallOpts)
}

// ValidationThreshold is a free data retrieval call binding the contract method 0xeeb6babf.
//
// Solidity: function validationThreshold() view returns(uint8)
func (_Alert *AlertCallerSession) ValidationThreshold() (uint8, error) {
	return _Alert.Contract.ValidationThreshold(&_Alert.CallOpts)
}

// Validations is a free data retrieval call binding the contract method 0x0df6fbbc.
//
// Solidity: function validations(bytes32 , bytes32 ) view returns(bytes32 alertId, bytes32 validatorId, bool isValid)
func (_Alert *AlertCaller) Validations(opts *bind.CallOpts, arg0 [32]byte, arg1 [32]byte) (struct {
	AlertId     [32]byte
	ValidatorId [32]byte
	IsValid     bool
}, error) {
	var out []interface{}
	err := _Alert.contract.Call(opts, &out, "validations", arg0, arg1)

	outstruct := new(struct {
		AlertId     [32]byte
		ValidatorId [32]byte
		IsValid     bool
	})
	if err != nil {
		return *outstruct, err
	}

	outstruct.AlertId = *abi.ConvertType(out[0], new([32]byte)).(*[32]byte)
	outstruct.ValidatorId = *abi.ConvertType(out[1], new([32]byte)).(*[32]byte)
	outstruct.IsValid = *abi.ConvertType(out[2], new(bool)).(*bool)

	return *outstruct, err

}

// Validations is a free data retrieval call binding the contract method 0x0df6fbbc.
//
// Solidity: function validations(bytes32 , bytes32 ) view returns(bytes32 alertId, bytes32 validatorId, bool isValid)
func (_Alert *AlertSession) Validations(arg0 [32]byte, arg1 [32]byte) (struct {
	AlertId     [32]byte
	ValidatorId [32]byte
	IsValid     bool
}, error) {
	return _Alert.Contract.Validations(&_Alert.CallOpts, arg0, arg1)
}

// Validations is a free data retrieval call binding the contract method 0x0df6fbbc.
//
// Solidity: function validations(bytes32 , bytes32 ) view returns(bytes32 alertId, bytes32 validatorId, bool isValid)
func (_Alert *AlertCallerSession) Validations(arg0 [32]byte, arg1 [32]byte) (struct {
	AlertId     [32]byte
	ValidatorId [32]byte
	IsValid     bool
}, error) {
	return _Alert.Contract.Validations(&_Alert.CallOpts, arg0, arg1)
}

// CreateAlert is a paid mutator transaction binding the contract method 0x49557504.
//
// Solidity: function createAlert(bytes32 alertId, bytes coordinates, bytes32 alertType, bytes alertData) returns()
func (_Alert *AlertTransactor) CreateAlert(opts *bind.TransactOpts, alertId [32]byte, coordinates []byte, alertType [32]byte, alertData []byte) (*types.Transaction, error) {
	return _Alert.contract.Transact(opts, "createAlert", alertId, coordinates, alertType, alertData)
}

// CreateAlert is a paid mutator transaction binding the contract method 0x49557504.
//
// Solidity: function createAlert(bytes32 alertId, bytes coordinates, bytes32 alertType, bytes alertData) returns()
func (_Alert *AlertSession) CreateAlert(alertId [32]byte, coordinates []byte, alertType [32]byte, alertData []byte) (*types.Transaction, error) {
	return _Alert.Contract.CreateAlert(&_Alert.TransactOpts, alertId, coordinates, alertType, alertData)
}

// CreateAlert is a paid mutator transaction binding the contract method 0x49557504.
//
// Solidity: function createAlert(bytes32 alertId, bytes coordinates, bytes32 alertType, bytes alertData) returns()
func (_Alert *AlertTransactorSession) CreateAlert(alertId [32]byte, coordinates []byte, alertType [32]byte, alertData []byte) (*types.Transaction, error) {
	return _Alert.Contract.CreateAlert(&_Alert.TransactOpts, alertId, coordinates, alertType, alertData)
}

// ValidateAlert is a paid mutator transaction binding the contract method 0xa27f17b2.
//
// Solidity: function validateAlert(bytes32 alertId, bool decision) returns()
func (_Alert *AlertTransactor) ValidateAlert(opts *bind.TransactOpts, alertId [32]byte, decision bool) (*types.Transaction, error) {
	return _Alert.contract.Transact(opts, "validateAlert", alertId, decision)
}

// ValidateAlert is a paid mutator transaction binding the contract method 0xa27f17b2.
//
// Solidity: function validateAlert(bytes32 alertId, bool decision) returns()
func (_Alert *AlertSession) ValidateAlert(alertId [32]byte, decision bool) (*types.Transaction, error) {
	return _Alert.Contract.ValidateAlert(&_Alert.TransactOpts, alertId, decision)
}

// ValidateAlert is a paid mutator transaction binding the contract method 0xa27f17b2.
//
// Solidity: function validateAlert(bytes32 alertId, bool decision) returns()
func (_Alert *AlertTransactorSession) ValidateAlert(alertId [32]byte, decision bool) (*types.Transaction, error) {
	return _Alert.Contract.ValidateAlert(&_Alert.TransactOpts, alertId, decision)
}

// AlertAlertCreatedIterator is returned from FilterAlertCreated and is used to iterate over the raw logs and unpacked data for AlertCreated events raised by the Alert contract.
type AlertAlertCreatedIterator struct {
	Event *AlertAlertCreated // Event containing the contract specifics and raw log

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
func (it *AlertAlertCreatedIterator) Next() bool {
	// If the iterator failed, stop iterating
	if it.fail != nil {
		return false
	}
	// If the iterator completed, deliver directly whatever's available
	if it.done {
		select {
		case log := <-it.logs:
			it.Event = new(AlertAlertCreated)
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
		it.Event = new(AlertAlertCreated)
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
func (it *AlertAlertCreatedIterator) Error() error {
	return it.fail
}

// Close terminates the iteration process, releasing any pending underlying
// resources.
func (it *AlertAlertCreatedIterator) Close() error {
	it.sub.Unsubscribe()
	return nil
}

// AlertAlertCreated represents a AlertCreated event raised by the Alert contract.
type AlertAlertCreated struct {
	AlertId     [32]byte
	SatelliteId [32]byte
	Raw         types.Log // Blockchain specific contextual infos
}

// FilterAlertCreated is a free log retrieval operation binding the contract event 0x6e5023c18c841992dfbc0d01c3cf9c496273f3c424db438d5ff42cc051bcfe69.
//
// Solidity: event AlertCreated(bytes32 indexed alertId, bytes32 satelliteId)
func (_Alert *AlertFilterer) FilterAlertCreated(opts *bind.FilterOpts, alertId [][32]byte) (*AlertAlertCreatedIterator, error) {

	var alertIdRule []interface{}
	for _, alertIdItem := range alertId {
		alertIdRule = append(alertIdRule, alertIdItem)
	}

	logs, sub, err := _Alert.contract.FilterLogs(opts, "AlertCreated", alertIdRule)
	if err != nil {
		return nil, err
	}
	return &AlertAlertCreatedIterator{contract: _Alert.contract, event: "AlertCreated", logs: logs, sub: sub}, nil
}

// WatchAlertCreated is a free log subscription operation binding the contract event 0x6e5023c18c841992dfbc0d01c3cf9c496273f3c424db438d5ff42cc051bcfe69.
//
// Solidity: event AlertCreated(bytes32 indexed alertId, bytes32 satelliteId)
func (_Alert *AlertFilterer) WatchAlertCreated(opts *bind.WatchOpts, sink chan<- *AlertAlertCreated, alertId [][32]byte) (event.Subscription, error) {

	var alertIdRule []interface{}
	for _, alertIdItem := range alertId {
		alertIdRule = append(alertIdRule, alertIdItem)
	}

	logs, sub, err := _Alert.contract.WatchLogs(opts, "AlertCreated", alertIdRule)
	if err != nil {
		return nil, err
	}
	return event.NewSubscription(func(quit <-chan struct{}) error {
		defer sub.Unsubscribe()
		for {
			select {
			case log := <-logs:
				// New log arrived, parse the event and forward to the user
				event := new(AlertAlertCreated)
				if err := _Alert.contract.UnpackLog(event, "AlertCreated", log); err != nil {
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

// ParseAlertCreated is a log parse operation binding the contract event 0x6e5023c18c841992dfbc0d01c3cf9c496273f3c424db438d5ff42cc051bcfe69.
//
// Solidity: event AlertCreated(bytes32 indexed alertId, bytes32 satelliteId)
func (_Alert *AlertFilterer) ParseAlertCreated(log types.Log) (*AlertAlertCreated, error) {
	event := new(AlertAlertCreated)
	if err := _Alert.contract.UnpackLog(event, "AlertCreated", log); err != nil {
		return nil, err
	}
	event.Raw = log
	return event, nil
}

// AlertAlertValidatedIterator is returned from FilterAlertValidated and is used to iterate over the raw logs and unpacked data for AlertValidated events raised by the Alert contract.
type AlertAlertValidatedIterator struct {
	Event *AlertAlertValidated // Event containing the contract specifics and raw log

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
func (it *AlertAlertValidatedIterator) Next() bool {
	// If the iterator failed, stop iterating
	if it.fail != nil {
		return false
	}
	// If the iterator completed, deliver directly whatever's available
	if it.done {
		select {
		case log := <-it.logs:
			it.Event = new(AlertAlertValidated)
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
		it.Event = new(AlertAlertValidated)
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
func (it *AlertAlertValidatedIterator) Error() error {
	return it.fail
}

// Close terminates the iteration process, releasing any pending underlying
// resources.
func (it *AlertAlertValidatedIterator) Close() error {
	it.sub.Unsubscribe()
	return nil
}

// AlertAlertValidated represents a AlertValidated event raised by the Alert contract.
type AlertAlertValidated struct {
	AlertId     [32]byte
	ValidatorId [32]byte
	Decision    bool
	Raw         types.Log // Blockchain specific contextual infos
}

// FilterAlertValidated is a free log retrieval operation binding the contract event 0xdcb26ca2544f5bef639f92dc7d97cde86edc85eb28a0f2a84c2d30b47257c302.
//
// Solidity: event AlertValidated(bytes32 indexed alertId, bytes32 validatorId, bool decision)
func (_Alert *AlertFilterer) FilterAlertValidated(opts *bind.FilterOpts, alertId [][32]byte) (*AlertAlertValidatedIterator, error) {

	var alertIdRule []interface{}
	for _, alertIdItem := range alertId {
		alertIdRule = append(alertIdRule, alertIdItem)
	}

	logs, sub, err := _Alert.contract.FilterLogs(opts, "AlertValidated", alertIdRule)
	if err != nil {
		return nil, err
	}
	return &AlertAlertValidatedIterator{contract: _Alert.contract, event: "AlertValidated", logs: logs, sub: sub}, nil
}

// WatchAlertValidated is a free log subscription operation binding the contract event 0xdcb26ca2544f5bef639f92dc7d97cde86edc85eb28a0f2a84c2d30b47257c302.
//
// Solidity: event AlertValidated(bytes32 indexed alertId, bytes32 validatorId, bool decision)
func (_Alert *AlertFilterer) WatchAlertValidated(opts *bind.WatchOpts, sink chan<- *AlertAlertValidated, alertId [][32]byte) (event.Subscription, error) {

	var alertIdRule []interface{}
	for _, alertIdItem := range alertId {
		alertIdRule = append(alertIdRule, alertIdItem)
	}

	logs, sub, err := _Alert.contract.WatchLogs(opts, "AlertValidated", alertIdRule)
	if err != nil {
		return nil, err
	}
	return event.NewSubscription(func(quit <-chan struct{}) error {
		defer sub.Unsubscribe()
		for {
			select {
			case log := <-logs:
				// New log arrived, parse the event and forward to the user
				event := new(AlertAlertValidated)
				if err := _Alert.contract.UnpackLog(event, "AlertValidated", log); err != nil {
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

// ParseAlertValidated is a log parse operation binding the contract event 0xdcb26ca2544f5bef639f92dc7d97cde86edc85eb28a0f2a84c2d30b47257c302.
//
// Solidity: event AlertValidated(bytes32 indexed alertId, bytes32 validatorId, bool decision)
func (_Alert *AlertFilterer) ParseAlertValidated(log types.Log) (*AlertAlertValidated, error) {
	event := new(AlertAlertValidated)
	if err := _Alert.contract.UnpackLog(event, "AlertValidated", log); err != nil {
		return nil, err
	}
	event.Raw = log
	return event, nil
}

// AlertOrbitalChangeTriggeredIterator is returned from FilterOrbitalChangeTriggered and is used to iterate over the raw logs and unpacked data for OrbitalChangeTriggered events raised by the Alert contract.
type AlertOrbitalChangeTriggeredIterator struct {
	Event *AlertOrbitalChangeTriggered // Event containing the contract specifics and raw log

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
func (it *AlertOrbitalChangeTriggeredIterator) Next() bool {
	// If the iterator failed, stop iterating
	if it.fail != nil {
		return false
	}
	// If the iterator completed, deliver directly whatever's available
	if it.done {
		select {
		case log := <-it.logs:
			it.Event = new(AlertOrbitalChangeTriggered)
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
		it.Event = new(AlertOrbitalChangeTriggered)
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
func (it *AlertOrbitalChangeTriggeredIterator) Error() error {
	return it.fail
}

// Close terminates the iteration process, releasing any pending underlying
// resources.
func (it *AlertOrbitalChangeTriggeredIterator) Close() error {
	it.sub.Unsubscribe()
	return nil
}

// AlertOrbitalChangeTriggered represents a OrbitalChangeTriggered event raised by the Alert contract.
type AlertOrbitalChangeTriggered struct {
	AlertId     [32]byte
	SatelliteId [32]byte
	Raw         types.Log // Blockchain specific contextual infos
}

// FilterOrbitalChangeTriggered is a free log retrieval operation binding the contract event 0x81572d6c1fa03021dc3b4027db165f27d517c9cab060f25c9266d46c397df04c.
//
// Solidity: event OrbitalChangeTriggered(bytes32 indexed alertId, bytes32 satelliteId)
func (_Alert *AlertFilterer) FilterOrbitalChangeTriggered(opts *bind.FilterOpts, alertId [][32]byte) (*AlertOrbitalChangeTriggeredIterator, error) {

	var alertIdRule []interface{}
	for _, alertIdItem := range alertId {
		alertIdRule = append(alertIdRule, alertIdItem)
	}

	logs, sub, err := _Alert.contract.FilterLogs(opts, "OrbitalChangeTriggered", alertIdRule)
	if err != nil {
		return nil, err
	}
	return &AlertOrbitalChangeTriggeredIterator{contract: _Alert.contract, event: "OrbitalChangeTriggered", logs: logs, sub: sub}, nil
}

// WatchOrbitalChangeTriggered is a free log subscription operation binding the contract event 0x81572d6c1fa03021dc3b4027db165f27d517c9cab060f25c9266d46c397df04c.
//
// Solidity: event OrbitalChangeTriggered(bytes32 indexed alertId, bytes32 satelliteId)
func (_Alert *AlertFilterer) WatchOrbitalChangeTriggered(opts *bind.WatchOpts, sink chan<- *AlertOrbitalChangeTriggered, alertId [][32]byte) (event.Subscription, error) {

	var alertIdRule []interface{}
	for _, alertIdItem := range alertId {
		alertIdRule = append(alertIdRule, alertIdItem)
	}

	logs, sub, err := _Alert.contract.WatchLogs(opts, "OrbitalChangeTriggered", alertIdRule)
	if err != nil {
		return nil, err
	}
	return event.NewSubscription(func(quit <-chan struct{}) error {
		defer sub.Unsubscribe()
		for {
			select {
			case log := <-logs:
				// New log arrived, parse the event and forward to the user
				event := new(AlertOrbitalChangeTriggered)
				if err := _Alert.contract.UnpackLog(event, "OrbitalChangeTriggered", log); err != nil {
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

// ParseOrbitalChangeTriggered is a log parse operation binding the contract event 0x81572d6c1fa03021dc3b4027db165f27d517c9cab060f25c9266d46c397df04c.
//
// Solidity: event OrbitalChangeTriggered(bytes32 indexed alertId, bytes32 satelliteId)
func (_Alert *AlertFilterer) ParseOrbitalChangeTriggered(log types.Log) (*AlertOrbitalChangeTriggered, error) {
	event := new(AlertOrbitalChangeTriggered)
	if err := _Alert.contract.UnpackLog(event, "OrbitalChangeTriggered", log); err != nil {
		return nil, err
	}
	event.Raw = log
	return event, nil
}
