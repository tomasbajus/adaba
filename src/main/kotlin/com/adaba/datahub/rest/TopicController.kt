package com.adaba.datahub.rest

import com.adaba.datahub.context.MessagingContext
import com.adaba.datahub.model.Topic
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.inject.Inject

@RestController
@RequestMapping("/topics")
class TopicController(@Inject var context: MessagingContext) {

	@GetMapping()
	fun findAll() : MutableSet<Topic> = context.topics;

	@GetMapping("/{topicId}")
	fun findOne(@PathVariable(name = "topicId") topicId: String): Topic?  =  context.topics.find { it.id == UUID.fromString(topicId) }

	@PostMapping()
	fun createTopic(newTopic: Topic) = context.registerTopic(newTopic)
}