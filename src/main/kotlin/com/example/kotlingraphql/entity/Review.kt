package com.example.kotlingraphql.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "reviews")
data class Review(
    @Id
    var id: String,
    var dessertId: String,
    var rating: Int,
    var text: String
)