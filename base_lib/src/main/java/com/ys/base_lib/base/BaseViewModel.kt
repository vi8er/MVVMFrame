package com.ys.base_lib.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    val mException: MutableLiveData<Throwable> = MutableLiveData()

    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    suspend fun launchOnIO(block: suspend CoroutineScope.() -> Unit){
        withContext(Dispatchers.IO){
            block()
        }
    }

    suspend fun tryCatch(
        tryBlock: suspend CoroutineScope.()->Unit,
        catchBlock: suspend CoroutineScope.(Throwable)->Unit,
        finallyBlock: suspend CoroutineScope.()->Unit
    ){
        coroutineScope{
            try {
                tryBlock()
            } catch (e: Throwable) {
                mException.value = e
                catchBlock(e)
            } finally {
                finallyBlock()
            }
        }
    }



}