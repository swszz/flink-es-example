package org.github.swszz.evaluation

import org.springframework.data.jpa.repository.JpaRepository

interface EvaluationRepository : JpaRepository<Evaluation, Long>