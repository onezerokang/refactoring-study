package chap1;

import java.util.List;

public record Invoice(String customer, List<Performance> performances) {
}
