package pooproject;
import java.util.List;

public class Phone {
    private int batteryLevel; // 0-100
    private SimCard simCard;
    private Position location;
    private boolean onCall;
    private Antenna connectedAntenna;
    private Network network;

    public Phone(int batteryLevel, SimCard simCard, Position location, Network network) {
        this.batteryLevel = batteryLevel;
        this.simCard = simCard;
        this.location = location;
        this.network = network;
        this.onCall = false;
        this.connectedAntenna = null;
    }

    // Check if phone can make a call based on all requirements
    public boolean canMakeCall() {
        if (batteryLevel <= 10) {
            System.out.println("Battery too low to make a call.");
            return false;
        }
        if (simCard == null || !simCard.isActivated()) {
            System.out.println("No active SIM card.");
            return false;
        }
        if (!simCard.hasSufficientCredit()) {
            System.out.println("Insufficient credit on SIM card.");
            return false;
        }
        Antenna antenna = network.findNearestAntennaWithCapacity(location);
        if (antenna == null) {
            System.out.println("No antenna in range or no capacity available.");
            return false;
        }
        return true;
    }

    // Check if phone can receive a call
    public boolean canReceiveCall() {
        if (batteryLevel <= 10) {
            System.out.println("Battery too low to receive a call.");
            return false;
        }
        Antenna antenna = network.findNearestAntenna(location);
        if (antenna == null) {
            System.out.println("No antenna in range to receive call.");
            return false;
        }
        return true;
    }

    // Start a stationary call (no position change)
    public boolean startStationaryCall() {
        if (onCall) {
            System.out.println("Already on a call, cannot start another.");
            return false;
        }
        if (!canMakeCall()) {
            return false;
        }
        Antenna antenna = network.findNearestAntennaWithCapacity(location);
        if (antenna == null) {
            System.out.println("No antenna available for call.");
            return false;
        }
        // Connect to antenna
        if (!antenna.incrementActiveCalls()) {
            System.out.println("Antenna capacity full, cannot start call.");
            return false;
        }
        connectedAntenna = antenna;
        onCall = true;
        simCard.deductCredit();
        System.out.println("Stationary call started at location " + location);
        return true;
    }

    // Start a moving call (position changes during call)
    public boolean startMovingCall(List<Position> path) {
        if (onCall) {
            System.out.println("Already on a call, cannot start another.");
            return false;
        }
        if (!canMakeCall()) {
            return false;
        }
        Antenna antenna = network.findNearestAntennaWithCapacity(location);
        if (antenna == null) {
            System.out.println("No antenna available for call.");
            return false;
        }
        if (!antenna.incrementActiveCalls()) {
            System.out.println("Antenna capacity full, cannot start call.");
            return false;
        }
        connectedAntenna = antenna;
        onCall = true;
        simCard.deductCredit();
        System.out.println("Moving call started at location " + location);

        // Simulate moving along the path
        for (Position newPos : path) {
            moveTo(newPos);
            System.out.println("Moved to " + location);
            // Check if still in coverage of connected antenna
            if (!connectedAntenna.isInRange(location)) {
                System.out.println("Moved out of coverage. Call disconnected.");
                endCall();
                return false;
            }
        }
        System.out.println("Call ended normally after moving.");
        endCall();
        return true;
    }

    // Receive an incoming call
    public boolean receiveCall(Phone caller) {
        if (onCall) {
            System.out.println("Incoming call rejected: already on a call.");
            return false;
        }
        if (!canReceiveCall()) {
            System.out.println("Cannot receive call due to battery or coverage.");
            return false;
        }
        onCall = true;
        System.out.println("Incoming call accepted from " + caller.getSimCard().getPhoneNumber());
        return true;
    }

    // End current call
    public void endCall() {
        if (onCall) {
            onCall = false;
            if (connectedAntenna != null) {
                connectedAntenna.decrementActiveCalls();
                connectedAntenna = null;
            }
            System.out.println("Call ended.");
        }
    }

    // Move the phone to a new location
    public void moveTo(Position newLocation) {
        this.location = newLocation;
    }

    public SimCard getSimCard() {
        return simCard;
    }

    public Position getLocation() {
        return location;
    }

    public boolean isOnCall() {
        return onCall;
    }
    
    public int getBatteryLevel() {
        return batteryLevel;
    }
    
    public void chargeBattery(int amount) {
        batteryLevel = Math.min(100, batteryLevel + amount);
    }
}
