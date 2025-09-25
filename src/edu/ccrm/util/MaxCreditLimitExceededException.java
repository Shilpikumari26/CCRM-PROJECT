package edu.ccrm.util;

public class MaxCreditLimitExceededException extends Exception {
    private final int currentCredits;
    private final int maxAllowed;
    
    public MaxCreditLimitExceededException(int currentCredits, int maxAllowed) {
        super(String.format("Credit limit exceeded. Current: %d, Max allowed: %d", 
              currentCredits, maxAllowed));
        this.currentCredits = currentCredits;
        this.maxAllowed = maxAllowed;
    }
    
    public int getCurrentCredits() { return currentCredits; }
    public int getMaxAllowed() { return maxAllowed; }
}