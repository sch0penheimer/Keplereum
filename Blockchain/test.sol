// SPDX-License-Identifier: MIT
pragma solidity ^0.8.17;

/**
 * @title SatelliteRegistry
 * @dev Manages satellite identities and their current status
 */
contract SatelliteRegistry {
    struct Satellite {
        address publicKey;      // Ethereum address of the satellite
        bool active;            // Whether satellite is active in the network
        uint256 lastPing;       // Last activity timestamp
        bytes32 currentPosition; // Encoded orbital parameters
    }
    
    mapping(bytes32 => Satellite) public satellites;
    bytes32[] public satelliteIds;
    
    event SatelliteRegistered(bytes32 indexed satelliteId, address publicKey);
    event SatelliteStatusChanged(bytes32 indexed satelliteId, bool active);
    event PositionUpdated(bytes32 indexed satelliteId, bytes32 position);
    

    // Add this function to the SatelliteRegistry contract to allow accessing the publicKey
    function getPublicKey(bytes32 satelliteId) public view returns (address) {
        return satellites[satelliteId].publicKey;
    }


    // Register a new satellite
    function registerSatellite(bytes32 satelliteId) public {
        require(satellites[satelliteId].publicKey == address(0), "Satellite already registered");
        
        satellites[satelliteId] = Satellite({
            publicKey: msg.sender,
            active: true,
            lastPing: block.timestamp,
            currentPosition: bytes32(0)
        });
        
        satelliteIds.push(satelliteId);
        emit SatelliteRegistered(satelliteId, msg.sender);
    }
    
    // Update satellite position
    function updatePosition(bytes32 satelliteId, bytes32 position) public {
        require(satellites[satelliteId].publicKey == msg.sender, "Unauthorized");
        satellites[satelliteId].currentPosition = position;
        satellites[satelliteId].lastPing = block.timestamp;
        emit PositionUpdated(satelliteId, position);
    }
    
    // Get all active satellites
    function getActiveSatellites() public view returns (bytes32[] memory) {
        uint activeCount = 0;
        
        // Count active satellites
        for (uint i = 0; i < satelliteIds.length; i++) {
            if (satellites[satelliteIds[i]].active) {
                activeCount++;
            }
        }
        
        // Create result array
        bytes32[] memory activeSats = new bytes32[](activeCount);
        uint index = 0;
        
        // Fill result array
        for (uint i = 0; i < satelliteIds.length; i++) {
            if (satellites[satelliteIds[i]].active) {
                activeSats[index] = satelliteIds[i];
                index++;
            }
        }
        
        return activeSats;
    }
}

/**
 * @title AlertSystem
 * @dev Manages alert creation, validation and orbital change commands
 */
contract AlertSystem {
    struct Alert {
        bytes32 alertId;
        bytes32 satelliteId;    // Satellite that created the alert
        uint256 timestamp;
        bytes coordinates;      // Encoded lat/long
        bytes32 alertType;      // Type of alert (wildfire, etc)
        bytes alertData;        // Raw sensor data
        bool executed;          // Whether an orbital change was executed
        uint256 validations;    // Count of validations
    }
    
    struct Validation {
        bytes32 alertId;
        bytes32 validatorId;    // Satellite that validated
        bool isValid;           // Their decision
    }
    
    mapping(bytes32 => Alert) public alerts;
    bytes32[] public alertIds;
    
    // Track validations: alertId => validatorId => validation
    mapping(bytes32 => mapping(bytes32 => Validation)) public validations;
    
    // Reference to the satellite registry
    SatelliteRegistry public satelliteRegistry;
    
    // Threshold for validation (percentage, 1-100)
    uint8 public validationThreshold = 51;
    
    event AlertCreated(bytes32 indexed alertId, bytes32 satelliteId);
    event AlertValidated(bytes32 indexed alertId, bytes32 validatorId, bool decision);
    event OrbitalChangeTriggered(bytes32 indexed alertId, bytes32 satelliteId);
    
    constructor(address _registryAddress) {
        satelliteRegistry = SatelliteRegistry(_registryAddress);
    }


    function getAlertSatelliteId(bytes32 alertId) public view returns (bytes32) {
        return alerts[alertId].satelliteId;
    }
    
    // Create a new alert
    function createAlert(
        bytes32 alertId,
        bytes calldata coordinates,
        bytes32 alertType,
        bytes calldata alertData
    ) public {
        // Find satellite ID based on sender address
        bytes32 satelliteId = getSatelliteIdFromAddress(msg.sender);
        require(satelliteId != bytes32(0), "Sender not a registered satellite");
        
        // Create alert
        alerts[alertId] = Alert({
            alertId: alertId,
            satelliteId: satelliteId,
            timestamp: block.timestamp,
            coordinates: coordinates,
            alertType: alertType,
            alertData: alertData,
            executed: false,
            validations: 0
        });
        
        alertIds.push(alertId);
        emit AlertCreated(alertId, satelliteId);
    }
    
    // Validate an alert
    function validateAlert(bytes32 alertId, bool decision) public {
        bytes32 validatorId = getSatelliteIdFromAddress(msg.sender);
        require(validatorId != bytes32(0), "Validator not a registered satellite");
        require(alerts[alertId].alertId == alertId, "Alert does not exist");
        
        // Record validation
        validations[alertId][validatorId] = Validation(alertId, validatorId, decision);
        
        if (decision) {
            alerts[alertId].validations += 1;
        }
        
        emit AlertValidated(alertId, validatorId, decision);
        
        // Check if threshold is met
        if (meetsValidationThreshold(alertId)) {
            triggerOrbitalChange(alertId);
        }
    }
    
    // Check if alert meets validation threshold
    function meetsValidationThreshold(bytes32 alertId) public view returns (bool) {
        uint256 activeCount = satelliteRegistry.getActiveSatellites().length;
        return (alerts[alertId].validations * 100) / activeCount >= validationThreshold;
    }
    
    // Trigger orbital change
    function triggerOrbitalChange(bytes32 alertId) internal {
        require(!alerts[alertId].executed, "Orbital change already executed");
        
        alerts[alertId].executed = true;
        emit OrbitalChangeTriggered(alertId, alerts[alertId].satelliteId);
        
        // In a real system, this would trigger the orbital change mechanism
        // For this prototype, we just emit the event
    }
    
    // Helper to find satellite ID from Ethereum address
    function getSatelliteIdFromAddress(address satelliteAddress) internal view returns (bytes32) {
        bytes32[] memory activeSats = satelliteRegistry.getActiveSatellites();
        
        for (uint i = 0; i < activeSats.length; i++) {
            bytes32 satId = activeSats[i];
            address satAddress = satelliteRegistry.getPublicKey(satId);

            
            if (satAddress == satelliteAddress) {
                return satId;
            }
        }
        
        return bytes32(0);
    }
}

/**
 * @title OrbitalManeuverSystem
 * @dev Manages execution and verification of orbital maneuvers
 */
contract OrbitalManeuverSystem {
    struct Maneuver {
        bytes32 maneuverHash;
        bytes32 alertId;           // Associated alert
        bytes32 satelliteId;       // Target satellite
        bytes parameters;          // Orbital parameters
        uint256 timestamp;
        bool completed;
    }
    
    mapping(bytes32 => Maneuver) public maneuvers;
    bytes32[] public maneuverHashes;
    
    // Reference to other contracts
    AlertSystem public alertSystem;
    SatelliteRegistry public satelliteRegistry;
    
    event ManeuverExecuted(bytes32 indexed maneuverHash, bytes32 satelliteId);
    event ManeuverCompleted(bytes32 indexed maneuverHash, uint256 completionTime);
    
    constructor(address _alertSystemAddress, address _registryAddress) {
        alertSystem = AlertSystem(_alertSystemAddress);
        satelliteRegistry = SatelliteRegistry(_registryAddress);
    }
    
    // Create and execute a maneuver based on alert
    function executeManeuver(bytes32 alertId, bytes calldata parameters) public {
        // Verify caller is authorized (in production would be restricted)
        bytes32 satelliteId = alertSystem.getAlertSatelliteId(alertId);
        require(satelliteId != bytes32(0), "Invalid alert");
        require(alertSystem.meetsValidationThreshold(alertId), "Alert not validated");
        
        // Create maneuver hash
        bytes32 maneuverHash = keccak256(abi.encodePacked(alertId, satelliteId, block.timestamp));
        
        // Store maneuver
        maneuvers[maneuverHash] = Maneuver({
            maneuverHash: maneuverHash,
            alertId: alertId,
            satelliteId: satelliteId,
            parameters: parameters,
            timestamp: block.timestamp,
            completed: false
        });
        
        maneuverHashes.push(maneuverHash);
        emit ManeuverExecuted(maneuverHash, satelliteId);
    }
    
    // Mark maneuver as completed
    function completeManeuver(bytes32 maneuverHash) public {
        Maneuver storage maneuver = maneuvers[maneuverHash];
        require(maneuver.maneuverHash == maneuverHash, "Invalid maneuver");
        
        // In production, this would verify the satellite is who it says it is
        bytes32 satelliteId = maneuver.satelliteId;
        require(satelliteRegistry.getPublicKey(satelliteId) == msg.sender, "Unauthorized");
        
        maneuver.completed = true;
        emit ManeuverCompleted(maneuverHash, block.timestamp);
    }
    
    // Get all pending maneuvers for a satellite
    function getPendingManeuvers(bytes32 satelliteId) public view returns (bytes32[] memory) {
        uint pendingCount = 0;
        
        // Count pending maneuvers
        for (uint i = 0; i < maneuverHashes.length; i++) {
            Maneuver memory maneuver = maneuvers[maneuverHashes[i]];
            if (maneuver.satelliteId == satelliteId && !maneuver.completed) {
                pendingCount++;
            }
        }
        
        // Create result array
        bytes32[] memory pendingManeuvers = new bytes32[](pendingCount);
        uint index = 0;
        
        // Fill result array
        for (uint i = 0; i < maneuverHashes.length; i++) {
            Maneuver memory maneuver = maneuvers[maneuverHashes[i]];
            if (maneuver.satelliteId == satelliteId && !maneuver.completed) {
                pendingManeuvers[index] = maneuver.maneuverHash;
                index++;
            }
        }
        
        return pendingManeuvers;
    }
}