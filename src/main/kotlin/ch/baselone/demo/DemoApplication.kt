package ch.baselone.demo

import graphql.execution.instrumentation.tracing.TracingInstrumentation
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DemoApplication {
	@Bean
	fun tracing() = TracingInstrumentation()
}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
