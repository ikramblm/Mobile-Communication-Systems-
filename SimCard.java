 package pooproject;
public class SimCard {
    private final String phoneNumber;
    private boolean activated;
    private double creditBalance;

    public SimCard(String phoneNumber, double initialCredit) {
        this.phoneNumber = phoneNumber;
        this.creditBalance = initialCredit;
        this.activated = true;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isActivated() {
        return activated;
    }

    public void deactivate() {
        activated = false;
    }

    public boolean hasSufficientCredit() {
        // Assume each call costs 1.0 credit unit
        return creditBalance >= 1.0;
    }

    public void deductCredit() {
        if (hasSufficientCredit()) {
            creditBalance -= 1.0;
            System.out.println("Credit deducted. Remaining credit: " + creditBalance);
        } else {
            System.out.println("Insufficient credit to deduct.");
        }
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void addCredit(double amount) {
        creditBalance += amount;
    }
}
