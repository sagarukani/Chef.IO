package com.common.data.network.model

data class ScheduleResponse(
    val chefid: String,
    val createdAt: String,
    val fridaytime: String,
    val id: Int,
    val mondaytime: String,
    val saturdaytime: String,
    val sundayatime: String,
    val thursdaytime: String,
    val tuesdaytime: String,
    val updatedAt: String,
    val wednesdaytime: String
)