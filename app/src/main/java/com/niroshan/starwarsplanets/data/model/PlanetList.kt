package com.niroshan.starwarsplanets.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class PlanetList(
    val count: Int?,
    val next: String?,
    val prev: String?,
    val results: List<PlanetData>
)

@Parcelize
data class PlanetData(
    val name: String?,
    val climate: String?,
    val orbital_period: String?,
    val gravity: String?,
    var imageURL: String?
) : Parcelable


