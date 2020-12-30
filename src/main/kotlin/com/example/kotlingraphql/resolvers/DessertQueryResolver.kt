package com.example.kotlingraphql.resolvers

import com.example.kotlingraphql.entity.Desserts
import com.example.kotlingraphql.entity.PagingInfo
import com.example.kotlingraphql.entity.Review
import com.example.kotlingraphql.repository.DessertRepository
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.kotlingraphql.entity.Dessert
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.findOne
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class DessertQueryResolver(val dessertRepository: DessertRepository,
                           private val mongoOperations: MongoOperations) : GraphQLQueryResolver {
    fun desserts(page: Int, size: Int): Desserts {
        val paging: Pageable = PageRequest.of(page, size)
        val list = dessertRepository.findAll(paging)
        val info = PagingInfo(
                list.totalElements.toInt(),
                list.totalPages,
                list.pageable.next().pageNumber,
                list.pageable.previousOrFirst().pageNumber,
        )
        return Desserts(list.content, info)
    }

    fun dessert(dessertId: String): Dessert? {
        val query = Query()
        query.addCriteria(Criteria.where("id").`is`(dessertId))
        val dessert = mongoOperations.findOne(query, Dessert::class.java)
        dessert?.let {
            dessert.reviews = getReviews(dessertId = dessertId)
        }
        return dessert
    }

    private fun getReviews(dessertId: String): List<Review> {
        val query = Query()
        query.addCriteria(Criteria.where("dessertId").`is`(dessertId))
        return mongoOperations.find(query, Review::class.java)
    }
}