package edu.brandeis.cosi103a.ip2;

/**
 * Represents an automation card in the cryptocurrency card game.
 * Automation cards are worth Automation Points at the end of the game, which determine the winner.
 * Known Bugs: None
 * @author Annika Bergstrom with GitHub Copilot
 * annikabergstrom@brandeis.edu
 * January 26, 2026
 * COSI 103a IP2
 */
public class AutomationCard {
    private int methodNum;
    private int moduleNum;
    private int frameworkNum;
    
    /**
     * Constructs a new AutomationCard with the specified properties.
     * @param methodNum the number or value of Method cards
     * @param moduleNum the number or value of Module cards
     * @param frameworkNum the number or value of Framework cards
     */
    public AutomationCard(int methodNum, int moduleNum, int frameworkNum) {
        this.methodNum = methodNum;
        this.moduleNum = moduleNum;
        this.frameworkNum = frameworkNum;
    }

    /**
     * Returns framework card count
     * @return
     */
    public int getNumberFrameworkCards() {
        return frameworkNum;
    }
    
    /**
     * Retuns the cound of specified card type. 0 else
     * @param cardName String of card name
     * @return number or value of cards of that type
     */
    public int getCount(String cardName) {
        switch (cardName) {
            case "Method": return methodNum;
            case "Module": return moduleNum;
            case "Framework": return frameworkNum;
            default: return 0;
        }
    }
    
    /**
     * Decrements the count of the specified card type by 1
     * @param cardName String of card name
     */
    public void decrementCount(String cardName) {
        switch (cardName) {
            case "Method": 
                if (methodNum > 0) methodNum--;
                break;
            case "Module": 
                if (moduleNum > 0) moduleNum--;
                break;
            case "Framework": 
                if (frameworkNum > 0) frameworkNum--;
                break;
        }
    }
    
    /**
     * Returns the cost of the specified card type
     * @param cardName String of card name
     * @return cost of the card type
     */
    public int getCost(String cardName) {
        switch (cardName) {
            case "Method": return 2;
            case "Module": return 5;
            case "Framework": return 8;
            default: return -1;
        }
    }
    
    /**
     * Returns the value of the specified card type
     * @param cardName String of card name
     * @return value of the card type
     */
    public int getValue(String cardName) {
        switch (cardName) {
            case "Method": return 1;
            case "Module": return 3;
            case "Framework": return 6;
            default: return -1;
        }
    }
}
