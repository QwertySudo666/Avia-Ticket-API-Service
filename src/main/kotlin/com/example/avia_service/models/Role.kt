package com.example.avia_service.models

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class Role {
    USER;

    public fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authority: MutableList<SimpleGrantedAuthority> = ArrayList()
        authority.add(SimpleGrantedAuthority(Role.USER.name))
        return authority
    }
}