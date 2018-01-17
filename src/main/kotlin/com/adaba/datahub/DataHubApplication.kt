package com.adaba.datahub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DataHubApplication

fun main(args: Array<String>) {
	runApplication<DataHubApplication>(*args)
}
