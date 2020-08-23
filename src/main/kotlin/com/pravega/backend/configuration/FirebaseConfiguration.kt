package com.pravega.backend.configuration

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.io.FileInputStream


@Configuration
class FirebaseConfiguration {
    @Primary
    @Bean
    fun firebaseAdmin() {
        val serviceAccount = FileInputStream("./firebase.json")
        val options = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://pravegaherokufirebase.firebaseio.com")
                .build()


        FirebaseApp.initializeApp(options)
        val ref = FirebaseDatabase.getInstance().reference
        ref.child("spring")
                .setValueAsync(mapOf(
                        Pair("name", "shibasis"),
                        Pair("skills", mapOf(
                                Pair("dev", "Kotlin/JS"),
                                Pair("ai", "python/math")
                        )
                        )
                ))
    }

}