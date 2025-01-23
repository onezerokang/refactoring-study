package chap1;

public class EnrichedPerformance {
    private final String playId;
    private final int audience;
    private final Play play;

    public EnrichedPerformance(final String playId, final int audience, final Play play) {
        this.playId = playId;
        this.audience = audience;
        this.play = play;
    }

    public String getPlayId() {
        return playId;
    }

    public int getAudience() {
        return audience;
    }
}
