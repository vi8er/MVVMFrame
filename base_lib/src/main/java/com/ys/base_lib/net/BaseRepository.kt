package com.ys.base_lib.net

open class BaseRepository {

    suspend fun <T:Any> getCall(call:suspend ()->BaseResponse<T>):BaseResponse<T>{
        return call.invoke()
    }
}