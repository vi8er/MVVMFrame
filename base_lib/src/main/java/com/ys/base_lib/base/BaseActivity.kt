package com.ys.base_lib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<VM:BaseViewModel>: AppCompatActivity() {

    protected abstract var layoutId: Int

    protected abstract fun initView()

    protected abstract fun initData()

    protected lateinit var mViewModel: VM

    abstract fun providerVM():Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        providerVM().let {
            mViewModel = ViewModelProvider(this).get(it)
            lifecycle.addObserver(mViewModel)
            mViewModel.mException.observe(this, Observer { it?.let { onError(it) } })
        }
        initView()
        initData()
    }

    open fun onError(e:Throwable) {}

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel)
    }
}