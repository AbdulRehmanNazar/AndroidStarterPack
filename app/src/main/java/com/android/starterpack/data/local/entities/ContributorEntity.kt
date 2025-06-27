package com.android.starterpack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Entity class for Contributor
 */
@Entity
data class ContributorEntity(
    @PrimaryKey
    val login: String = "",
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    val contributions: Int? = null
)