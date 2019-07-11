package pl.com.tt.restapp.utils.generators;

import java.util.HashMap;
import java.util.Map;

public enum MovieType {

    Action(1),
    SciFi(2),
    Horror(3),
    Romans(4),
    Drama(5),
    Animation(6),
    Thriller(7),
    Western(8),
    Biography(9),
    Erotic(10),
    Catastrophic(11),
    Comedy(12),
    Adventure(13),
    Moral(14),
    Musical(15),
    Document(16),
    Family(17),
    Fantasy(18),
    Gore(19),
    Criminal(20),
    Psychological(21),
    RomanticComedy(22);

    private int type;
    private static Map map = new HashMap<>();

    MovieType(int type) {
        this.type = type;
    }

    static {
        for (MovieType movieType : MovieType.values()) {
            map.put(movieType.type, movieType);
        }
    }

    public static MovieType valueOf(int movieType) {
        return (MovieType) map.get(movieType);
    }

    public int getValue() {
        return type;
    }

}
