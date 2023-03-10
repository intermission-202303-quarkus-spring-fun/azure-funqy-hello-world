package cloud.intermission.quarkus;

import cloud.intermission.quarkus.amortization.Loan;
import cloud.intermission.quarkus.invoice.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import io.quarkus.funqy.Funq;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;

@ApplicationScoped
public class MyFunctions {

    @Inject
    ObjectMapper objectMapper;

    @Inject
    TemplateEngine templateEngine;


    @Funq
    public String fun(FunInput input) {
        return String.format("Hello %s!", input != null ? input.getName() : "Funqy");
    }

    @Funq
    public String render(Invoice invoice) {

        var myContext = new Context();
        myContext.setVariable("invoice", invoice);

        var html = templateEngine.process("invoice", myContext);

        var bytes = htmlToPdf(html);

        return bytes2Base64(bytes);
    }

    @Funq
    public Loan.Amortization amortization(Loan loan) {
        return loan.amortization();
    }

    private byte[] htmlToPdf(String html) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            var builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, null);
            builder.toStream(os);
            builder.run();
            os.flush();
            return os.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String bytes2Base64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
