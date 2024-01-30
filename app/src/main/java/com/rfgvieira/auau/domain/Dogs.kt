package com.rfgvieira.auau.domain

import com.rfgvieira.auau.R

class Dogs {
    companion object {
        private val result = ArrayList<Dog>()
        fun dogsList(): List<Dog> {
            if (result.isEmpty()) {
                initializeResult()
            }
            return result
        }

        private fun initializeResult() {
            val dog1 = Dog("Poe", "20/12/2020", R.drawable.pug, favoriteFood = "Meat")
            val dog2 = Dog("Joe", "01/06/2017", R.drawable.dog1, favoriteFood = "Dog food")
            val dog3 = Dog("Fluffy", "28/09/2001", R.drawable.dog2, favoriteFood =  "Dog food")
            val dog4 = Dog("Penelope", "06/07/2006", R.drawable.dog3, favoriteFood = "Fish")
            val dog5 = Dog("Bear", "01/04/2011", R.drawable.dog4, favoriteFood = "Chocolate")
            val dog6 = Dog("Rudolph", "19/12/2015", R.drawable.dog5, favoriteFood = "Pear")
            val dog7 = Dog("Rex", "09/11/2013", R.drawable.dog6, favoriteFood = "Mango")
            val dog8 = Dog("Chin", "04/04/1999", R.drawable.dog7, favoriteFood = "Meat")
            val dog9 = Dog("Fry", "12/01/2020", R.drawable.dog8, favoriteFood = "Bones")
            val dog10 = Dog("Bob", "30/10/2014", R.drawable.dog9, favoriteFood = "Dog food")
            val dog11 = Dog("Poti", "10/08/2020", R.drawable.dog10, favoriteFood = "Chesse")
            val dog12 = Dog("Bode", "25/10/2015", R.drawable.dog11, favoriteFood = "Rice")

            result.add(dog1)
            result.add(dog2)
            result.add(dog3)
            result.add(dog4)
            result.add(dog5)
            result.add(dog6)
            result.add(dog7)
            result.add(dog8)
            result.add(dog9)
            result.add(dog10)
            result.add(dog11)
            result.add(dog12)
        }

        fun addNewDog(newDog : Dog){
            result.add(newDog)
        }
    }
}