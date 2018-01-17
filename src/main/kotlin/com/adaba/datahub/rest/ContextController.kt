package com.adaba.datahub.rest

import com.adaba.datahub.context.MessagingContext
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.inject.Inject

@RestController
@RequestMapping("/context")
class ContextController (@Inject val context: MessagingContext){

	@GetMapping(value = ["/{subscriberId}", "/{topicId}" ])
	fun subscribe(@PathVariable subscriberId : String, @PathVariable topicId : String) = context.subscribeToTopic(UUID.fromString(topicId), UUID.fromString(subscriberId))

	@DeleteMapping(value = ["/{subscriberId}", "/{topicId}" ])
	fun unSubscribe(@PathVariable subscriberId : String, @PathVariable topicId : String) =  context.unSubscribeFromTopic(UUID.fromString(topicId), UUID.fromString(subscriberId))

}