package org.github.swszz.candidate

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime

@Entity(name = "candidate_evaluation")
class CandidateEvaluation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val candidateId: Long,

    @Column(nullable = false)
    val evaluationId: Long,

    @CreationTimestamp
    @Column
    val createdAt: ZonedDateTime? = null,

    @UpdateTimestamp
    @Column
    val updatedAt: ZonedDateTime? = null,
) {
    companion object {
        fun generate(candidateId: Long, evaluationId: Long): CandidateEvaluation {
            return CandidateEvaluation(
                candidateId = candidateId,
                evaluationId = evaluationId,
            )
        }
    }
}