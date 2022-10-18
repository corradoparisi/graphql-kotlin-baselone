package ch.baselone.demo

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Query
import kotlinx.coroutines.delay
import org.springframework.stereotype.Component

@Component
class ConferenceQuery : Query {

    fun conference() = Conference("BaselOne", location = "Basel")

    fun people(nameStartsWith: String? = null): List<People> = listOf(
        Speaker("Corrado", listOf("GraphQL mit Kotlin und Spring Boot")),
        Speaker("Matthias", listOf("Sichere Spring Security Applikationen")),
        Attendee("Marton", TicketType.CONFERENCE),
        Attendee("Dominik", TicketType.WORKSHOP),
        Attendee("Nico", TicketType.FULL),
        Attendee("Elisa", TicketType.WORKSHOP),
        Attendee("Sanjay", TicketType.WORKSHOP),
        Attendee("Eslem", TicketType.CONFERENCE),
        Attendee("Sandro", TicketType.CONFERENCE)
    ).filter { it.name.startsWith(nameStartsWith ?: "") }

    fun schedule() = ScheduleDetails(
        greeting = "Willkommen an der BaselOne",
    )
}

class ScheduleDetails(val greeting: String) {
    suspend fun talks(): List<String> {
        delay(1000)
        return listOf("GraphQL mit Kotlin und Spring Boot", "Java Tutorial", "Kotlin Tutorial")
    }
}


interface People {
    val name: String
}

enum class TicketType {
    CONFERENCE,
    WORKSHOP,
    FULL
}

data class Speaker(override val name: String, val talks: List<String>) : People

data class Attendee(override val name: String, val ticketType: TicketType) : People

data class Conference(
    @GraphQLDescription("my super **awesome** and incredible `conference` name") // markdown syntax, also multiline strings
    val name: String,
    @Deprecated("not needed anymore")
    val location: String?
)
