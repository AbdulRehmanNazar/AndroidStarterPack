package com.android.starterpack.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Domain model for Contributer
 */
data class Contributor(
    val login: String = "",
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    val contributions: Int? = null
)