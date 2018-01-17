package com.adaba.datahub.model.impl

import com.adaba.datahub.model.Message
import com.adaba.datahub.model.MetaData
import java.util.*

data class MessageImpl(override val id: UUID?, override val topicId: UUID?, override val title: String?, override val content: String?, override var metaData: MetaData?) : Message