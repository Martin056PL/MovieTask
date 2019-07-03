package pl.com.tt.restapp.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ActorDivider {

    public static List<String> actorDivider(List<String> actors) {

        if (actors.size() == 1) {
            String singleRow = actors.get(0);
            if (singleRow.contains(",")) {
                String[] array = singleRow.split(",");
                return Arrays.stream(array).map(String::trim).collect(Collectors.toList());
            }
        }
        return actors;
    }
}
