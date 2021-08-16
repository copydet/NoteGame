package com.example.core.utils

import androidx.paging.PagingData
import androidx.paging.map
import com.example.core.data.source.local.entity.GamesEntity
import com.example.core.data.source.local.entity.SavedGamesEntity
import com.example.core.data.source.remote.response.*
import com.example.core.domain.model.Games
import com.example.core.domain.model.SavedGames
import com.example.core.domain.model.ScreenShots

object DataMapper{

    fun mapListGamesResponseToEntities(input: List<ResultsItem>): List<GamesEntity> = input.map {
        GamesEntity(
            id = it.id ?: 0,
            name = it.name,
            release = it.released,
            update = null,
            image = it.backgroundImage,
            rating = null,
            playtime = null,
            description = null,
            website = null,
            added = it.added
        )
    }

    fun mapListGamesEntitiesToDomain(input: PagingData<GamesEntity>): PagingData<Games> =
        input.map {
            Games(
                id = it.id,
                name = it.name,
                release = it.release,
                update = it.update,
                image = it.image,
                rating = it.rating,
                playtime = it.playtime,
                description = it.description,
                website = it.website,
                added = it.added
            )
        }

    fun responseToDomainGames(input: DetailResponseGames) = Games(
            id = input.id ?: 0,
            name = input.name,
            release = input.released,
            update = input.updated,
            image = input.backgroundImage,
            rating = input.rating,
            playtime = input.playtime,
            description = input.description,
            website = input.website,
            genres = input.genres,
            stores = input.stores.map {
                                      it.store
            },
            publishers = input.publishers,
            developers = input.developers,
            tags = input.tags,
            platform = input.parentPlatforms.map {
                                                 it.platform
            },

            added = input.added
        )


    fun mapGamesEntitiesToDomain(input: GamesEntity): Games =
        Games(
            id = input.id,
            name = input.name,
            release = input.release,
            update = input.update,
            image = input.image,
            rating = input.rating,
            playtime = input.playtime,
            description = input.description,
            website = input.website,
            added = input.added
        )


    fun mapEntityToDomainGames(input: GamesEntity) = Games(
        id = input.id,
        name = input.name,
        release = input.release,
        update = input.update,
        image = input.image,
        rating = input.rating,
        playtime = input.playtime,
        description = input.description,
        website = input.website,
        added = input.added
    )

    fun mapSavedGamesEntitiesToDomain(input: SavedGamesEntity?): SavedGames? {
        return if (input != null){
            SavedGames(
                savedId = input.id
            )
        }else {
            null
        }
    }

    fun responseToDomainScreenShots(input: List<ScreenShotsResponse>): List<ScreenShots>{
        val list = ArrayList<ScreenShots>()
        input.map {
            val data = ScreenShots(
                ssId = it.id,
                image = it.image
            )
            list.add(data)
        }
        return list
    }
}


