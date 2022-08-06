package com.example.avia_service.services

import com.example.avia_service.models.Ticket
import com.example.avia_service.repositories.TicketRepository
import org.springframework.stereotype.Service

@Service
class TicketService(private val ticketRepository: TicketRepository) {
    fun save(ticket: Ticket): Ticket {
        return ticketRepository.save(ticket)
    }

    fun findAllByUserId(userId: Int): List<Ticket> {
        return ticketRepository.findAllByUserId(userId)
    }

    fun findByIdAndUserId(id: Int, userId: Int): Ticket {
        return ticketRepository.findByIdAndUserId(id, userId)
    }

    fun deleteById(id: Int){
        val ticket = ticketRepository.findById(id).get()
        ticketRepository.delete(ticket)
    }
}