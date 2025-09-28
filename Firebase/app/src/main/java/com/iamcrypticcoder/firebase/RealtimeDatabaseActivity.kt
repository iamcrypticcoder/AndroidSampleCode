package com.iamcrypticcoder.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.iamcrypticcoder.firebase.databinding.ActivityRealtimeDatabaseBinding

class RealtimeDatabaseActivity : AppCompatActivity() {
    private val TAG = RealtimeDatabaseActivity::class.java.simpleName

    private val FIREBASE_EMPLOYEE = "employees"
    private lateinit var binding: ActivityRealtimeDatabaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealtimeDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtons()
    }

    private fun initButtons() {
        binding.addEmployeeButton.setOnClickListener {
            val id : Long = binding.idEditText.text.toString().toLong()
            val name : String = binding.nameEditText.text.toString()
            val role : String = binding.roleEditText.text.toString()
            val age : Int = binding.ageEditText.text.toString().toInt()
            val salary: Int = binding.salaryEditText.text.toString().toInt()

            val employee = Employee(id, name, role, age, salary)
            val employeeDb = Firebase.database.getReference(FIREBASE_EMPLOYEE)
            employeeDb.child(id.toString()).setValue(employee)
        }

        binding.viewEmployeeButton.setOnClickListener {
            val text = binding.searchTextEditText.text.toString()
            val employeeDb = Firebase.database.getReference(FIREBASE_EMPLOYEE)

            employeeDb.child(text).get().addOnSuccessListener {
                Log.d(TAG, "it.exists() = " + it.exists())
                Log.d(TAG, "Id = " + it.child("id").value)
                Log.d(TAG, "Name = " + it.child("name").value)
                Log.d(TAG, "Role = " + it.child("role").value)
                Log.d(TAG, "Age = " + it.child("age").value)
                Log.d(TAG, "Salary = " + it.child("salary").value)

                if (false == it.exists()) {
                    binding.resultTextView.text = "Not Found"
                    return@addOnSuccessListener
                }

                val id : Long = it.child("id").value as Long
                val name : String = it.child("name").value as String
                val role : String = it.child("role").value as String
                val age : Int = (it.child("age").value as Long).toInt()
                val salary : Int = (it.child("salary").value as Long).toInt()

                val employee = Employee(id, name, role, age, salary)
                binding.resultTextView.text = employee.toString()
            }
        }

    }

    private fun writeToDatabase() {
        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")
    }


}