package com.comquas.tgif3_demo

import android.app.DownloadManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import khttp.responses.Response
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {

    var button: Button? = null
    var responseView: TextView? = null

    var data1: EditText? = null
    var data2: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.sendButton)
        responseView = findViewById(R.id.responseText)

        data1 = findViewById(R.id.data1Txt)

        data2 = findViewById(R.id.data2Txt)

        button?.setOnClickListener {
            sendDataRequest()
        }
    }

    fun sendDataRequest() {
        responseView?.text = "Loading..."
        val val1: String = data1!!.text.toString()
        val val2: String = data2!!.text.toString()

        var jsonVal =  mapOf("data1" to val1, "data2" to val2)

        doAsync {

            //val repoListJsonStr = URL("http://10.0.2.2:8000/mim").readText()
            val resp: Response = khttp.post(
                //url = "http://10.0.2.2:8000/tgif/mim",
                url = "https://blog.saturngod.net",
                json = jsonVal)

            uiThread {
                responseView?.text = resp.text
            }
        }




    }
}
