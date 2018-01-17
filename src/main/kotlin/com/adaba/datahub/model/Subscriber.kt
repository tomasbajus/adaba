package com.adaba.datahub.model

import java.util.*

interface Subscriber {

	val id : UUID
	val observerName : String
	val topics: MutableSet<Topic>
	var lastUpdate : Date
	fun update() : List<Message>
}