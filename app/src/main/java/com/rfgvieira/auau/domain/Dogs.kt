package com.rfgvieira.auau.domain

import com.rfgvieira.auau.R

class Dogs {
    companion object {
        fun dogsList(): List<Dog> {


            val result = ArrayList<Dog>()
            val dog1 = Dog("Poe", "20/12/2020", R.drawable.pug, "Meat")
            val dog2 = Dog("Joe", "01/06/2017", R.drawable.dog1, "Dog food")
            val dog3 = Dog("Fluffy", "28/09/2001", R.drawable.dog2, "Dog food")
            val dog4 = Dog("Penelope", "06/07/2006", R.drawable.dog3, "Fish")
            val dog5 = Dog("Bear", "01/04/2011", R.drawable.dog4,"Chocolate")
            val dog6 = Dog("Rudolph", "19/12/2015", R.drawable.dog5,"Pear")
            val dog7 = Dog("Rex", "09/11/2013", R.drawable.dog6,"Mango")
            val dog8 = Dog("Chin", "04/04/1999", R.drawable.dog7,"Meat")
            val dog9 = Dog("Fry", "12/01/2020", R.drawable.dog8,"Bones")
            val dog10 = Dog("Bob", "30/10/2014", R.drawable.dog9,"Dog food")
            val dog11 = Dog("Poti", "10/08/2020", R.drawable.dog10,"Chesse")
            val dog12 = Dog("Bode", "25/10/2015", R.drawable.dog11,"Rice")

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

            return result
        }
    }
}