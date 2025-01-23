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

    public String htmlStatement() {
        return renderHtml(new StatementData(invoice, plays));
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

    private String renderHtml(final StatementData data) {
        final StringBuilder result = new StringBuilder("<h1>청구 내역 (고객명: %s)</h1>\n".formatted(data.getCustomer()));
        result.append("<table>\n");
        result.append("<tr><th>연극</th><th>좌석 수</th><th>금액</th></tr>");

        for (final EnrichedPerformance perf : data.getPerformances()) {
            // 청구 내역을 출력한다.
            result.append(" <tr><td>%s</td><td>%s</td>\n".formatted(perf.getPlay().name(), perf.getAudience()));
            result.append("<td>%s</td></tr>\n".formatted(usd(perf.getAmount())));
        }
        result.append("</table>\n");
        result.append("<p>총액: %s</p>\n".formatted(usd(data.getTotalAmount())));
        result.append("<p>적립 포인트: %d점</p>\n".formatted(data.getTotalVolumeCredits()));
        return result.toString();
    }

    private static String usd(final int aNumber) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(aNumber / 100);
    }
}
