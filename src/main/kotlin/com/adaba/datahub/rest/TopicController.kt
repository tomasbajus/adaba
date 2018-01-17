package com.adaba.datahub.rest

import com.adaba.datahub.context.MessagingContext
import com.adaba.datahub.model.MetaData
import com.adaba.datahub.model.Topic
import com.adaba.datahub.model.impl.MessageImpl
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.inject.Inject

@RestController
@RequestMapping("/topics")
@Component
class TopicController(@Inject var context: MessagingContext) {

	@GetMapping()
	fun findAll() : MutableSet<Topic> = context.topics;

	@GetMapping("/{topicId}")
	fun findOne(@PathVariable(name = "topicId") topicId: String): Topic?  =  context.topics.find { it.id == UUID.fromString(topicId) }

	@PostMapping()
	fun createTopic(newTopic: Topic) = context.registerTopic(newTopic)

	@PostMapping("/{topicId}")
	fun postToTopic(@PathVariable(name = "topicId") topicId: String, message : MessageImpl) {
		val metaData = MetaData("DUMMY", "NONE", Date(), Date())
		message.metaData = metaData
		context.postNewMessageToSubject(UUID.fromString(topicId), message)
	}
}