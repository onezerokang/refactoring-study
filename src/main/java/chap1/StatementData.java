package chap1;

import java.util.ArrayList;
import java.util.List;

public class StatementData {
    private final String customer;
    private final List<EnrichedPerformance> performances = new ArrayList<>();
    private final Plays plays;

    public StatementData(final Invoice invoice, final Plays plays) {
        this.customer = invoice.customer();
        this.plays = plays;
        this.performances.addAll(enrichPerformance(invoice.performances()));
    }

    private List<EnrichedPerformance> enrichPerformance(final List<Performance> performances) {
        return performances.stream()
                .map(p -> new EnrichedPerformance(p.playId(), p.audience(), playFor(p), amountFor(p)))
                .toList();
    }

    private Play playFor(final Performance perf) {
        return plays.getPlay(perf.playId());
    }

    private int amountFor(final Performance aPerformance) {
        int result = 0;
        switch (playFor(aPerformance).type()) {
            case "tragedy": // 비극
                result = 40000;
                if (aPerformance.audience() > 30) {
                    result += 1000 * (aPerformance.audience() - 30);
                }
                break;
            case "comedy": // 희극
                result = 30000;
                if (aPerformance.audience() > 20) {
                    result += 10000 + 500 * (aPerformance.audience() - 20);
                }
                result += 300 * aPerformance.audience();
                break;
            default:
                throw new IllegalStateException("알 수 없는 장르: %s".formatted(playFor(aPerformance).type()));
        }
        return result;
    }

    public String getCustomer() {
        return customer;
    }

    public List<Performance> getPerformances() {
        return this.performances.stream()
                .map(ep -> new Performance(ep.getPlayId(), ep.getAudience()))
                .toList();
    }
}
