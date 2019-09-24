package com.ys.base_lib.base

import androidx.annotation.CallSuper
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

/**
 * base activity with viewmodel
 */
abstract class BaseMVVMActivity<VM:BaseViewModel>: BaseActivity() {

    protected lateinit var mViewModel: VM

    abstract fun providerVM():Class<VM>

    @CallSuper
    override fun initData() {
        providerVM().let {
            mViewModel = ViewModelProvider(this).get(it)
            lifecycle.addObserver(mViewModel)
            //all Throwable deal by {@link #onError()}
            mViewModel.mException.observe(this, Observer { it?.let { onError(it) } })
        }
    }

    open fun onError(e:Throwable) {}

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel)
    }

}