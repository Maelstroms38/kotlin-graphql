package com.example.kotlingraphql.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "desserts")
data class Dessert(
    var name: String,
    var amount: Float
) {
    @Id
    var id: String = ""

    @Transient
    var reviews: List<Review> = ArrayList()
}