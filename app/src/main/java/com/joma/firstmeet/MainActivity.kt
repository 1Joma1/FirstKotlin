package com.joma.firstmeet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var sendText: String
    private lateinit var returnText: String
    private val REQUEST_SEND_TEXT = 21

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView(){
        editText = findViewById(R.id.edit_text)
        button = findViewById(R.id.send_btn)
        button.setOnClickListener {
            send()
        }
    }

    private fun send(){
        sendText = editText.text.toString()
        if (notNull(sendText)){
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("text", sendText)
            startActivityForResult(intent, REQUEST_SEND_TEXT)
        } else{
            Toast.makeText(this, "Пожалуйста, заполните поле", Toast.LENGTH_LONG).show()
        }
    }

    private fun notNull(text: String):Boolean{
        return text != ""
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SEND_TEXT && resultCode == Activity.RESULT_OK){
            returnText = data!!.getStringExtra("returnText")
            if (!sendText.equals(returnText)){
                Toast.makeText(this, "Данные из Second Activity были изменены с $sendText на $returnText", Toast.LENGTH_LONG).show()
            }
        }

    }
}
