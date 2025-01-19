package chap1;

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
            int thisAmount = amountFor(perf);
            // 포인트를 적립한다.
            volumeCredits += Math.max(perf.audience() - 30, 0);
            // 희극 관객 5명마다 추가 포인트를 제공한다.
            if ("comedy".equals(playFor(perf).type())) {
                volumeCredits += perf.audience() / 5;
            }

            // 청구 내역을 출력한다.
            result.append(" %s: %d (%d석)\n".formatted(playFor(perf).name(), thisAmount, perf.audience()));
            totalAmount += thisAmount;
        }
        result.append("총액: %d\n".formatted(totalAmount));
        result.append("적립 포인트: %d점\n".formatted(volumeCredits));
        return result.toString();
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
