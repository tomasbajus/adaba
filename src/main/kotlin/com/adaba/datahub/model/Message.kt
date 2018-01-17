package com.adaba.datahub.model

import java.util.*

interface Message {

	val id : UUID?
	val topicId : UUID?
	val title : String?
	val content : String?
	var metaData : MetaData?
}