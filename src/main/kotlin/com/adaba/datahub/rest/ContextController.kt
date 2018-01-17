package com.adaba.datahub.rest

import com.adaba.datahub.context.MessagingContext
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.inject.Inject

@RestController
@RequestMapping("/context")
class ContextController (@Inject val context: MessagingContext){

	@RequestMapping(method = [RequestMethod.GET], value = ["/{subscriberId}", "/{topicId}" ])
	fun subscribe(@PathVariable subscriberId : String, @PathVariable topicId : String) = context.subscribeToTopic(UUID.fromString(topicId), UUID.fromString(subscriberId))

	@RequestMapping(method = [RequestMethod.DELETE], value = ["/{subscriberId}", "/{topicId}" ])
	fun unSubscribe(@PathVariable subscriberId : String, @PathVariable topicId : String) =  context.unSubscribeFromTopic(UUID.fromString(topicId), UUID.fromString(subscriberId))

}