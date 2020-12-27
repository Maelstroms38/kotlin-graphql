package com.example.kotlingraphql.entity

data class PagingInfo(
    var count: Int,
    var pages: Int,
    var next: Int,
    var prev: Int
)