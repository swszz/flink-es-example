package org.github.swszz.candidate

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime

@Entity(name = "candidate")
class Candidate(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    val email: String,

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val name: String,

    @CreationTimestamp
    @Column
    val createdAt: ZonedDateTime? = null,

    @UpdateTimestamp
    @Column
    val updatedAt: ZonedDateTime? = null,
) {
    companion object {
        fun generate(email: String, name: String): Candidate {
            return Candidate(
                email = email,
                name = name
            )
        }
    }
}