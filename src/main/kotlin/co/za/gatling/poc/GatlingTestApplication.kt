package co.za.gatling.poc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GatlingTestApplication

fun main(args: Array<String>) {
	runApplication<GatlingTestApplication>(*args)
}
