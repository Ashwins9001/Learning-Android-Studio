package com.example.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set up ViewModel, require factory method which returns instance of same class
        //Gets or creates instance of main view model
        val mainViewModel = ViewModelProviders().of(this).get(MainViewModel::class.java)

        //Every data-binding layout (XML) gets ActivityMainBinding generated cls, defn activity & layout to inflate
        //
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            this.setLifeCycleOwner(this@MainActivity) //return binding obj, need to set properties via apply, setLifeCycleOwner for LiveData so it knows when onPause, onDestroyed, etc. called
            this.viewmodel = mainViewModel //set this equal to view model set up in main
        }
        //it: editTextContent var of type str being observed, that gets returned to current activity instance for toast
        mainViewModel.editTextContent.observe(this, Observer { Toast.makeText(this, it, Toast.LENGTH_SHORT).show()})
        }
    }
}
