package chap1;

import java.util.HashMap;
import java.util.Map;

public class Plays {
    private final Map<String, Play> plays = new HashMap<>();

    public void addPlay(final String playId, final Play play) {
        plays.put(playId, play);
    }

    public Play getPlay(String playId) {
        return plays.get(playId);
    }
}


