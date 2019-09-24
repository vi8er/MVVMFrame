package com.ys.mvvmframe.repository

import com.ys.base_lib.net.BaseRepository
import com.ys.base_lib.net.BaseResponse
import kotlinx.coroutines.delay

class DemoRepository:BaseRepository() {

    suspend fun getText():BaseResponse<String>{
        val response = BaseResponse<String>(200, "", "text")
        delay(2000)
        return response
    }

    suspend fun changeText():BaseResponse<String>{
        val response = BaseResponse<String>(200, "", "changed")
        delay(2000)
        return response
    }

}