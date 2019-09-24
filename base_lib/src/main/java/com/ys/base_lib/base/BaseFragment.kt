package com.ys.base_lib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<VM:BaseViewModel>:Fragment() {

    protected abstract var layoutId: Int

    protected abstract fun initView(view: View)

    protected abstract fun initData()

    protected lateinit var mViewModel: VM

    protected abstract fun privoderVM(): Class<VM>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        privoderVM().let {
            mViewModel = ViewModelProvider(this).get(privoderVM())
            lifecycle.addObserver(mViewModel)
            mViewModel.mException.observe(this, Observer { it?.let { onError(it) } })
        }
        initView(view)
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel)
    }

    open fun onError(e: Throwable) {}
}