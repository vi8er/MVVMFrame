package com.ys.baselib.net

import okhttp3.Cache
import java.io.File

class HttpCache {
    companion object{
        const val HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 1024 * 1024 * 128
        val cache = Cache(File(""), HTTP_RESPONSE_DISK_CACHE_MAX_SIZE.toLong())
    }
}