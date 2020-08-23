package com.pravega.backend.models.greeting

data class GreetingRequest(val name: String);
data class GreetingResponse(val message: String);
