package edu.brandeis.cosi103a.ip2;

/**
 * Represents an automation card in the cryptocurrency card game.
 * Automation cards are worth Automation Points at the end of the game,
 * which determine the winner.
 */
public class AutomationCard {
    private String method = "Method";
    private int methodNum;
    private String module = "Module";
    private int moduleNum;
    private String framework = "Framework";
    private int frameworkNum;
    
    public AutomationCard(int methodNum, int moduleNum, int frameworkNum) {
        this.methodNum = methodNum;
        this.moduleNum = moduleNum;
        this.frameworkNum = frameworkNum;
    }

    public int getNumberFrameworkCards() {
        return frameworkNum;
    }
    
    public int getCount(String cardName) {
        switch (cardName) {
            case "Method": return methodNum;
            case "Module": return moduleNum;
            case "Framework": return frameworkNum;
            default: return 0;
        }
    }
    
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
    
    public int getCost(String cardName) {
        switch (cardName) {
            case "Method": return 2;
            case "Module": return 5;
            case "Framework": return 8;
            default: return -1;
        }
    }
    
    public int getValue(String cardName) {
        switch (cardName) {
            case "Method": return 1;
            case "Module": return 3;
            case "Framework": return 6;
            default: return -1;
        }
    }
}
