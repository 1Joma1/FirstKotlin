package com.joma.firstmeet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var receivedText: String
    private lateinit var sendTextBack: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        initView()
    }

    private fun initView(){
        editText = findViewById(R.id.return_edit_text)
        button = findViewById(R.id.return_btn)
        button.setOnClickListener {
            sendBack()
        }
        receivedText = intent.getStringExtra("text")
        editText.setText(receivedText)
    }

    private fun sendBack(){
        sendTextBack = editText.text.toString()
        if (notNull(sendTextBack)){
            setResult(Activity.RESULT_OK, Intent(this, MainActivity::class.java).putExtra("returnText", sendTextBack))
            finish()
        } else{
            Toast.makeText(this, "Пожалуйста заполните поле", Toast.LENGTH_LONG).show()
        }
    }

    private fun notNull(text: String?):Boolean{
        return text != null
    }
}
