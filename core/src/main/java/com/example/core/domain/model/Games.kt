package com.example.core.domain.model

import android.os.Parcelable
import com.example.core.data.source.remote.response.*
import com.example.core.utils.parseDateFormat
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Games (
    val id: Int? = 0,
    val name: String? = "",
    val image: String? = "",
    val description: String? = "",
    val release: String? = "",
    val website: String? = "",
    val rating: Double? = null,
    val playtime: Int? = null,
    val update: String? = "",
    val added: Int? = null,

    //RecyclerView
    val genres: @RawValue List<GenresItem>? = emptyList(),
    val platform: @RawValue List<Platform>? = emptyList(),
    val publishers: @RawValue List<PublishersItem>? = emptyList(),
    val developers: @RawValue List<DevelopersItem>? = emptyList(),
    val tags: @RawValue List<TagsItem>? = emptyList(),
    val stores: @RawValue List<Store>? = emptyList()

): Parcelable {
    fun getUpdateDateFormat(): String = update?.parseDateFormat("yyyy-MM-dd") ?: ""
}