package org.github.swszz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.github.swszz"])
internal class AtsApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<AtsApplication>(*args)
        }
    }
}