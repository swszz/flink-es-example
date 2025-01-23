package org.github.swszz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.github.swszz"])
internal class IndexerApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<IndexerApplication>(*args)
        }
    }
}