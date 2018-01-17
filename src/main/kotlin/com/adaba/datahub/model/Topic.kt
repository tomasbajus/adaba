package com.adaba.datahub.model

import java.util.*

interface Topic {

	var id : UUID
	var name : String
	var shortDescription : String?
	val messageThread : MutableList<Message>
	var currentLocation : Location?
	val locationHistory : MutableList<Location>
	var lastUpdateAt : Date
	val subscribers: MutableMap<Subscriber, Date>


	fun register(obj: Subscriber)
	fun unregister(obj: Subscriber)
	fun notifyObservers()
	fun postMessage(message : Message)

	fun getUpdate(subscriber: Subscriber): List<Message>
}