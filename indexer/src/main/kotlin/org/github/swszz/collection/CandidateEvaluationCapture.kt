package org.github.swszz.collection

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "candidate_evaluation_captures")
data class CandidateEvaluationCapture(
    @Field("schema")
    val schema: Schema,
    @Field("payload")
    val payload: CandidateEvaluationPayload,
)

data class Schema(
    @Field("type")
    val type: String,
    @Field("fields")
    val fields: List<FieldSchema>,
    @Field("optional")
    val optional: Boolean,
    @Field("name")
    val name: String,
    @Field("version")
    val version: Int,
)

data class FieldSchema(
    @Field("type")
    val type: String,
    @Field("optional")
    val optional: Boolean,
    @Field("field")
    val field: String,
    @Field("name")
    val name: String? = null,
    @Field("version")
    val version: Int? = null,
    @Field("parameters")
    val parameters: Map<String, String>? = null,
    @Field("default")
    val default: String? = null,
)

data class CandidateEvaluationPayload(
    @Field("before")
    val before: CandidateEvaluationValue? = null,
    @Field("after")
    val after: CandidateEvaluationValue? = null,
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

data class CandidateEvaluationValue(
    @Field("candidate_id")
    val candidateId: Long,
    @Field("created_at")
    val createdAt: Long? = null,
    @Field("evaluation_id")
    val evaluationId: Long,
    @Field("id")
    val id: Long,
    @Field("updated_at")
    val updatedAt: Long? = null,
)

data class Source(
    @Field("version")
    val version: String,
    @Field("connector")
    val connector: String,
    @Field("name")
    val name: String,
    @Field("ts_ms")
    val tsMs: Long,
    @Field("snapshot")
    val snapshot: String? = null,
    @Field("db")
    val db: String,
    @Field("sequence")
    val sequence: String? = null,
    @Field("ts_us")
    val tsUs: Long? = null,
    @Field("ts_ns")
    val tsNs: Long? = null,
    @Field("table")
    val table: String? = null,
    @Field("server_id")
    val serverId: Long,
    @Field("gtid")
    val gtid: String? = null,
    @Field("file")
    val file: String,
    @Field("pos")
    val pos: Long,
    @Field("row")
    val row: Int,
    @Field("thread")
    val thread: Long? = null,
    @Field("query")
    val query: String? = null,
)

data class Transaction(
    @Field("id")
    val id: String,
    @Field("total_order")
    val totalOrder: Long,
    @Field("data_collection_order")
    val dataCollectionOrder: Long,
)
