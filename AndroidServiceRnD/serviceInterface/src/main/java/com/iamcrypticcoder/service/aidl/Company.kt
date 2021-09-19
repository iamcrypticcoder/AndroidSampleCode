package com.iamcrypticcoder.service.aidl

import android.os.Parcel
import android.os.Parcelable

class Company : Parcelable {
    lateinit var name: String
    var employeeList: MutableList<Employee> = mutableListOf()

    constructor()
    constructor(name: String) {
        this.name = name
    }
    constructor(parcel: Parcel) : this() {
        readFromParcel(parcel)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeTypedList(employeeList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Company> {
        override fun createFromParcel(parcel: Parcel): Company {
            return Company(parcel)
        }

        override fun newArray(size: Int): Array<Company?> {
            return arrayOfNulls(size)
        }
    }

    fun readFromParcel(inParcel: Parcel) {
        name = inParcel.readString().toString()
        employeeList = inParcel.createTypedArrayList(Employee)!!
    }

}
