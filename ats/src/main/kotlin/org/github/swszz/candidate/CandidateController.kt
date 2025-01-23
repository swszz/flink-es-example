package org.github.swszz.candidate

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CandidateController(
    private val candidateRepository: CandidateRepository,
) {

    data class CandidateCreationRequest(
        val name: String,
        val email: String,
    )

    @PostMapping("/candidates")
    fun createCandidate(
        @RequestBody request: CandidateCreationRequest,
    ): Candidate {
        return Candidate.generate(email = request.email, name = request.name)
            .let { candidateRepository.save(it) }
    }
}