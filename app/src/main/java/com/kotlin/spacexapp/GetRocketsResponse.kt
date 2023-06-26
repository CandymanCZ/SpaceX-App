package com.kotlin.spacexapp


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Rocket(
    @Json(name = "active")
    val active: Boolean = false,
    @Json(name = "boosters")
    val boosters: Int? = 0,
    @Json(name = "company")
    val company: String? = "",
    @Json(name = "cost_per_launch")
    val costPerLaunch: Int = 0,
    @Json(name = "country")
    val country: String? = "",
    @Json(name = "description")
    val description: String? = "",
    @Json(name = "diameter")
    val diameter: Diameter = Diameter(),
    @Json(name = "engines")
    val engines: Engines = Engines(),
    @Json(name = "first_flight")
    val firstFlight: String? = "",
    @Json(name = "first_stage")
    val firstStage: FirstStage = FirstStage(),
    @Json(name = "flickr_images")
    val flickrImages: List<String?> = listOf(),
    @Json(name = "height")
    val height: Height = Height(),
    @Json(name = "id")
    val id: String? = "",
    @Json(name = "landing_legs")
    val landingLegs: LandingLegs = LandingLegs(),
    @Json(name = "mass")
    val mass: Mass = Mass(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "payload_weights")
    val payloadWeights: List<PayloadWeight> = listOf(),
    @Json(name = "second_stage")
    val secondStage: SecondStage = SecondStage(),
    @Json(name = "stages")
    val stages: Int = 0,
    @Json(name = "success_rate_pct")
    val successRatePct: Double? = 0.0,
    @Json(name = "type")
    val type: String? = "",
    @Json(name = "wikipedia")
    val wikipedia: String? = ""
) {
    @JsonClass(generateAdapter = true)
    data class Diameter(
        @Json(name = "feet")
        val feet: Double? = 0.0,
        @Json(name = "meters")
        val meters: Double? = 0.0
    )

    @JsonClass(generateAdapter = true)
    data class Engines(
        @Json(name = "engine_loss_max")
        val engineLossMax: Double? = 0.0,
        @Json(name = "isp")
        val isp: Isp = Isp(),
        @Json(name = "layout")
        val layout: String? = "",
        @Json(name = "number")
        val number: Int = 0,
        @Json(name = "propellant_1")
        val propellant1: String? = "",
        @Json(name = "propellant_2")
        val propellant2: String? = "",
        @Json(name = "thrust_sea_level")
        val thrustSeaLevel: ThrustSeaLevel = ThrustSeaLevel(),
        @Json(name = "thrust_to_weight")
        val thrustToWeight: Double? = 0.0,
        @Json(name = "thrust_vacuum")
        val thrustVacuum: ThrustVacuum = ThrustVacuum(),
        @Json(name = "type")
        val type: String? = "",
        @Json(name = "version")
        val version: String? = ""
    ) {
        @JsonClass(generateAdapter = true)
        data class Isp(
            @Json(name = "sea_level")
            val seaLevel: Double? = 0.0,
            @Json(name = "vacuum")
            val vacuum: Double? = 0.0
        )

        @JsonClass(generateAdapter = true)
        data class ThrustSeaLevel(
            @Json(name = "kN")
            val kN: Double? = 0.0,
            @Json(name = "lbf")
            val lbf: Double? = 0.0
        )

        @JsonClass(generateAdapter = true)
        data class ThrustVacuum(
            @Json(name = "kN")
            val kN: Double? = 0.0,
            @Json(name = "lbf")
            val lbf: Double? = 0.0
        )
    }

    @JsonClass(generateAdapter = true)
    data class FirstStage(
        @Json(name = "burn_time_sec")
        val burnTimeSec: Double?? = 0.0,
        @Json(name = "engines")
        val engines: Double? = 0.0,
        @Json(name = "fuel_amount_tons")
        val fuelAmountTons: Double? = 0.0,
        @Json(name = "reusable")
        val reusable: Boolean = false,
        @Json(name = "thrust_sea_level")
        val thrustSeaLevel: ThrustSeaLevel = ThrustSeaLevel(),
        @Json(name = "thrust_vacuum")
        val thrustVacuum: ThrustVacuum = ThrustVacuum()
    ) {
        @JsonClass(generateAdapter = true)
        data class ThrustSeaLevel(
            @Json(name = "kN")
            val kN: Double? = 0.0,
            @Json(name = "lbf")
            val lbf: Double? = 0.0
        )

        @JsonClass(generateAdapter = true)
        data class ThrustVacuum(
            @Json(name = "kN")
            val kN: Double? = 0.0,
            @Json(name = "lbf")
            val lbf: Double? = 0.0
        )
    }

    @JsonClass(generateAdapter = true)
    data class Height(
        @Json(name = "feet")
        val feet: Double? = 0.0,
        @Json(name = "meters")
        val meters: Double? = 0.0
    )

    @JsonClass(generateAdapter = true)
    data class LandingLegs(
        @Json(name = "material")
        val material: String? = "",
        @Json(name = "number")
        val number: Int = 0
    )

    @JsonClass(generateAdapter = true)
    data class Mass(
        @Json(name = "kg")
        val kg: Double? = 0.0,
        @Json(name = "lb")
        val lb: Double? = 0.0
    )

    @JsonClass(generateAdapter = true)
    data class PayloadWeight(
        @Json(name = "id")
        val id: String? = "",
        @Json(name = "kg")
        val kg: Double? = 0.0,
        @Json(name = "lb")
        val lb: Double? = 0.0,
        @Json(name = "name")
        val name: String? = ""
    )

    @JsonClass(generateAdapter = true)
    data class SecondStage(
        @Json(name = "burn_time_sec")
        val burnTimeSec: Double? = 0.0,
        @Json(name = "engines")
        val engines: Double? = 0.0,
        @Json(name = "fuel_amount_tons")
        val fuelAmountTons: Double? = 0.0,
        @Json(name = "payloads")
        val payloads: Payloads = Payloads(),
        @Json(name = "reusable")
        val reusable: Boolean = false,
        @Json(name = "thrust")
        val thrust: Thrust = Thrust()
    ) {
        @JsonClass(generateAdapter = true)
        data class Payloads(
            @Json(name = "composite_fairing")
            val compositeFairing: CompositeFairing = CompositeFairing(),
            @Json(name = "option_1")
            val option1: String? = ""
        ) {
            @JsonClass(generateAdapter = true)
            data class CompositeFairing(
                @Json(name = "diameter")
                val diameter: Diameter = Diameter(),
                @Json(name = "height")
                val height: Height = Height()
            ) {
                @JsonClass(generateAdapter = true)
                data class Diameter(
                    @Json(name = "feet")
                    val feet: Double? = 0.0,
                    @Json(name = "meters")
                    val meters: Double? = 0.0
                )

                @JsonClass(generateAdapter = true)
                data class Height(
                    @Json(name = "feet")
                    val feet: Double? = 0.0,
                    @Json(name = "meters")
                    val meters: Double? = 0.0
                )
            }
        }

        @JsonClass(generateAdapter = true)
        data class Thrust(
            @Json(name = "kN")
            val kN: Double? = 0.0,
            @Json(name = "lbf")
            val lbf: Double? = 0.0
        )
    }
}