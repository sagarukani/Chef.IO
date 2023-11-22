package com.common.data.network.model.request

data class ScheduleReqModel(
    val fridaytime: String,
    val mondaytime: String,
    val saturdaytime: String,
    val sundayatime: String,
    val thursdaytime: String,
    val tuesdaytime: String,
    val wednesdaytime: String
)