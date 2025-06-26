package com.android.starterpack.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Entity class for Contributer
 */
@Entity
data class ContributorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val login: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    val contributions: Int? = null
)