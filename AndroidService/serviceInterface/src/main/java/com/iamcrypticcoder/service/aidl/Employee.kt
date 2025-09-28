package com.iamcrypticcoder.service.aidl

import android.os.Parcel
import android.os.Parcelable

class Employee : Parcelable {
    var id: Long = -1
    lateinit var name: String
    var age: Int = -1
    var salary: Int = -1
    lateinit var role: String

    constructor()
    constructor(id: Long, name: String, age: Int,
                salary: Int, role: String) {
        this.id = id
        this.name = name
        this.age = age
        this.salary = salary
        this.role = role
    }

    constructor(inParcel: Parcel) : this() {
        readFromParcel(inParcel)
    }

    override fun describeContents(): Int {
        return 0;
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeInt(salary)
        parcel.writeString(role)
    }

    companion object CREATOR : Parcelable.Creator<Employee> {
        override fun createFromParcel(parcel: Parcel): Employee {
            return Employee(parcel)
        }

        override fun newArray(size: Int): Array<Employee?> {
            return arrayOfNulls(size)
        }
    }

    fun readFromParcel(inParcel: Parcel) {
        name = inParcel.readString().toString()
        age = inParcel.readInt()
        salary = inParcel.readInt()
        role = inParcel.readString().toString()
    }
}
