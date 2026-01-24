import java.util.Map;
import java.util.HashMap;

public class Deck {
    private Map<String, Integer> automationCards = new HashMap<>(Map.of(
        "Method", 14,
        "Module", 8,
        "Framework", 8
    ));
    private Map<String, Integer> cryptocurrencyCards = new HashMap<>(Map.of(
        "Bitcoin", 60,
        "Etherium", 40,
        "Dogecoin", 30
    ));

    public Deck() {
    }

    public int getNumberFrameworkCards() {
        return automationCards.getOrDefault("Framework", 0);
    }

    public int getAutomationCount(String name) {
        return automationCards.getOrDefault(name, 0);
    }

    public int getCryptocurrencyCount(String name) {
        return cryptocurrencyCards.getOrDefault(name, 0);
    }

    public Card buyAutomationCard(String name) {
        int count = automationCards.getOrDefault(name, 0);
        if (count <= 0) return null;
        automationCards.put(name, count - 1);
        switch (name) {
            case "Method":
                return new AutomationCard(2, 1, "Method");
            case "Module":
                return new AutomationCard(5, 3, "Module");
            case "Framework":
                return new AutomationCard(8, 6, "Framework");
            default:
                return null;
        }
    }

    public Card buyCryptocurrencyCard(String name) {
        int count = cryptocurrencyCards.getOrDefault(name, 0);
        if (count <= 0) return null;
        cryptocurrencyCards.put(name, count - 1);
        switch (name) {
            case "Bitcoin":
                return new CryptocurrencyCard(0, 1, "Bitcoin");
            case "Etherium":
                return new CryptocurrencyCard(3, 2, "Etherium");
            case "Dogecoin":
                return new CryptocurrencyCard(6, 3, "Dogecoin");
            default:
                return null;
        }
    }
}
