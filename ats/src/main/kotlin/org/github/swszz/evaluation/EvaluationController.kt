package org.github.swszz.evaluation

import org.springframework.context.ApplicationEventPublisher
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EvaluationController(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val evaluationRepository: EvaluationRepository,
) {

    data class EvaluationCreationRequest(
        val candidateId: Long,
        val comment: String,
    )

    @PostMapping("/evaluations")
    @Transactional
    fun createEvaluation(
        @RequestBody request: EvaluationCreationRequest,
    ) {
        evaluationRepository.save(Evaluation.generate(comment = request.comment))
            .let { EvaluationCreationEvent(candidateId = request.candidateId, evaluationId = it.id) }
            .let { applicationEventPublisher.publishEvent(it) }
    }
}