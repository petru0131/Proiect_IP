import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
class FlyweightCandyFactory {
    private Map<String, FlyweightCandy> candies = new HashMap<>();

    public FlyweightCandy getCandy(String imageUrl) {
        String key = imageUrl.toString();
        if (!candies.containsKey(key)) {
            candies.put(key, new FlyweightCandy(new ImageIcon(imageUrl)));

        }
        return candies.get(key);
    }
}