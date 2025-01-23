package org.github.swszz.collection

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "candidates")
data class Candidate(
    @Id
    val candidateId: Long?,
    @Field("name")
    val name: String?,
    @Field("email")
    val email: String?,
    @Field("evaluations")
    val evaluations: List<Evaluation>,
) {
    fun addEvaluation(evaluation: Evaluation): Candidate {
        val existingEvaluationIndex = evaluations.indexOfFirst { it.id == evaluation.id }

        return if (existingEvaluationIndex != -1) {
            // 존재하는 경우, 해당 Evaluation을 업데이트합니다.
            val updatedEvaluations = evaluations.toMutableList()
            updatedEvaluations[existingEvaluationIndex] = evaluation
            this.copy(evaluations = updatedEvaluations)
        } else {
            // 존재하지 않는 경우, 새로운 Evaluation을 추가합니다.
            this.copy(evaluations = evaluations + evaluation)
        }
    }
}