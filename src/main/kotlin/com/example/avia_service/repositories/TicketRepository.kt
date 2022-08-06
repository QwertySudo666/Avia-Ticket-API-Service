package com.example.avia_service.repositories

import com.example.avia_service.models.Ticket
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository : CrudRepository<Ticket, Int> {
    fun findAllByUserId(userId: Int): List<Ticket>
    fun findByIdAndUserId(id: Int, userId: Int): Ticket
}