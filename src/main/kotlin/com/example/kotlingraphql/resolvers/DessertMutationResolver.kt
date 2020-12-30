package com.example.kotlingraphql.resolvers

import com.example.kotlingraphql.entity.Dessert
import com.example.kotlingraphql.repository.DessertRepository
import com.coxautodev.graphql.tools.GraphQLMutationResolver
import org.springframework.stereotype.Component
import java.util.*

@Component
class DessertMutationResolver (private val dessertRepository: DessertRepository): GraphQLMutationResolver {
    fun newDessert(name: String, description: String, imageUrl: String): Dessert {
        val dessert = Dessert(name, description, imageUrl)
        dessert.id = UUID.randomUUID().toString()
        dessertRepository.save(dessert)
        return dessert
    }

    fun deleteDessert(id: String): Boolean {
        dessertRepository.deleteById(id)
        return true
    }

    fun updateDessert(id: String, name: String?, description: String?, imageUrl: String?): Dessert {
        val dessert = dessertRepository.findById(id)
        dessert.ifPresent {
            it.name = name ?: it.name
            it.description = description ?: it.description
            it.imageUrl = imageUrl ?: it.imageUrl
            dessertRepository.save(it)
        }
        return dessert.get()
    }
}