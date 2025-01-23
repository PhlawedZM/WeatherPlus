package com.zachm.weatherplus

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.nl.entityextraction.Entity
import com.google.mlkit.nl.entityextraction.EntityExtractor
import com.zachm.weatherplus.util.EntityRecognition
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class ChatViewModel : ViewModel() {

    val error: MutableLiveData<String> by lazy { MutableLiveData<String>(null) }

    private val _client = MutableStateFlow<EntityExtractor?>(null)
    val client: StateFlow<EntityExtractor?> get() = _client

    private val _downloaded = MutableStateFlow(false)
    val downloaded: StateFlow<Boolean> get() = _downloaded

    private val _entities = MutableStateFlow<List<Entity>>(listOf())
    val entities: StateFlow<List<Entity>> get() = _entities


    fun getClient() {
        _client.value = EntityRecognition().getClient()

        client.value?.let {
            downloadModel()
        }
    }

    fun recognize(text: String) {
        if(!downloaded.value) { return }

        viewModelScope.launch {
            try {
                EntityRecognition().recognize(client.value!!, text) { annotations, result ->
                    if(result) {
                        _entities.value = EntityRecognition().getEntities(annotations)
                        Log.d("EntityRecognition", _entities.value.toString())
                    }
                }
            }
            catch (e: Exception) {
                Log.d("EntityRecognition", e.message.toString())
                error.value = e.message
            }
        }
    }

    private fun downloadModel() {
        viewModelScope.launch {
            withTimeout(10000) {
                try {
                    client.value?.let {
                        EntityRecognition().downloadModel(it) { result ->
                            _downloaded.value = result
                        }
                    }
                }
                catch (e: Exception) {
                    Log.d("EntityRecognition", e.message.toString())
                    error.value = e.message
                }
            }
        }
    }

}