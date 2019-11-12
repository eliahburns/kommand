package io.github.eliahburns.kommand.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withTimeout
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds
import kotlin.time.minutes

@ExperimentalTime
suspend fun expect(
    timeout: Duration = 3.minutes,
    retryDelay: Duration = 250.milliseconds,
    property: ExpectedProperty
) = try {
    val outcome = withTimeout(timeout.toLongMilliseconds()) {
        while (isActive) {
            if (property.check()) {
                return@withTimeout true
            }
            delay(retryDelay.inMilliseconds.toLong())
        }
        false
    }
    ExpectedPropertyTestOutcome(property, outcome)
} catch (e: Exception) {
    ExpectedPropertyTestOutcome(property, false, "failed while checking ${property.name}", e)
}

typealias ExpectedPropertyTest = suspend () -> Boolean

data class ExpectedProperty(
    val name: String,
    val check: ExpectedPropertyTest
)

data class ExpectedPropertyTestOutcome(
    val property: ExpectedProperty,
    val passed: Boolean,
    val info: String = "",
    val throwable: Throwable? = null
)

