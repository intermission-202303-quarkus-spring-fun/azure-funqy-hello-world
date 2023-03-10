package cloud.intermission.quarkus;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class AmortizationLoadTest extends Simulation {
  private HttpProtocolBuilder httpProtocol = http
    .baseUrl(System.getProperty("targetUrl"))
    .inferHtmlResources()
    .acceptHeader("*/*")
    .contentTypeHeader("application/json")
    .userAgentHeader("curl/7.79.1");
  private ScenarioBuilder scn = scenario("RecordedSimulation")
    .exec(
      http("request_0")
        .post("/api/amortization")
        .body(
          StringBody("""
              {
                  "totalLoan": "100.0",
                  "currentInterestRate": "0.01",
                  "repaymentPerYear": "50.0"
              }
              """
          )
        )
    );

  {
      /*
      setUp(scn.injectOpen(
        atOnceUsers(1)
      )).protocols(httpProtocol);
*/

      setUp(scn.injectOpen(
          rampUsersPerSec(0).to(50).during(60),
          constantUsersPerSec(50).during(600)
      )).protocols(httpProtocol);
  }
}
