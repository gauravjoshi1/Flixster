package com.example.flixster

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.json.JSONArray

@Parcelize
data class Movie(
    private val posterPath: String,
    val title: String,
    val movieId: Int,
    val movieRating: Double,
    val overview: String
) : Parcelable {
    @IgnoredOnParcel
    val posterImageUrl = "https://image.tmdb.org/t/p/w342/$posterPath"
    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJsonArray.length()) {
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                    movieJson.getString("poster_path"),
                    movieJson.getString("title"),
                    movieJson.getInt("id"),
                    movieJson.getDouble("vote_average"),
                    movieJson.getString("overview")
                    )
                )
            }
            return movies
        }
    }
}