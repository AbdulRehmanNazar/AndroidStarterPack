package com.android.starterpack.data.mapper

import com.android.starterpack.data.local.entities.ContributorEntity
import com.android.starterpack.data.remote.dto.ContributorDto
import com.android.starterpack.domain.model.Contributor


/**
 * Model mapper between domain, dto and entity objects using singleton pattern
 */
object ContributorMapper {

    // Remote DTO → Domain
    fun dtoToDomain(dto: ContributorDto): Contributor {
        return Contributor(
            login = dto.login, avatarUrl = dto.avatarUrl, contributions = dto.contributions
        )
    }

    // Remote DTO → Entity
    fun dtoToEntity(dto: ContributorDto): ContributorEntity {
        return ContributorEntity(
            login = dto.login, avatarUrl = dto.avatarUrl, contributions = dto.contributions
        )
    }

    // Remote Domain → DTO
    fun domainToDto(dto: Contributor): ContributorDto {
        return ContributorDto(
            login = dto.login, avatarUrl = dto.avatarUrl, contributions = dto.contributions
        )
    }

    // Domain → Local Entity
    fun domainToEntity(domain: Contributor): ContributorEntity {
        return ContributorEntity(
            login = domain.login,
            avatarUrl = domain.avatarUrl,
            contributions = domain.contributions
        )
    }

    // Local Entity → Domain
    fun entityToDomain(entity: ContributorEntity): Contributor {
        return Contributor(
            login = entity.login,
            avatarUrl = entity.avatarUrl,
            contributions = entity.contributions
        )
    }
}