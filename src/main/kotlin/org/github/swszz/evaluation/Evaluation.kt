package org.github.swszz.evaluation

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime

@Entity(name = "evaluation")
class Evaluation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val score: Int,

    @Column(columnDefinition = "TEXT")
    val comment: String,

    @CreationTimestamp
    @Column
    val createdAt: ZonedDateTime? = null,

    @UpdateTimestamp
    @Column
    val updatedAt: ZonedDateTime? = null,
) {
    companion object {
        fun generate(score: Int, comment: String): Evaluation {
            return Evaluation(
                score = score,
                comment = comment
            )
        }
    }
}