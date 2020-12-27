package com.example.kotlingraphql.entity

data class Desserts(
        val results: List<Dessert>,
        val info: PagingInfo
)