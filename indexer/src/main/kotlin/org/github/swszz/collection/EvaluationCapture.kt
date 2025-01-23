package org.github.swszz.collection

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "evaluation_captures")
data class EvaluationCapture(
    @Field("schema")
    val schema: Schema,
    @Field("payload")
    val payload: EvaluationCapturePayload,
)


data class EvaluationCapturePayload(
    @Field("before")
    val before: EvaluationValue? = null,
    @Field("after")
    val after: EvaluationValue? = null,
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

data class EvaluationValue(
    @Field("id")
    val id: Long,
    @Field("comment")
    val comment: String,
    @Field("created_at")
    val createdAt: Long? = null,
    @Field("updated_at")
    val updatedAt: Long? = null,
)