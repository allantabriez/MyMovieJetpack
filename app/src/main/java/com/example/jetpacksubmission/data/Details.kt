package com.example.jetpacksubmission.data

data class Details(
    var genres : ArrayList<String>,
    var runTimes: ArrayList<Int>?,
    var runTime: Int?,
    val tagLine: String?
)