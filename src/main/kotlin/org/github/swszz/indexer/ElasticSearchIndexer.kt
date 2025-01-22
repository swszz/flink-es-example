package org.github.swszz.indexer

import org.apache.flink.api.common.eventtime.WatermarkStrategy
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.connector.kafka.source.KafkaSource
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.springframework.context.annotation.Configuration
import java.util.regex.Pattern

@Configuration
class ElasticSearchIndexer {
    fun index() {
        val environment = StreamExecutionEnvironment.getExecutionEnvironment()
        val kafkaSource = KafkaSource.builder<String>()
            .setBootstrapServers("localhost:9092")
            .setTopicPattern(Pattern.compile("cdc.flink.*"))
            .setGroupId("test")
            .setValueOnlyDeserializer(SimpleStringSchema())
            .build()

        val datastream = environment.fromSource(kafkaSource, WatermarkStrategy.noWatermarks(), "CDC Source")
        datastream.print()
        environment.execute("Kafka Consumer Example")
    }
}