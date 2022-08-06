package com.example.avia_service.services

import com.example.avia_service.models.User
import com.example.avia_service.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun save(user: User): User {
        return this.userRepository.save(user)
    }

    fun findByEmail(email: String): User?{
        return userRepository.findByEmail(email)
    }

    fun findById(id: Int): User?{
        return userRepository.findById(id).get()
    }
}