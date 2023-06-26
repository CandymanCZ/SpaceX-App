package com.kotlin.spacexapp


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PastLaunch(
    @Json(name = "auto_update")
    val autoUpdate: Boolean? = false,
    @Json(name = "capsules")
    val capsules: List<String?>? = listOf(),
    @Json(name = "cores")
    val cores: List<Core?>? = listOf(),
    @Json(name = "crew")
    val crew: List<Any?>? = listOf(),
    @Json(name = "date_local")
    val dateLocal: String? = "",
    @Json(name = "date_precision")
    val datePrecision: String? = "",
    @Json(name = "date_unix")
    val dateUnix: Int? = 0,
    @Json(name = "date_utc")
    val dateUtc: String? = "",
    @Json(name = "details")
    val details: String? = "",
    @Json(name = "failures")
    val failures: List<Any?>? = listOf(),
    @Json(name = "fairings")
    val fairings: Any? = Any(),
    @Json(name = "flight_number")
    val flightNumber: Int? = 0,
    @Json(name = "id")
    val id: String? = "",
    @Json(name = "launchpad")
    val launchpad: String? = "",
    @Json(name = "links")
    val links: Links? = Links(),
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "net")
    val net: Boolean? = false,
    @Json(name = "payloads")
    val payloads: List<String?>? = listOf(),
    @Json(name = "rocket")
    val rocket: String? = "",
    @Json(name = "ships")
    val ships: List<Any?>? = listOf(),
    @Json(name = "static_fire_date_unix")
    val staticFireDateUnix: Int? = 0,
    @Json(name = "static_fire_date_utc")
    val staticFireDateUtc: String? = "",
    @Json(name = "success")
    val success: Boolean? = false,
    @Json(name = "tdb")
    val tdb: Boolean? = false,
    @Json(name = "upcoming")
    val upcoming: Boolean? = false,
    @Json(name = "window")
    val window: Int? = 0
) {
    @JsonClass(generateAdapter = true)
    data class Core(
        @Json(name = "core")
        val core: String? = "",
        @Json(name = "flight")
        val flight: Int? = 0,
        @Json(name = "gridfins")
        val gridfins: Boolean? = false,
        @Json(name = "landing_attempt")
        val landingAttempt: Boolean? = false,
        @Json(name = "landing_success")
        val landingSuccess: Boolean? = false,
        @Json(name = "landing_type")
        val landingType: String? = "",
        @Json(name = "landpad")
        val landpad: String? = "",
        @Json(name = "legs")
        val legs: Boolean? = false,
        @Json(name = "reused")
        val reused: Boolean? = false
    )

    @JsonClass(generateAdapter = true)
    data class Links(
        @Json(name = "article")
        val article: String? = "",
        @Json(name = "flickr")
        val flickr: Flickr? = Flickr(),
        @Json(name = "patch")
        val patch: Patch? = Patch(),
        @Json(name = "presskit")
        val presskit: String? = "",
        @Json(name = "reddit")
        val reddit: Reddit? = Reddit(),
        @Json(name = "webcast")
        val webcast: String? = "",
        @Json(name = "wikipedia")
        val wikipedia: String? = "",
        @Json(name = "youtube_id")
        val youtubeId: String? = ""
    ) {
        @JsonClass(generateAdapter = true)
        data class Flickr(
            @Json(name = "original")
            val original: List<String?>? = listOf(),
            @Json(name = "small")
            val small: List<Any?>? = listOf()
        )

        @JsonClass(generateAdapter = true)
        data class Patch(
            @Json(name = "large")
            val large: String? = "",
            @Json(name = "small")
            val small: String? = ""
        )

        @JsonClass(generateAdapter = true)
        data class Reddit(
            @Json(name = "campaign")
            val campaign: String? = "",
            @Json(name = "launch")
            val launch: String? = "",
            @Json(name = "media")
            val media: String? = "",
            @Json(name = "recovery")
            val recovery: Any? = Any()
        )
    }
}