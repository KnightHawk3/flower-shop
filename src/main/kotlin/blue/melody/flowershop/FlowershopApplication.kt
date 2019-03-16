package blue.melody.flowershop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan(basePackages = ["blue.melody.flowershop.model"])
class FlowershopApplication

fun main(args: Array<String>) {
    runApplication<FlowershopApplication>(*args)
}
