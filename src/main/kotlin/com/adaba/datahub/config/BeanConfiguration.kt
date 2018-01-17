package com.adaba.datahub.config

import com.adaba.datahub.context.MessagingContext
import com.adaba.datahub.model.Subscriber
import com.adaba.datahub.model.impl.MessageImpl
import com.adaba.datahub.model.impl.SubscriberImpl
import com.adaba.datahub.model.impl.TopicImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Date
import java.util.UUID

@Configuration
class BeanConfiguration {

	@Bean
	fun messagingContext() : MessagingContext {
		val messagingContext = MessagingContext()
		val top = TopicImpl(UUID.fromString("54a686d9-72c3-475e-b58e-7e3a8dba03cf"), "ShitTopic", "Brief shitty discussion", null, Date())
		val topId = top.id

		val subscriber1: Subscriber = SubscriberImpl(UUID.fromString("54a686d9-72c3-475e-b58e-7e3a8dba03cf"),"Subscriber1", mutableSetOf(), Date())
		val subscriber2: Subscriber = SubscriberImpl(UUID.fromString("94655df1-e219-4e24-9752-a231fe23828d"),"Subscriber2", mutableSetOf(), Date())
		val subscriber3: Subscriber = SubscriberImpl(UUID.fromString("1c466964-95d3-45b7-8d50-1063bc4532e3"),"Subscriber3", mutableSetOf(), Date())
		val subscriber4: Subscriber = SubscriberImpl(UUID.fromString("eb0be4ac-d9f7-4e6e-a118-35b839bd5cb6"),"Subscriber4", mutableSetOf(), Date())

		messagingContext.registerTopic(top)
		messagingContext.registerSubscriber(subscriber1)
		messagingContext.registerSubscriber(subscriber2)
		messagingContext.registerSubscriber(subscriber3)
		messagingContext.registerSubscriber(subscriber4)
		messagingContext.subscribeToTopic(topId, subscriber1.id)
		messagingContext.subscribeToTopic(topId, subscriber2.id)
		messagingContext.subscribeToTopic(topId, subscriber3.id)
		messagingContext.subscribeToTopic(topId, subscriber4.id)

		top.messageThread.add( MessageImpl(UUID.randomUUID(), top.id, "brand new message", "I had a wonderful shit today", null))

		return messagingContext
	}
}