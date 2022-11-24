package com.example.appdevelopment.mockData.dailyWord

import java.util.*

val dailyWords = listOf(
    DailyWord("Dog"),
    DailyWord("Cat"),
    DailyWord("Kitchen"),
    DailyWord("Coffee"),
)

fun pickDailyWord(): String {

    Collections.shuffle(dailyWords)
    var randomDailyWord= dailyWords[0].word

    return randomDailyWord
}