package com.adaba.datahub.config

import com.adaba.datahub.context.MessagingContext
import com.adaba.datahub.model.Subscriber
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
		val top = TopicImpl(UUID.randomUUID(), "ShitTopic", "Brief shitty discussion", null, Date())
		val topId = top.id

		val subscriber1: Subscriber = SubscriberImpl(UUID.randomUUID(),"Subscriber1", mutableSetOf(), Date())
		val subscriber2: Subscriber = SubscriberImpl(UUID.randomUUID(),"Subscriber2", mutableSetOf(), Date())
		val subscriber3: Subscriber = SubscriberImpl(UUID.randomUUID(),"Subscriber3", mutableSetOf(), Date())
		val subscriber4: Subscriber = SubscriberImpl(UUID.randomUUID(),"Subscriber4", mutableSetOf(), Date())

		messagingContext.registerTopic(top)
		messagingContext.registerSubscriber(subscriber1)
		messagingContext.registerSubscriber(subscriber2)
		messagingContext.registerSubscriber(subscriber3)
		messagingContext.registerSubscriber(subscriber4)
		messagingContext.subscribeToTopic(topId, subscriber1.id)
		messagingContext.subscribeToTopic(topId, subscriber2.id)
		messagingContext.subscribeToTopic(topId, subscriber3.id)
		messagingContext.subscribeToTopic(topId, subscriber4.id)

		return messagingContext
	}
}