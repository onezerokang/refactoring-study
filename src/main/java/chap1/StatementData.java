package chap1;

import java.util.ArrayList;
import java.util.List;

public class StatementData {
    private final String customer;
    private final List<Performance> performances = new ArrayList<>();

    public StatementData(final String customer, final List<Performance> performances) {
        this.customer = customer;
        this.performances.addAll(enrichPerformance(performances));
    }

    private static List<Performance> enrichPerformance(final List<Performance> performances) {
        return performances.stream()
                .map(p -> new Performance(p.playId(), p.audience()))
                .toList();
    }

    public String getCustomer() {
        return customer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }
}
