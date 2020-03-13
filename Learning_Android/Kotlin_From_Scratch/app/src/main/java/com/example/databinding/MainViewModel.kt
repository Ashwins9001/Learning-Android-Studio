package com.example.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){
    val currentRandomFruitName : LiveData<String>
        get() = FakeRepository.currentRandomFruitName //access from pub property of cls, set var of ViewModel to it for XML changes
    //btn listener & var currentRandomFruitName ref directly in XML, thus viewmodel stores data and affects UI
    fun onChangeRandomFruitClick() = FakeRepository.changeCurrentRandomFruitName() //set onClick to get currentRandomFruitName & set it for obj


}

//App architecture way of designing app classes and relationships b/w them, follow MVVM here
//ViewModel holds data (LiveData) to be displayed to fragment/activity associated with it
//UI controller UI-based class such as activity/fragment that handles displaying views and taking user input
//ViewModel prepares data and determines when UI controller can access
//ViewModelFactory instantiates ViewModel obj w/ or w/ out constructors