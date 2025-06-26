package com.android.starterpack.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.starterpack.data.local.entities.ContributorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContributorDao {

    // Insert or update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContributor(contributor: ContributorEntity)

    // Insert list
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContributors(contributors: List<ContributorEntity>)

    // Get all contributors as Flow (observable)
    @Query("SELECT * FROM ContributorEntity ORDER BY contributions DESC")
    fun observeAllContributors(): Flow<List<ContributorEntity>>

    // Get all contributors as suspend function
    @Query("SELECT * FROM ContributorEntity ORDER BY contributions DESC")
    suspend fun getAllContributors(): List<ContributorEntity>

    // Delete specific
    @Delete
    suspend fun deleteContributor(contributor: ContributorEntity)

    // Clear table
    @Query("DELETE FROM ContributorEntity")
    suspend fun clearAll()
}