package cloud.intermission.quarkus;

import cloud.intermission.quarkus.amortization.Loan;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class MyFunctionsTest {

    @Test
    public void testFun() {
        given()
            .post("/fun")
            .then()
            .statusCode(200)
            .body(containsString("Hello Funqy!"));
    }

    @Test
    public void testFunWithName() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"name\": \"Friend\"}")
            .post("/fun")
            .then()
            .statusCode(200)
            .body(containsString("Hello Friend!"));
    }

    @Test
    public void testRepayment() {
        var sut = new MyFunctions();

        var loan = new Loan(100.0d, 0.10d, 50.0d);

        var result = sut.amortization(loan);

        assertEquals(3, result.years());
    }

}
