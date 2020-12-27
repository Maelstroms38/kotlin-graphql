package com.example.kotlingraphql.repository

import com.example.kotlingraphql.entity.Dessert
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DessertRepository : MongoRepository<Dessert, String>