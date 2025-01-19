package chap1;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Statement 클래스")
class StatementTest {

    @Nested
    class Describe_statement {

        @DisplayName("연극의 장르가 비극이나 희극이 아닐 경우")
        @Nested
        class Context_with_not_tragedy_or_comedy {

            @DisplayName("예외를 던진다.")
            @Test
            void it_throws_exception() {
                final Invoice invoice = new Invoice(
                        "test-customer", List.of(new Performance("hamlet", 40))
                );
                final Plays plays = new Plays();
                final Statement statement = new Statement(invoice, plays);
                plays.addPlay("hamlet", new Play("hamlet", "history"));

                assertThatThrownBy(statement::statement)
                        .isInstanceOf(IllegalStateException.class);
            }
        }

        @DisplayName("연극의 장르가 비극이면서")
        @Nested
        class Context_with_tragedy {

            @DisplayName("관객이 30명 이하일 경우")
            @Nested
            class Context_with_audience_under_30 {

                @DisplayName("$400.00의 기본 요금이 부과되고, 포인트는 적립되지 않는다.")
                @Test
                void it() {
                    final Invoice invoice = new Invoice(
                            "test-customer", List.of(new Performance("hamlet", 30)));
                    final Plays plays = new Plays();
                    plays.addPlay("hamlet", new Play("Hamlet", "tragedy"));
                    final Statement statement = new Statement(invoice, plays);

                    final String result = statement.statement();

                    assertThat(result).isEqualTo("""
                            청구 내역 (고객명: test-customer)
                             Hamlet: $400.00 (30석)
                            총액: $400.00
                            적립 포인트: 0점
                            """);
                }
            }

            @DisplayName("관객이 30명 초과일 경우")
            @Nested
            class Context_with_audience_over_30 {

                @DisplayName("$400.00의 기본 요금에 초과 인원 1명당 $10.00가 부과되고, 포인트는 초과인원수 만큼 적립된다.")
                @Test
                void it() {
                    final Invoice invoice = new Invoice(
                            "test-customer", List.of(new Performance("hamlet", 31)));
                    final Plays plays = new Plays();
                    plays.addPlay("hamlet", new Play("Hamlet", "tragedy"));
                    final Statement statement = new Statement(invoice, plays);

                    final String result = statement.statement();

                    assertThat(result).isEqualTo("""
                            청구 내역 (고객명: test-customer)
                             Hamlet: $410.00 (31석)
                            총액: $410.00
                            적립 포인트: 1점
                            """);
                }
            }
        }

        @DisplayName("연극의 장르가 희극이면서")
        @Nested
        class Context_with_comedy {

            @DisplayName("관객이 20명 이하일 경우")
            @Nested
            class Context_with_audience_under_20 {
                @DisplayName("기본 요금($300.00 + $3.00 * 관객수)이 부과되고, 포인트는 (관객수 / 5)만큼 적립된다.")
                @Test
                void it() {
                    final Invoice invoice = new Invoice(
                            "test-customer", List.of(new Performance("as-like", 20)));
                    final Plays plays = new Plays();
                    plays.addPlay("as-like", new Play("As You Like It", "comedy"));
                    final Statement statement = new Statement(invoice, plays);

                    final String result = statement.statement();

                    assertThat(result).isEqualTo("""
                        청구 내역 (고객명: test-customer)
                         As You Like It: $360.00 (20석)
                        총액: $360.00
                        적립 포인트: 4점
                        """);
                }
            }
            @DisplayName("관객이 20명 초과일 경우")
            @Nested
            class Context_with_audience_over_20 {
                @DisplayName("기본 요금($300.00 + $3.00 * 관객수)에 초과 요금($100.00 + $5.00 * 초과인원수), 포인트는 (관객수 / 5)만큼 적립된다.")
                @Test
                void it() {
                    final Invoice invoice = new Invoice(
                            "test-customer", List.of(new Performance("as-like", 21)));
                    final Plays plays = new Plays();
                    plays.addPlay("as-like", new Play("As You Like It", "comedy"));

                    final Statement statement = new Statement(invoice, plays);
                    final String result = statement.statement();

                    assertThat(result).isEqualTo("""
                        청구 내역 (고객명: test-customer)
                         As You Like It: $468.00 (21석)
                        총액: $468.00
                        적립 포인트: 4점
                        """);
                }
            }
        }
    }
}
