package com.example.kotlingraphql.resolvers

import com.example.kotlingraphql.entity.Review
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class ReviewQueryResolver(val mongoOperations: MongoOperations) : GraphQLQueryResolver {
    fun reviews(dessertId: String): List<Review> {
        val query = Query()
        query.addCriteria(Criteria.where("dessertId").`is`(dessertId))
        return mongoOperations.find(query, Review::class.java)
    }
}