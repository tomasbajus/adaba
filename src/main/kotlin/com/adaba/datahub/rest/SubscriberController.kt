package com.adaba.datahub.rest

import com.adaba.datahub.context.MessagingContext
import com.adaba.datahub.model.Message
import com.adaba.datahub.model.Subscriber
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.inject.Inject

@RestController
@RequestMapping("/subscribers")
class SubscriberController(@Inject val context: MessagingContext) {

	@GetMapping
	fun findAll() : MutableSet<Subscriber> = context.subscribers

	@GetMapping(value = ["/{subscriberId}"])
	fun findOne(@PathVariable subscriberId : String) : Subscriber? = context.subscribers.find { it.id === UUID.fromString(subscriberId) }

	@PostMapping
	fun createSubscriber(newSubscriber: Subscriber) = context.subscribers.add(newSubscriber)

	@GetMapping(value = ["/pull/{subscriberId}"])
	fun pull(@PathVariable subscriberId : String) : List<Message> = context.pullNewMessages(UUID.fromString(subscriberId))
}