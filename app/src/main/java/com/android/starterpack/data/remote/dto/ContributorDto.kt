package com.android.starterpack.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object for Remote APIs
 */
data class ContributorDto(
    val login: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    val contributions: Int? = null
)