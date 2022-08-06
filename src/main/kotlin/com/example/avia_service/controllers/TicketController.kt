package com.example.avia_service.controllers

import com.example.avia_service.models.Ticket
import com.example.avia_service.models.User
import com.example.avia_service.services.TicketService
import com.example.avia_service.services.UserService
import io.jsonwebtoken.Jwts
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/tickets")
class TicketController(private val userService: UserService, private val ticketService: TicketService) {
    //Add new ticket
    @PostMapping("save")
    fun addTicket(@RequestBody requestTicket: Ticket, @CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body("unauthenticated")
            }
            val userFromDB = getUserFromJWT(jwt)
            requestTicket.userId = userFromDB.id
            val ticket = ticketService.save(requestTicket)
            userFromDB.tickets.add(ticket)
            return ResponseEntity.ok("success")
        } catch (e: Exception) {
            return ResponseEntity.status(401).body("unauthenticated")
        }
    }

    @GetMapping("")
    fun getTickets(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body("unauthenticated")
            }
            val userFromDB = getUserFromJWT(jwt)
            val tickets = ticketService.findAllByUserId(userFromDB.id)
            return ResponseEntity.ok(tickets)
        } catch (e: Exception) {
            return ResponseEntity.status(401).body("unauthenticated")
        }
    }

    @GetMapping("{ticketId}")
    fun getTicket(@PathVariable ticketId: Int, @CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body("unauthenticated")
            }
            val userFromDB = getUserFromJWT(jwt)
            val ticket = ticketService.findByIdAndUserId(ticketId, userFromDB.id)
            return ResponseEntity.ok(ticket)
        } catch (e: Exception) {
            return ResponseEntity.status(401).body("unauthenticated")
        }
    }

    @DeleteMapping("{ticketId}")
    fun deleteTicket(@PathVariable ticketId: Int, @CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body("unauthenticated")
            }
            //remove ticket from DB
            ticketService.deleteById(ticketId)
            return ResponseEntity.ok("success")
        } catch (e: Exception) {
            return ResponseEntity.status(401).body("unauthenticated")
        }
    }

    fun getUserFromJWT(jwt: String?): User{
        val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body
        return userService.findById(body.issuer.toInt())!!
    }
}