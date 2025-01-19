package chap1;

import java.text.NumberFormat;
import java.util.Locale;

public class Statement {

    private final Invoice invoice;
    private final Plays plays;

    public Statement(final Invoice invoice, final Plays plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public String statement() {
        int totalAmount = 0;
        int volumeCredits = 0;
        final StringBuilder result = new StringBuilder("청구 내역 (고객명: %s)\n".formatted(invoice.customer()));

        for (final Performance perf : invoice.performances()) {
            volumeCredits += volumeCreditsFor(perf);

            // 청구 내역을 출력한다.
            result.append(" %s: %s (%d석)\n".formatted(playFor(perf).name(), usd(amountFor(perf)), perf.audience()));
            totalAmount += amountFor(perf);
        }
        result.append("총액: %s\n".formatted(usd(totalAmount)));
        result.append("적립 포인트: %d점\n".formatted(volumeCredits));
        return result.toString();
    }

    private static String usd(final int aNumber) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(aNumber / 100);
    }

    private int volumeCreditsFor(final Performance aPerformance) {
        int result = 0;
        result += Math.max(aPerformance.audience() - 30, 0);
        if ("comedy".equals(playFor(aPerformance).type())) {
            result += aPerformance.audience() / 5;
        }
        return result;
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
}
