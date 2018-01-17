package com.adaba.datahub.rest

import com.adaba.datahub.context.MessagingContext
import com.adaba.datahub.model.MetaData
import com.adaba.datahub.model.impl.MessageImpl
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.inject.Inject

@RestController
@RequestMapping("/messages")
class MessageController(@Inject var context: MessagingContext) {

	@PostMapping()
	fun postToTopic(message : MessageImpl) {
		val metaData = MetaData("DUMMY", "NONE", Date(), Date())
		message.metaData = metaData
		context.postNewMessageToSubject(message)
	}
}