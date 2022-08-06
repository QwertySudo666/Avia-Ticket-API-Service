package com.example.avia_service.repositories

import com.example.avia_service.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {
    fun findByEmail(email: String): User?
}