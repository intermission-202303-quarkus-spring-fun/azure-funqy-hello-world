package cloud.intermission.quarkus.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record Invoice (
    Company from,
    Company to,
    LocalDate date,
    LocalDate due,
    String attention,
    String id,
    List<InvoiceItem> items,
    BigDecimal subTotal,
    BigDecimal taxRate,
    BigDecimal taxAmount,
    BigDecimal total,
    String notice
) {
    record InvoiceItem (
            String description,
            int quantity,
            BigDecimal unitPrice,
            BigDecimal totalPrice
    ) {}
}
