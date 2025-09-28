// IRemoteService.aidl
package com.iamcrypticcoder.service;
import com.iamcrypticcoder.service.Company;
import com.iamcrypticcoder.service.Employee;
import com.iamcrypticcoder.service.IRemoteServiceListener;

// Declare any non-default types here with import statements

interface IRemoteService {

    /** Request the process ID of this service, to do evil things with it. */
    int getPid();

    void registerListener(IRemoteServiceListener listener);
    void unregisterListener(IRemoteServiceListener listener);

    void createCompany(inout Company company);
    void addEmployee(String companyName , inout Employee employee);
    void removeEmployee(String companyName, inout Employee employee);
    Company getCompany(String companyName);
    void doLongTask(String taskId);
}