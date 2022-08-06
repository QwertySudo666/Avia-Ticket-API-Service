package com.example.avia_service.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.*

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var name: String = ""
    var email: String = ""
    var password: String = ""
        @JsonIgnore
        get
        set(value) {
            val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    @Enumerated(value = EnumType.STRING)
    var role: Role = Role.USER

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "userId")
    var tickets: MutableList<Ticket> = ArrayList()

    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }

    fun deleteTicketByTicketId(ticketId: Int) {
        for (i in 0..(tickets.size - 1)) {
            if (tickets[i].id == ticketId) {
                tickets.removeAt(i)
            }
        }
    }
}