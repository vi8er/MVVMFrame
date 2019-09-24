package com.ys.mvvmframe.ui

import androidx.lifecycle.Observer
import com.ys.base_lib.base.BaseMVVMActivity
import com.ys.mvvmframe.R
import com.ys.mvvmframe.viewmodel.DemoViewModel
import kotlinx.android.synthetic.main.activity_demo.*

class DemoActivity:BaseMVVMActivity<DemoViewModel>() {
    override var layoutId: Int
        get() = R.layout.activity_demo
        set(value) {}

    override fun initView() {
        tv_text.setOnClickListener { mViewModel.changeText() }
    }

    override fun initData() {
        super.initData()
        mViewModel.mStr.observe(this@DemoActivity, Observer{
            tv_text.text = it
        })
        mViewModel.getText()
    }

    override fun providerVM(): Class<DemoViewModel> {
        return DemoViewModel::class.java
    }
}