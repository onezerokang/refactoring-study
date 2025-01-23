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
        return renderPlainText(new StatementData(invoice, plays));
    }

    private String renderPlainText(final StatementData data) {
        final StringBuilder result = new StringBuilder("청구 내역 (고객명: %s)\n".formatted(data.getCustomer()));

        for (final EnrichedPerformance perf : data.getPerformances()) {
            // 청구 내역을 출력한다.
            result.append(" %s: %s (%d석)\n".formatted(perf.getPlay().name(), usd(perf.getAmount()), perf.getAudience()));
        }
        result.append("총액: %s\n".formatted(usd(data.getTotalAmount())));
        result.append("적립 포인트: %d점\n".formatted(data.getTotalVolumeCredits()));
        return result.toString();
    }

    private static String usd(final int aNumber) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(aNumber / 100);
    }
}
