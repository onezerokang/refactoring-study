package chap1;

public class EnrichedPerformance {
    private final String playId;
    private final int audience;
    private final Play play;
    private final int amount;
    private final int volumeCredits;

    public EnrichedPerformance(
            final String playId,
            final int audience,
            final Play play,
            final int amount,
            final int volumeCredits
    ) {
        this.playId = playId;
        this.audience = audience;
        this.play = play;
        this.amount = amount;
        this.volumeCredits = volumeCredits;
    }

    public String getPlayId() {
        return playId;
    }

    public int getAudience() {
        return audience;
    }

    public Play getPlay() {
        return play;
    }

    public int getAmount() {
        return amount;
    }

    public int getVolumeCredits() {
        return volumeCredits;
    }
}
