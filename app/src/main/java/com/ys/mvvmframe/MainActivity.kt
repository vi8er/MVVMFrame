package com.ys.mvvmframe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ys.mvvmframe.ui.DemoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_demo.setOnClickListener {
            startActivity(Intent(this,DemoActivity::class.java))
        }
    }
}
