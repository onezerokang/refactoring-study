package chap1;

public class EnrichedPerformance {
    private final String playId;
    private final int audience;
    private final Play play;
    private final int amount;

    public EnrichedPerformance(final String playId, final int audience, final Play play, final int amount) {
        this.playId = playId;
        this.audience = audience;
        this.play = play;
        this.amount = amount;
    }

    public String getPlayId() {
        return playId;
    }

    public int getAudience() {
        return audience;
    }
}
