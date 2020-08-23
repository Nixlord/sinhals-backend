package com.pravega.backend.controllers.api.friends

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.HashMap

@RestController
class FriendController {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @GetMapping("/friends")
    fun getAllFriends(): HashMap<Int, Friend> {
        // Write better Kotlin code
        val results = hashMapOf<Int, Friend>()
        var id = 0
        jdbcTemplate.query("SELECT * FROM friends ORDER BY age desc") { result, _ ->
            results[id] = Friend(result.getString("name"), result.getInt("age"))
            id += 1
        }
        return results;
    }
}