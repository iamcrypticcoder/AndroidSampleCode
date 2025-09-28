// IRemoteServiceListener.aidl
package com.iamcrypticcoder.service;
import com.iamcrypticcoder.service.Company;
import com.iamcrypticcoder.service.Employee;


interface IRemoteServiceListener {
    void onCompanyCreated(inout Company company);
    void onEmployeeAdded(inout Employee employee);
    void onEmployeeRemoved(inout Employee employee);
    void onLongTaskStarted(String taskId);
    void onLongTaskInProgress(String taskId, float progress);
    void onLongTaskCompleted(String taskId);
}