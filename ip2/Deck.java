import java.util.Map;

public class Deck {
    private Map<String, Integer> AutomationCards = Map.of(
        "Method", 14,
        "Module", 8,
        "Framework", 8
    ); 
    private Map<String, Integer> CryptocurrencyCards = Map.of(
        "Bitcoin", 60,
        "Etherium", 40,
        "Dogecoin", 30
    ); 
    
    public Deck() {
        
    }

    public int getNumberFrameworkCards() {
        return AutomationCards.get("Framework");
    }
}
