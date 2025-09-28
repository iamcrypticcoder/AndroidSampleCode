package com.iamcrypticcoder.service

import com.iamcrypticcoder.service.aidl.Company
import com.iamcrypticcoder.service.aidl.Employee

object FakeDB {
    var companyMap: MutableMap<String, Company> = mutableMapOf()

    fun getCompany(name: String) : Company? {
        return companyMap[name]
    }

    fun createCompany(company: Company) : Boolean {
        if (null != companyMap[company.name]) {
            return false;
        }
        companyMap[company.name] = company
        return true;
    }

    fun addEmployee(companyName: String, employee: Employee) : Boolean {
        if (null == companyMap[companyName])
            return false;
        companyMap[companyName]!!.employeeList.add(employee)
        return true;
    }

    fun removeEmployee(companyName: String, employee: Employee) : Boolean {
        if (null == companyMap[companyName])
            return false;
        for (emp in companyMap[companyName]!!.employeeList) {
            if (emp.equals(employee.name)) {
                companyMap[companyName]!!.employeeList.remove(emp)
                return true
            }
        }
        return false
    }

}