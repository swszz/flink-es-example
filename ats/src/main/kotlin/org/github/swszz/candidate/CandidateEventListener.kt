package org.github.swszz.candidate

import org.github.swszz.evaluation.EvaluationCreationEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CandidateEventListener(
    private val candidateEvaluationRepository: CandidateEvaluationRepository,
) {
    @TransactionalEventListener(EvaluationCreationEvent::class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun handleEvaluationCreationEvent(event: EvaluationCreationEvent) {
        event.let { CandidateEvaluation(candidateId = it.candidateId, evaluationId = it.evaluationId) }
            .let { candidateEvaluationRepository.save(it) }
    }
}