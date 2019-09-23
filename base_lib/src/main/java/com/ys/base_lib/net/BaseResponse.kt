package com.ys.base_lib.net

data class BaseResponse<out T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T
)