package com.ys.base_lib.base

import androidx.annotation.CallSuper
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

/**
 * Base fragment with viewmodel
 */
abstract class BaseMVVMFragment<VM:BaseViewModel>:BaseFragment() {

    protected lateinit var mViewModel: VM

    protected abstract fun privoderVM(): Class<VM>

    @CallSuper
    override fun initData() {
        privoderVM().let {
            mViewModel = ViewModelProvider(this).get(privoderVM())
            lifecycle.addObserver(mViewModel)
            //all Throwable deal by {@link #onError()}
            mViewModel.mException.observe(this, Observer { it?.let { onError(it) } })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel)
    }

    open fun onError(e: Throwable) {}
}