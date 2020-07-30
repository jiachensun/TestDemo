package com.sjc.coroutinedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineDemoMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendBT.setOnClickListener{
            coroutineSend()
        }
    }

    private fun coroutineSend() {
        val uiScope = MainScope() + CoroutineName("CoroutineDemoMainActivity")
//        val uiScope = CoroutineScope(Dispatchers.Main)
        uiScope.launch {
            sendBT.visibility = View.GONE
            loadingPB.visibility = View.VISIBLE
            val deffer = async(Dispatchers.Default) {
                getCoroutineResult()
            }
            val coroutineResult = deffer.await()
            timeTV.text = "get $coroutineResult"
            loadingPB.visibility = View.GONE
            sendBT.visibility = View.VISIBLE
            Toast.makeText(this@CoroutineDemoMainActivity, coroutineResult, Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun getCoroutineResult():String {
        delay(3000L)
        return "coroutine result"
    }
}
