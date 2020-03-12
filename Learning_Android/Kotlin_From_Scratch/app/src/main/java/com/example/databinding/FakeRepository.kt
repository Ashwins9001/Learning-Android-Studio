package com.example.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

//Simple fake database to retrieve from
object FakeRepository {
    private val fruitNames: List<String> = listOf("Apple", "Banana", "Orange", "Kiwi",
        "Grapes", "Figs", "Pears", "Strawberries", "Raspberries")
    //encapsulation to protect mutable data
    private val _currentRandomFruitName = MutableLiveData<String>()
    val currentRandomFruitName: LiveData<String>
        get() = _currentRandomFruitName //init public var from private
    init { //runs when instance made
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