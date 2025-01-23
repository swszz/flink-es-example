package org.github.swszz

import org.github.swszz.collection.*
import org.springframework.context.event.EventListener
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class Indexer(
    private val mongoTemplate: MongoTemplate,
) {

    @EventListener(EventSource.CandidateIndexRequest::class)
    fun indexCandidate(event: EventSource.CandidateIndexRequest) {
        val candidateCaptureQuery = Query()
            .addCriteria(Criteria.where("payload.after.id").`is`(event.candidateId))
            .with(Sort.by(Sort.Direction.DESC, "payload.after.updated_at"))
            .limit(1)

        val candidateCapture = mongoTemplate.findOne(candidateCaptureQuery, CandidateCapture::class.java)

        if (candidateCapture == null) {
            println("No candidate found for candidate ${event.candidateId}")
            return
        }

        val candidateQuery = Query()
            .addCriteria(Criteria.where("id").`is`(candidateCapture.payload.after?.id))
            .with(Sort.by(Sort.Direction.DESC, "payload.after.updated_at"))
            .limit(1)

        val candidate = mongoTemplate.findOne(candidateQuery, Candidate::class.java)

        if (candidate == null) {
            mongoTemplate.save(
                Candidate(
                    candidateId = candidateCapture.payload.after?.id,
                    email = candidateCapture.payload.after?.email,
                    name = candidateCapture.payload.after?.name,
                    evaluations = emptyList()
                )
            )
        } else {
            mongoTemplate.save(
                candidate.copy(
                    email = candidateCapture.payload.after?.email,
                    name = candidateCapture.payload.after?.name
                )
            )
        }

    }


    @EventListener(EventSource.EvaluationIndexRequest::class)
    fun indexEvaluation(event: EventSource.EvaluationIndexRequest) {
        val relationQuery = Query()
            .addCriteria(Criteria.where("payload.after.evaluation_id").`is`(event.evaluationId))
            .with(Sort.by(Sort.Direction.DESC, "payload.after.updated_at"))
            .limit(1)

        val relationCapture = mongoTemplate.findOne(relationQuery, CandidateEvaluationCapture::class.java)

        if (relationCapture == null) {
            println("No relation found for evaluation ${event.evaluationId}")
            return
        }

        val evaluationQuery = Query()
            .addCriteria(Criteria.where("payload.after.id").`is`(event.evaluationId))
            .with(Sort.by(Sort.Direction.DESC, "payload.after.updated_at"))
            .limit(1)

        val evaluationCapture = mongoTemplate.findOne(evaluationQuery, EvaluationCapture::class.java)

        if (evaluationCapture == null) {
            println("No evaluation found for evaluation ${event.evaluationId}")
            return
        }

        val candidateCaptureQuery = Query()
            .addCriteria(Criteria.where("payload.after.id").`is`(relationCapture.payload.after?.candidateId))
            .with(Sort.by(Sort.Direction.DESC, "payload.after.updated_at"))
            .limit(1)

        val candidateCapture = mongoTemplate.findOne(candidateCaptureQuery, CandidateCapture::class.java)

        if (candidateCapture == null) {
            println("No candidate found for evaluation ${event.evaluationId}")
            return
        }


        val candidateQuery = Query()
            .addCriteria(Criteria.where("_id").`is`(candidateCapture.payload.after?.id))
            .with(Sort.by(Sort.Direction.DESC, "payload.after.updated_at"))
            .limit(1)


        val candidate = mongoTemplate.findOne(candidateQuery, Candidate::class.java)

        if (candidate == null) {
            mongoTemplate.save(
                Candidate(
                    candidateId = candidateCapture.payload.after?.id,
                    email = candidateCapture.payload.after?.email,
                    name = candidateCapture.payload.after?.name,
                    evaluations = listOf(
                        Evaluation(
                            id = evaluationCapture.payload.after?.id,
                            comment = evaluationCapture.payload.after?.comment
                        )
                    )
                )
            )
        } else {
            val added = candidate.addEvaluation(
                Evaluation(
                    id = evaluationCapture.payload.after?.id,
                    comment = evaluationCapture.payload.after?.comment
                )
            )
            mongoTemplate.save(added)
        }
    }
}