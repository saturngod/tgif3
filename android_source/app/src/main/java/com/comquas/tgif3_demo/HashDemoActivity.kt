package com.comquas.tgif3_demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import khttp.responses.Response
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class HashDemoActivity : AppCompatActivity() {

    var button: Button? = null
    var responseView: TextView? = null
    var hashView: TextView? = null

    var data1: EditText? = null
    var data2: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hash_demo)
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

        val hasher = HmacSha256.hmacSHA256("samplekey".toByteArray(),val1+val2)


        val hash: String = HmacSha256.hex(hasher)

        hashView?.text = hash

        var jsonVal =  mapOf("data1" to val1, "data2" to val2, "hash" to hash)


        doAsync {

            //val repoListJsonStr = URL("http://10.0.2.2:8000/mim").readText()
            val resp: Response = khttp.post(
                url = "http://10.0.2.2:8000/tgif/mim_hash",
                json = jsonVal)

            uiThread {
                responseView?.text = resp.text
            }
        }





    }


}

object HmacSha256 {

    fun hash(payload: String): String = hash(payload.toByteArray())

    fun hash(payload: ByteArray): String = try {
        hex(MessageDigest.getInstance("SHA-256").digest(payload))
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
    }

    fun hmacSHA256(key: ByteArray, data: String): ByteArray = try {
        val algorithm = "HmacSHA256"
        Mac.getInstance(algorithm).run {
            init(SecretKeySpec(key, algorithm))
            doFinal(data.toByteArray(charset("UTF8")))
        }
    } catch (e: Exception) {
        throw RuntimeException("Could not run HMAC SHA256", e)
    }

    fun hex(data: ByteArray): String = data.fold(StringBuilder()) { acc, next ->
        acc.append(String.format("%02x", next))
    }.toString().toLowerCase()
}
