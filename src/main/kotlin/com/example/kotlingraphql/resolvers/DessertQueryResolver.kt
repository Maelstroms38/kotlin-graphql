package com.example.kotlingraphql.resolvers

import com.example.kotlingraphql.entity.Desserts
import com.example.kotlingraphql.entity.PagingInfo
import com.example.kotlingraphql.entity.Review
import com.example.kotlingraphql.repository.DessertRepository
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class DessertQueryResolver(val dessertRepository: DessertRepository,
                           private val mongoOperations: MongoOperations) : GraphQLQueryResolver {
    fun desserts(page: Int?, size: Int?): Desserts {
        val paging: Pageable = PageRequest.of(page ?: 0, size ?: 10)
        val list = dessertRepository.findAll(paging)
        for (item in list) {
            item.reviews = getReviews(dessertId = item.id)
        }
        val info = PagingInfo(
                list.totalElements.toInt(),
                list.totalPages,
                list.pageable.next().pageNumber,
                list.pageable.previousOrFirst().pageNumber,
        )
        return Desserts(list.content, info)
    }

    private fun getReviews(dessertId: String): List<Review> {
        val query = Query()
        query.addCriteria(Criteria.where("snackId").`is`(dessertId))
        return mongoOperations.find(query, Review::class.java)
    }
}