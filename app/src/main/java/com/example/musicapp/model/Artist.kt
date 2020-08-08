package com.example.musicapp.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Artist(
    val artist: Data = Data()
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "artist_id")
        val artistId: Int = 0,
        @Json(name = "artist_name")
        val artistName: String? = null,
        @Json(name = "artist_name_translation_list")
        val artistNameTranslationList: List<ArtistNameTranslation>? = null,
        @Json(name = "artist_comment")
        val artistComment: String? = null,
        @Json(name = "artist_country")
        val artistCountry: String? = null,
        @Json(name = "artist_alias_list")
        val artistAliasList: List<ArtistAlias>? = null,
        @Json(name = "artist_rating")
        val artistRating: Int? = null,
        @Json(name = "artist_twitter_url")
        val artistTwitterUrl: String? = null,
        @Json(name = "artist_credits")
        val artistCredits: ArtistCredits? = null,
        val restricted: Int? = null,
        @Json(name = "updated_time")
        val updatedTime: String? = null
    ) : Parcelable
}