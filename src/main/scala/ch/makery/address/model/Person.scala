package ch.makery.address.model

import scalafx.beans.property.{ObjectProperty, StringProperty}

import java.time.LocalDate

class Person (firstNameS:String, lastNameS:String){
  val firstName = new StringProperty(firstNameS)
  val lastName = new StringProperty(lastNameS)
  val street = new StringProperty("Jalan Universiti")
  val city = new StringProperty("Bandar Sunway")
  val postalCode = ObjectProperty[Int](47500)
  val date = ObjectProperty[LocalDate](LocalDate.of(1999,2,21))

}