package com.example.kotlingraphql.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.example.kotlingraphql.entity.Review
import com.example.kotlingraphql.repository.ReviewRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ReviewMutationResolver (private val reviewRepository: ReviewRepository): GraphQLMutationResolver {
    fun newReview(dessertId: String, rating: Int, text: String): Review {
        val id = UUID.randomUUID().toString()
        val review = Review(id, dessertId, rating, text)
        reviewRepository.save(review)
        return review
    }
}