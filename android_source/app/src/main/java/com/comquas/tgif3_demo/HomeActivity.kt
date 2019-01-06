package com.comquas.tgif3_demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.widget.Button

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    val button1: Button? = null
    val button2: Button? = null
    val button3: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val button1: Button? = findViewById(R.id.button)
        val button2: Button? = findViewById(R.id.button2)
        val button3: Button? = findViewById(R.id.button3)

        button1?.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java).apply {

            }

            startActivity(intent)
        }

        button2?.setOnClickListener {
            val intent = Intent(this,HashDemoActivity::class.java).apply {

            }

            startActivity(intent)
        }


        button3?.setOnClickListener {
            val intent = Intent(this,HashNative::class.java).apply {

            }

            startActivity(intent)
        }

    }

}
