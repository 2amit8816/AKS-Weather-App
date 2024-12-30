package com.example.aksweatherapp.common

import java.text.SimpleDateFormat
import java.util.TimeZone

object Utility {

    /**
     * Get formatted date from epoch time
     * @param dateInEpoch: String
     * @param timeZone: String
     */
    fun getFormattedDate(dateInEpoch: String, timeZone: String): String {
        val tz = TimeZone.getTimeZone(timeZone)
        val destFormat = SimpleDateFormat("HH:mm aa")
        destFormat.timeZone = tz
        return destFormat.format(dateInEpoch.toLong() * 1000)
    }
}