package com.example.data.dto

import com.example.data.getPosterUrl
import com.example.domain.enums.ImageSize
import com.example.domain.models.DetailedMovieModel
import com.example.domain.models.MovieGenreModel
import com.example.domain.models.MovieLanguageModel
import com.google.gson.annotations.SerializedName

data class MovieDetailsDto(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("belongs_to_collection") val belongsToCollection: MovieCollectionDto,
    @SerializedName("budget") val budget: Int,
    @SerializedName("genres") val genres: List<MovieGenreDto>,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("id") val id: Int,
    @SerializedName("imdb_id") val imdbId: String,
    @SerializedName("origin_country") val originCountry: List<String>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompanyDto>,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountryDto>,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("revenue") val revenue: Int,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("spoken_languages") val spokenLanguages: List<LanguageDto>,
    @SerializedName("status") val status: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)

data class MovieCollectionDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
)

data class MovieGenreDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)

data class ProductionCompanyDto(
    @SerializedName("id") val id: Int,
    @SerializedName("logo_path") val logoPath: String,
    @SerializedName("name") val name: String,
    @SerializedName("origin_country") val originCountry: String,
)

data class ProductionCountryDto(
    @SerializedName("iso_3116_1") val iso: String,
    @SerializedName("name") val name: String,
)

data class LanguageDto(
    @SerializedName("english_name") val englishName: String,
    @SerializedName("iso_639_1") val iso: String,
    @SerializedName("name") val name: String,
)

fun MovieDetailsDto.toDetailedMovieModel() =
    DetailedMovieModel(
        adult = adult,
        genres = genres.map { MovieGenreModel(it.id, it.name) },
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        poster = getPosterUrl(posterPath, imageSize = ImageSize.LARGE),
        countries = originCountry,
        releaseDate = releaseDate,
        runtime = runtime,
        languages = spokenLanguages.map { MovieLanguageModel(it.englishName, it.name) },
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
