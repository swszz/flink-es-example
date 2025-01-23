package org.github.swszz.collection

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "candidate_captures")
data class CandidateCapture(
    @Field("schema")
    val schema: Schema,
    @Field("payload")
    val payload: CandidatePayload,
)


data class CandidatePayload(
    @Field("before")
    val before: CandidateValue? = null,
    @Field("after")
    val after: CandidateValue? = null,
    @Field("source")
    val source: Source,
    @Field("op")
    val op: String,
    @Field("ts_ms")
    val tsMs: Long? = null,
    @Field("ts_us")
    val tsUs: Long? = null,
    @Field("ts_ns")
    val tsNs: Long? = null,
    @Field("transaction")
    val transaction: Transaction? = null,
)

data class CandidateValue(
    @Field("id")
    val id: Long,
    @Field("email")
    val email: String,
    @Field("name")
    val name: String,
    @Field("created_at")
    val createdAt: Long? = null,
    @Field("updated_at")
    val updatedAt: Long? = null,
)