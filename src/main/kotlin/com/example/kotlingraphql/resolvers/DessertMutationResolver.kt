package com.example.kotlingraphql.resolvers

import com.example.kotlingraphql.entity.Dessert
import com.example.kotlingraphql.repository.DessertRepository
import com.coxautodev.graphql.tools.GraphQLMutationResolver
import org.springframework.stereotype.Component
import java.util.*

@Component
class DessertMutationResolver (private val dessertRepository: DessertRepository): GraphQLMutationResolver {
    fun newDessert(name: String, amount: Float): Dessert {
        val dessert = Dessert(name, amount)
        dessert.id = UUID.randomUUID().toString()
        dessertRepository.save(dessert)
        return dessert
    }

    fun deleteDessert(id: String): Boolean {
        dessertRepository.deleteById(id)
        return true
    }

    fun updateDessert(id: String, amount: Float): Dessert {
        val dessert = dessertRepository.findById(id)
        dessert.ifPresent {
            it.amount = amount
            dessertRepository.save(it)
        }
        return dessert.get()
    }
}