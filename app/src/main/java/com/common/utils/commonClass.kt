package com.common.utils

import com.common.data.network.model.CuisineModel

fun getCustomObjects(): ArrayList<String> {
    val customObjects = ArrayList<String>()
    customObjects.apply {
        add("Select role")
        add("User")
        add("Chef")
    }
    return customObjects
}

fun getGenders(): ArrayList<String> {
    val customObjects = ArrayList<String>()
    customObjects.apply {
        add("Select gender")
        add("Male")
        add("Female")
    }
    return customObjects
}

val famousCuisines = arrayListOf(
    CuisineModel("Italian", false),
    CuisineModel("Chinese", false),
    CuisineModel("Japanese", false),
    CuisineModel("Indian", false),
    CuisineModel("Mexican", false),
    CuisineModel("French", false),
    CuisineModel("Thai", false),
    CuisineModel("Greek", false),
    CuisineModel("Spanish", false),
    CuisineModel("Mediterranean", false),
    CuisineModel("Korean", false),
    CuisineModel("Vietnamese", false),
    CuisineModel("Middle Eastern", false),
    CuisineModel("American", false),
    CuisineModel("Brazilian", false)
)