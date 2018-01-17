package com.adaba.datahub.model.impl

import com.adaba.datahub.model.Message
import com.adaba.datahub.model.Subscriber
import com.adaba.datahub.model.Topic
import java.util.*

class SubscriberImpl(override val id: UUID, override val observerName: String, override val topics: MutableSet<Topic> = mutableSetOf(), override var lastUpdate: Date) : Subscriber {

	override fun update() : List<Message>{
		val messages = mutableListOf<Message>()

		topics.forEach { topic ->
			val topicMessages = topic.getUpdate(this)
			println("$observerName :: Consuming message:: $topicMessages")
			messages.addAll(topicMessages)
		}

		return messages
	}
}