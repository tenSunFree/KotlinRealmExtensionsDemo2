package com.home.kotlinrealmextensionsdemo2.view.first

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.home.kotlinrealmextensionsdemo2.R
import com.home.kotlinrealmextensionsdemo2.view.main.MainActivity

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }

    fun goToNext(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
