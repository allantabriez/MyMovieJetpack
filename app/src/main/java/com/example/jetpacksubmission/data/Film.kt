package com.example.jetpacksubmission.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "releaseDate") var releaseDate: String?,
    @ColumnInfo(name = "overView") var overView: String?,
    @ColumnInfo(name = "imagePath") var imagePath: String?,
    @ColumnInfo(name = "voteAverage") var voteAverage: Double,
    @ColumnInfo(name = "voteCount") var voteCount: Int,
    @ColumnInfo(name = "popularity") var popularity: Int
): Parcelable