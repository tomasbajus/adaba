package com.adaba.datahub.model.impl

import com.adaba.datahub.model.Location
import com.adaba.datahub.model.Message
import com.adaba.datahub.model.Subscriber
import com.adaba.datahub.model.Topic
import java.util.UUID
import java.util.Date

class TopicImpl(override var id: UUID, override var name: String, override var shortDescription: String?, override var currentLocation: Location?, override var lastUpdateAt: Date) : Topic {

	override val subscribers: MutableMap<Subscriber, Boolean> = mutableMapOf()
	override val messageThread: MutableList<Message> = mutableListOf()
	override val locationHistory: MutableList<Location> = mutableListOf()
	private val MUTEX = Any()

	override fun register(obj: Subscriber) {
		synchronized(MUTEX) {
			if (!subscribers.contains(obj)) {
				subscribers.put(obj, false)
				obj.topics.add(this)
			}
		}
	}

	override fun unregister(obj: Subscriber) {
		synchronized(MUTEX) {
			subscribers.remove(obj)
			obj.topics.remove(this)
		}
	}

	override fun notifyObservers() {
		/**	var observersLocal: List<Subscriber>? = null
		//synchronization is used to make sure any observer registered after message is received is not notified
		synchronized(MUTEX) {
		if (!changed)
		return
		observersLocal = ArrayList(this.subscribers)
		this.changed = false
		}
		for (obj in observersLocal!!) {
		obj.update()
		} */
	}

	override fun getUpdate(subscriber: Subscriber): List<Message> {
		val shouldUpdate = subscribers.get(subscriber)
		val messages = mutableListOf<Message>()

		// subscriber is subscribed to this topic
		if (shouldUpdate != null) {
			if (shouldUpdate) {
				for (message in messageThread) {
					message.metaData?.let { metaData ->
						{
							if (metaData.createdAt.before(subscriber.lastUpdate)) {
								messages.add(message)
							}
						}
					}
				}
				subscribers.put(subscriber, true)
			}
		}
		return messages
	}

	override fun postMessage(message: Message) {
		messageThread.add(message)
		println("Current messageThread is ${messageThread}")
		//notifyObservers()
	}
}