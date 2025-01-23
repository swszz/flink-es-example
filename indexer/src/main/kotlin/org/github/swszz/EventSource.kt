package org.github.swszz

import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.springframework.util.PathMatcher

@Component
class EventSource(
    private val mongoTemplate: MongoTemplate,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val pathMatcher: PathMatcher,
) {
    @KafkaListener(topics = ["cdc.ats.candidate"])
    fun listenCandidateCdc(record: ConsumerRecord<Any, Any>) {
        mongoTemplate.save(record.value(), "candidate_captures")
        (ObjectMappers.DEFAULT.readValue(record.value() as String) as Map<String, Any?>)
            .let { it["payload"]?.let { it as Map<*, *> } }
            .let { it?.get("after")?.let { it as Map<*, *> } }
            .let { it?.get("id")?.let { it as Int } }
            ?.let { applicationEventPublisher.publishEvent(CandidateIndexRequest(it.toLong())) }

    }

    @KafkaListener(topics = ["cdc.ats.evaluation"])
    fun listenEvaluationCdc(record: ConsumerRecord<Any, Any>) {
        mongoTemplate.save(record.value(), "evaluation_captures")
        (ObjectMappers.DEFAULT.readValue(record.value() as String) as Map<String, Any?>)
            .let { it["payload"]?.let { it as Map<*, *> } }
            .let { it?.get("after")?.let { it as Map<*, *> } }
            .let { it?.get("id")?.let { it as Int } }
            ?.let { applicationEventPublisher.publishEvent(EvaluationIndexRequest(it.toLong())) }
    }

    @KafkaListener(topics = ["cdc.ats.candidate_evaluation"])
    fun listenCandidateEvaluationCdc(record: ConsumerRecord<Any, Any>) {
        mongoTemplate.save(record.value(), "candidate_evaluation_captures")
        (ObjectMappers.DEFAULT.readValue(record.value() as String) as Map<String, Any?>)
            .let { it["payload"]?.let { it as Map<*, *> } }
            .let { it?.get("after")?.let { it as Map<*, *> } }
            .let { it?.get("evaluation_id")?.let { it as Int } }
            ?.let { applicationEventPublisher.publishEvent(EvaluationIndexRequest(it.toLong())) }
    }

    data class CandidateIndexRequest(val candidateId: Long)
    data class EvaluationIndexRequest(val evaluationId: Long)
}