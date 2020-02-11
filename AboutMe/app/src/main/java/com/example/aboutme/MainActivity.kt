package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    //lateinit used to define properties without constructor
    //var = variable, val = constant
    //var name: dateType
    private lateinit var binding: ActivityMainBinding

    private val myName: MyName = MyName("Aerfaf")
    //declare functions w: fun
    //? performs a safe call -> method/property accessed if callee not null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //data binding is obj w ref to view, saves time when searching for obj in deep hierarchies
        //compiler generates names of views in binding obj from layout IDs
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //grab ref to DONE btn, define onClickListener

        //single param lambda func
        //lambda func allows you to pass one func as arg to another, encased by {}
        //it allows param inference, setOnClickListener() defined elsewhere
        //as setOnClickListener(myFunc: View->onClickObj)
        //no need to worry about strict implementation details
        //upon func call it will set up View to execute at click as defined in Listener()
        binding.doneButton.setOnClickListener{addNickname(it)}
        //another lambda func but ref TextView, made to reset for more input
        findViewById<TextView>(R.id.nickname_text).setOnClickListener{updateNickname(it)}
        binding.myName = myName
    }
    //pass in view for btn
    private fun addNickname(view: View)
    {
        val editText = binding.nicknameEdit
        val nicknameTextView = binding.nicknameText
        nicknameTextView.text = editText.text.toString()
        editText.visibility = View.GONE
        view.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE
        //as casts object to another type
        //input method mgr is client-side API in every app context and comms w global system
        //input method implements interaction model allowing user to gen text
        //system binds to current input method, tells it to hide/show
        //many client apps can req control for input, mgr handles
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname(view: View)
    {
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val button = findViewById<Button>(R.id.done_button)
        editText.visibility = View.VISIBLE
        button.visibility = View.VISIBLE
        view.visibility = View.GONE

    }
}
