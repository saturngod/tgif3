package com.comquas.tgif3_demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import khttp.responses.Response
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class HashNative : AppCompatActivity() {

    var button: Button? = null
    var responseView: TextView? = null
    var hashView: TextView? = null

    var data1: EditText? = null
    var data2: EditText? = null

    external fun getNativeKey():String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hash_native)

        System.loadLibrary("native-lib")

        button = findViewById(R.id.sendButton)
        responseView = findViewById(R.id.responseText)
        hashView = findViewById(R.id.hashText)


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

        val key = getNativeKey();
        val hasher = HmacSha256.hmacSHA256(key.toByteArray(),val1+val2)


        val hash: String = HmacSha256.hex(hasher)

        hashView?.text = hash

        var jsonVal =  mapOf("data1" to val1, "data2" to val2, "hash" to hash)


        doAsync {

            //val repoListJsonStr = URL("http://10.0.2.2:8000/mim").readText()
            val resp: Response = khttp.post(
                url = "http://10.0.2.2:8000/tgif/mim_hash_native",
                json = jsonVal)

            uiThread {
                responseView?.text = resp.text
            }
        }





    }
}
