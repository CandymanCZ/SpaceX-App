package com.kotlin.spacexapp.api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCompanyInfoResponse(
    @Json(name = "ceo")
    val ceo: String = "",
    @Json(name = "coo")
    val coo: String = "",
    @Json(name = "cto")
    val cto: String = "",
    @Json(name = "cto_propulsion")
    val ctoPropulsion: String = "",
    @Json(name = "employees")
    val employees: Int = 0,
    @Json(name = "founded")
    val founded: Int = 0,
    @Json(name = "founder")
    val founder: String = "",
    @Json(name = "headquarters")
    val headquarters: Headquarters = Headquarters(),
    @Json(name = "id")
    val id: String = "",
    @Json(name = "launch_sites")
    val launchSites: Int = 0,
    @Json(name = "links")
    val links: Links = Links(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "summary")
    val summary: String = "",
    @Json(name = "test_sites")
    val testSites: Int = 0,
    @Json(name = "valuation")
    val valuation: Long = 0,
    @Json(name = "vehicles")
    val vehicles: Int = 0
) {
    @JsonClass(generateAdapter = true)
    data class Headquarters(
        @Json(name = "address")
        val address: String = "",
        @Json(name = "city")
        val city: String = "",
        @Json(name = "state")
        val state: String = ""
    )

    @JsonClass(generateAdapter = true)
    data class Links(
        @Json(name = "elon_twitter")
        val elonTwitter: String = "",
        @Json(name = "flickr")
        val flickr: String = "",
        @Json(name = "twitter")
        val twitter: String = "",
        @Json(name = "website")
        val website: String = ""
    )
}