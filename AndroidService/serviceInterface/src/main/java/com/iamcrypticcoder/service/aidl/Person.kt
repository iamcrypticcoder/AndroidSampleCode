package com.iamcrypticcoder.service.aidl

import android.os.Parcel
import android.os.Parcelable

class Person: Parcelable {
    lateinit var name: String
    var age: Int = 0

    constructor()
    constructor(name: String, age: Int) : this() {
        this.name = name
        this.age = age
    }

    private constructor(inParcel: Parcel) : this() {
        readFromParcel(inParcel)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }

    fun readFromParcel(inParcel: Parcel) {
        name = inParcel.readString().toString()
        age = inParcel.readInt()
    }
}
