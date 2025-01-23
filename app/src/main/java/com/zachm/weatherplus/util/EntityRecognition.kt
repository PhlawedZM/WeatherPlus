package com.zachm.weatherplus.util

import com.google.mlkit.nl.entityextraction.Entity
import com.google.mlkit.nl.entityextraction.EntityAnnotation
import com.google.mlkit.nl.entityextraction.EntityExtraction
import com.google.mlkit.nl.entityextraction.EntityExtractor
import com.google.mlkit.nl.entityextraction.EntityExtractorOptions

class EntityRecognition {

    fun getClient() : EntityExtractor {
        return EntityExtraction.getClient(
            EntityExtractorOptions.Builder(EntityExtractorOptions.ENGLISH).build()
        )
    }

    fun downloadModel(client: EntityExtractor, callback: (Boolean) -> Unit) {
        client.downloadModelIfNeeded().addOnSuccessListener {
            callback(true)
        }.addOnFailureListener {
            callback(false)
        }
    }

    fun recognize(client: EntityExtractor, text: String, callback: (List<EntityAnnotation>, Boolean) -> Unit) {
        client.annotate(text).addOnSuccessListener {
            callback(it, true)
        }.addOnFailureListener {
            callback(listOf(), false)
        }
    }

    fun getEntities(annotations: List<EntityAnnotation>): List<Entity> {
        val list = mutableListOf<Entity>()

        annotations.forEach {
            it.entities.forEach { entity ->
                when(entity.type) {
                    Entity.TYPE_DATE_TIME -> list.add(entity)
                    Entity.TYPE_ADDRESS -> list.add(entity)
                }
            }
        }
        return list
    }
}