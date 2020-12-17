package com.example.jetpacksubmission.data.source.remote.response

data class FilmResponse(
    var id: Int,
    var title: String?,
    var releaseDate: String?,
    var overView: String?,
    var imagePath: String?,
    var voteAverage: Double,
    var voteCount: Int,
    var popularity: Int
)