package com.kotlin.spacexapp


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rocket(
    @Json(name = "active")
    val active: Boolean,
    @Json(name = "boosters")
    val boosters: Double?,
    @Json(name = "company")
    val company: String?,
    @Json(name = "cost_per_launch")
    val costPerLaunch: Double?,
    @Json(name = "country")
    val country: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "diameter")
    val diameter: Diameter,
    @Json(name = "engines")
    val engines: Engines,
    @Json(name = "first_flight")
    val firstFlight: String?,
    @Json(name = "first_stage")
    val firstStage: FirstStage,
    @Json(name = "flickr_images")
    val flickrImages: List<String?>,
    @Json(name = "height")
    val height: Height,
    @Json(name = "id")
    val id: String?,
    @Json(name = "landing_legs")
    val landingLegs: LandingLegs,
    @Json(name = "mass")
    val mass: Mass,
    @Json(name = "name")
    val name: String?,
    @Json(name = "payload_weights")
    val payloadWeights: List<PayloadWeight>,
    @Json(name = "second_stage")
    val secondStage: SecondStage,
    @Json(name = "stages")
    val stages: Double?,
    @Json(name = "success_rate_pct")
    val successRatePct: Double?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "wikipedia")
    val wikipedia: String?
) {
    @JsonClass(generateAdapter = true)
    data class Diameter(
        @Json(name = "feet")
        val feet: Double?,
        @Json(name = "meters")
        val meters: Double?
    )

    @JsonClass(generateAdapter = true)
    data class Engines(
        @Json(name = "engine_loss_max")
        val engineLossMax: Double?,
        @Json(name = "isp")
        val isp: Isp,
        @Json(name = "layout")
        val layout: String?,
        @Json(name = "number")
        val number: Double?,
        @Json(name = "propellant_1")
        val propellant1: String?,
        @Json(name = "propellant_2")
        val propellant2: String?,
        @Json(name = "thrust_sea_level")
        val thrustSeaLevel: ThrustSeaLevel,
        @Json(name = "thrust_to_weight")
        val thrustToWeight: Double?,
        @Json(name = "thrust_vacuum")
        val thrustVacuum: ThrustVacuum,
        @Json(name = "type")
        val type: String?,
        @Json(name = "version")
        val version: String?
    ) {
        @JsonClass(generateAdapter = true)
        data class Isp(
            @Json(name = "sea_level")
            val seaLevel: Double?,
            @Json(name = "vacuum")
            val vacuum: Double?
        )

        @JsonClass(generateAdapter = true)
        data class ThrustSeaLevel(
            @Json(name = "kN")
            val kN: Double?,
            @Json(name = "lbf")
            val lbf: Double?
        )

        @JsonClass(generateAdapter = true)
        data class ThrustVacuum(
            @Json(name = "kN")
            val kN: Double?,
            @Json(name = "lbf")
            val lbf: Double?
        )
    }

    @JsonClass(generateAdapter = true)
    data class FirstStage(
        @Json(name = "burn_time_sec")
        val burnTimeSec: Double?,
        @Json(name = "engines")
        val engines: Double?,
        @Json(name = "fuel_amount_tons")
        val fuelAmountTons: Double?,
        @Json(name = "reusable")
        val reusable: Boolean,
        @Json(name = "thrust_sea_level")
        val thrustSeaLevel: ThrustSeaLevel,
        @Json(name = "thrust_vacuum")
        val thrustVacuum: ThrustVacuum
    ) {
        @JsonClass(generateAdapter = true)
        data class ThrustSeaLevel(
            @Json(name = "kN")
            val kN: Double?,
            @Json(name = "lbf")
            val lbf: Double?
        )

        @JsonClass(generateAdapter = true)
        data class ThrustVacuum(
            @Json(name = "kN")
            val kN: Double?,
            @Json(name = "lbf")
            val lbf: Double?
        )
    }

    @JsonClass(generateAdapter = true)
    data class Height(
        @Json(name = "feet")
        val feet: Double?,
        @Json(name = "meters")
        val meters: Double?
    )

    @JsonClass(generateAdapter = true)
    data class LandingLegs(
        @Json(name = "material")
        val material: String??,
        @Json(name = "number")
        val number: Double?
    )

    @JsonClass(generateAdapter = true)
    data class Mass(
        @Json(name = "kg")
        val kg: Double?,
        @Json(name = "lb")
        val lb: Double?
    )

    @JsonClass(generateAdapter = true)
    data class PayloadWeight(
        @Json(name = "id")
        val id: String?,
        @Json(name = "kg")
        val kg: Double?,
        @Json(name = "lb")
        val lb: Double?,
        @Json(name = "name")
        val name: String?
    )

    @JsonClass(generateAdapter = true)
    data class SecondStage(
        @Json(name = "burn_time_sec")
        val burnTimeSec: Double?,
        @Json(name = "engines")
        val engines: Double?,
        @Json(name = "fuel_amount_tons")
        val fuelAmountTons: Double?,
        @Json(name = "payloads")
        val payloads: Payloads,
        @Json(name = "reusable")
        val reusable: Boolean,
        @Json(name = "thrust")
        val thrust: Thrust
    ) {
        @JsonClass(generateAdapter = true)
        data class Payloads(
            @Json(name = "composite_fairing")
            val compositeFairing: CompositeFairing,
            @Json(name = "option_1")
            val option1: String?
        ) {
            @JsonClass(generateAdapter = true)
            data class CompositeFairing(
                @Json(name = "diameter")
                val diameter: Diameter,
                @Json(name = "height")
                val height: Height
            ) {
                @JsonClass(generateAdapter = true)
                data class Diameter(
                    @Json(name = "feet")
                    val feet: Double?,
                    @Json(name = "meters")
                    val meters: Double?
                )

                @JsonClass(generateAdapter = true)
                data class Height(
                    @Json(name = "feet")
                    val feet: Double?,
                    @Json(name = "meters")
                    val meters: Double?
                )
            }
        }

        @JsonClass(generateAdapter = true)
        data class Thrust(
            @Json(name = "kN")
            val kN: Double?,
            @Json(name = "lbf")
            val lbf: Double?
        )
    }
}

data class GetRocketsResponse(@Json(name = "")
    val rockets: List<Rocket>
)