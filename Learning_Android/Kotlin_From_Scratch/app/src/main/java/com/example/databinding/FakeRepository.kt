package com.example.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

//Simple fake database to retrieve from
object FakeRepository {
    private val fruitNames: List<String> = listOf("Apple", "Banana", "Orange", "Kiwi",
        "Grapes", "Figs", "Pears", "Strawberries", "Raspberries")
    //encapsulation to protect mutable data
    //MutableLiveData generic class w specified data type at instantiation
    private val _currentRandomFruitName = MutableLiveData<String>()
    val currentRandomFruitName: LiveData<String>
        //custom getter (kotlin-only), called every time used
        get() = _currentRandomFruitName //init public var from private
    init { //runs when instance made
        //Use .value property to modify livedata
        _currentRandomFruitName.value = fruitNames.first()
    }
    //func gen random fruit name from list
    fun getRandomFruitName(): String {
        val random = Random()
        return fruitNames[random.nextInt(fruitNames.size)]
    }
    fun changeCurrentRandomFruitName() {
        _currentRandomFruitName.value = getRandomFruitName()
    }
}
//More on livedata: android architecture comp that builds data obj which notify views when database changes
//Set up observers (such as activities, fragments) that check for data changes, and it is lifecycle aware
//Updates thus only occur in active life-cycle state (such as not during onPause or onDestroy methods)
//Attach Observer objects to livedata objects, use lambda func to defn input param that ref livedata & respective view-element to be modified
//for live data itme: _currentRandomFruitName, can attach observer via following & ref textview:
//viewModel._currentRandomFruitName.observe(viewLifeCycleOwner, Observer {newScore -> binding.scoreText.text = newScore.toString()})
//Observer pattern implemented where comms b/w observable ("subject" of observation) and observer occur
//Observable notifies observers of its behavior, LiveData is observable, observers are UI controllers such as fragments/activities
//Whenever data wrapped inside LiveData changes, state change occurs, allows for UI changes given data change, must be careful to avoid lifecycle bugs such as repeating elem

