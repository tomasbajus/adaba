package com.adaba.datahub.context

import com.adaba.datahub.model.Message
import com.adaba.datahub.model.Subscriber
import com.adaba.datahub.model.Topic
import org.springframework.stereotype.Component
import java.util.*

@Component
class MessagingContext() {

	val id: UUID = UUID.randomUUID()
	val topics: MutableSet<Topic> = mutableSetOf()
	val subscribers: MutableSet<Subscriber> = mutableSetOf()


	fun registerTopic(topic: Topic) {
		this.topics.add(topic)
	}

	fun unRegisterTopic(topic: Topic) {
		this.topics.remove(topic)
	}

	fun registerSubscriber(subscriber: Subscriber) {
		this.subscribers.add(subscriber)
	}

	fun unRegisterSubscriber(subscriber: Subscriber) {
		this.subscribers.remove(subscriber)
	}


	fun subscribeToTopic(topicId: UUID, subscriberId: UUID) {
		val subjectFound = topics.find { it.id == topicId }
		val observerFound = subscribers.find { it.id == subscriberId }

		if (subjectFound != null && observerFound != null) {
			subjectFound.register(observerFound)
			println("observer ${observerFound.observerName} was successfully registered to subject ${subjectFound.name}")
		}
	}

	fun unSubscribeFromTopic(topicId: UUID, subscriberId: UUID) {
		val subjectFound = topics.find { it.id == topicId }
		val observerFound = subscribers.find { it.id == subscriberId }

		if (subjectFound != null && observerFound != null) {
			subjectFound.unregister(observerFound)
			println("observer ${observerFound.observerName} was successfully unregistered from subject ${subjectFound.name}")
		}
	}

	fun postNewMessageToSubject(topicID: UUID, message: Message) {
		val subjectFound = topics.find { it.id === topicID }

		subjectFound?.let {
			println("Message ${message} was successfully posted to Topic ${subjectFound.name}")
			subjectFound.postMessage(message)
		}
	}

	fun pullNewMessages(subscriberId: UUID): List<Message> {
		val messages = mutableListOf<Message>()
		subscribers.find { it.id == subscriberId }?.let { subscriber ->
			{
				for (topic in subscriber.topics) {
					for (message in topic.messageThread) {
						message.metaData?.let { metaData ->
							{
								if (metaData.createdAt.before(subscriber.lastUpdate)) {
									messages.add(message)
								}
							}
						}
					}
					topic.subscribers.set(subscriber, true)
				}
			}
		}
		return messages
	}
}