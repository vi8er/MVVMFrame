package com.ys.base_lib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * base activity without viewmodel
 */
abstract class BaseActivity:AppCompatActivity() {

    protected abstract var layoutId: Int

    protected abstract fun initView()

    protected abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initView()
        initData()
    }

}