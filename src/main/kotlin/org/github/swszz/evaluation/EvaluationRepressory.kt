package org.github.swszz.evaluation

import org.springframework.data.jpa.repository.JpaRepository

interface EvaluationRepressory : JpaRepository<Evaluation, Long>