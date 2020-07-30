package com.sjc.coroutinedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_coroutine2_main.*

class Coroutine2MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine2_main)

        button_coroutine2.setOnClickListener {
            startActivity(Intent(this, CoroutineDemoMainActivity::class.java))
        }
    }
}