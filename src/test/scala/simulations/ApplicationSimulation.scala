package simulations

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class ApplicationSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:74.0) Gecko/20100101 Firefox/74.0")

  val getAllPersons = "Find all Persons Request"
  val addPersonRequest = "Add Person Request"

  val scn = scenario("ApplicationSimulation")
    .repeat(5000) {
      exec(http(getAllPersons)
        .get("/api/all")
        .resources(http(addPersonRequest)
          .post("/api/add")
        )
      )
    }

  setUp(scn.inject(atOnceUsers(1)))
    .protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(400),
      global.successfulRequests.percent.gt(95)
    )
}