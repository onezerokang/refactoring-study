package chap1;

public class EnrichedPerformance {
    private final String playId;
    private final int audience;

    public EnrichedPerformance(final String playId, final int audience) {
        this.playId = playId;
        this.audience = audience;
    }

    public String getPlayId() {
        return playId;
    }

    public int getAudience() {
        return audience;
    }
}
