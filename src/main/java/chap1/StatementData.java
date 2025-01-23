package chap1;

import java.util.ArrayList;
import java.util.List;

public class StatementData {
    private final String customer;
    private final List<EnrichedPerformance> performances = new ArrayList<>();
    private final Plays plays;

    public StatementData(final Invoice invoice, final Plays plays) {
        this.customer = invoice.customer();
        this.performances.addAll(enrichPerformance(invoice.performances()));
        this.plays = plays;
    }

    private static List<EnrichedPerformance> enrichPerformance(final List<Performance> performances) {
        return performances.stream()
                .map(p -> new EnrichedPerformance(p.playId(), p.audience()))
                .toList();
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
