package com.ys.mvvmframe.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ys.base_lib.base.BaseViewModel
import com.ys.mvvmframe.repository.DemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DemoViewModel:BaseViewModel() {

    private val repository by lazy { DemoRepository() }
    val mStr:MutableLiveData<String> = MutableLiveData()

    fun getText(){
        launchOnUI{
            val response = withContext(Dispatchers.IO){repository.getText()}
            mStr.value = response.data
        }
    }

    fun changeText(){
        launchOnUI{
            val response =  withContext(Dispatchers.IO){repository.changeText()}
            mStr.value = response.data
        }
    }
}