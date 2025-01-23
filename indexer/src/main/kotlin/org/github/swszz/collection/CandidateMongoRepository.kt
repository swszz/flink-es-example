package org.github.swszz.collection

import org.springframework.data.mongodb.repository.MongoRepository

interface CandidateMongoRepository : MongoRepository<Candidate, Long>