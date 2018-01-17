package com.adaba.datahub.rest

import com.adaba.datahub.context.MessagingContext
import com.adaba.datahub.model.Subscriber
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.inject.Inject

@RestController
@RequestMapping("/subscribers")
class SubscriberController(@Inject val context: MessagingContext) {

	@RequestMapping(method = [RequestMethod.GET])
	fun findAll() : MutableSet<Subscriber> = context.subscribers

	@RequestMapping(method = [RequestMethod.GET], value = ["/{subscriberId}"])
	fun findOne(@PathVariable subscriberId : String) : Subscriber? = context.subscribers.find { it.id === UUID.fromString(subscriberId) }

	@RequestMapping(method = [RequestMethod.POST])
	fun createSubscriber(newSubscriber: Subscriber) = context.subscribers.add(newSubscriber)

	@RequestMapping(method = [RequestMethod.GET], value = ["/pull/{subscriberId}"])
	fun pull(@PathVariable subscriberId : String) : List<String> {
		return listOf();
	}

}