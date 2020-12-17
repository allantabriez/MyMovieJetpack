package com.example.jetpacksubmission.data.source.remote.response

data class DetailResponse(
    var genres : ArrayList<String>,
    var runTimes: ArrayList<Int>?,
    var runTime: Int?,
    var tagLine: String?
)