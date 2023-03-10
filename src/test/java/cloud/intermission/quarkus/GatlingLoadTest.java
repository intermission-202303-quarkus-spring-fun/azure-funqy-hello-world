package cloud.intermission.quarkus;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class GatlingLoadTest extends Simulation {
  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://xkvexppvsuyh-functions-quarkus.azurewebsites.net")
    .inferHtmlResources()
    .acceptHeader("*/*")
    .contentTypeHeader("application/json")
    .userAgentHeader("curl/7.79.1");
  private ScenarioBuilder scn = scenario("RecordedSimulation")
    .exec(
      http("request_0")
        .post("/api/render")
        .body(
          StringBody("""
                {"from":{"name":"company","logoFile":null,"address":{"line1":"line1","line2":"line2","city":"city","state":"state","country":"county","zipCode":"zip"},"email":"info@company,,com","website":"http://acme.com","phone":"+123456789"},"to":{"name":"company","logoFile":null,"address":{"line1":"line1","line2":"line2","city":"city","state":"state","country":"county","zipCode":"zip"},"email":"info@company,,com","website":"http://acme.com","phone":"+123456789"},"date":"2023-03-10","due":"+999999999-12-31","attention":"attention","id":"id","items":[{"description":"description","quantity":1,"unitPrice":1,"totalPrice":1}],"subTotal":1,"taxRate":0,"taxAmount":0,"total":1,"notice":"notice"}
            """
          )
        )
    );

  {
      setUp(scn.injectOpen(
        atOnceUsers(1)
      )).protocols(httpProtocol);

      /*
      setUp(scn.injectOpen(
          atOnceUsers(1),
          nothingFor(10),
          rampUsersPerSec(0).to(100).during(60),
          constantUsersPerSec(100).during(60)
      )).protocols(httpProtocol);
       */
  }
}
