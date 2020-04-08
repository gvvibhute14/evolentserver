/**
 * 
 */
package com.evolent.employee.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evolent.employee.datamodel.Employee;
import com.evolent.employee.payload.ApiResponse;
import com.evolent.employee.payload.EmployeeRequest;
import com.evolent.employee.payload.EmployeeResponse;
import com.evolent.employee.repository.EmployeeRepository;
import com.evolent.exception.AppException;

/**
 * @author Gangadhar
 *
 */
@Service
public class EmployeeReposServiceImpl {

  private static final Logger logger = LoggerFactory.getLogger(EmployeeReposServiceImpl.class);

  @Autowired
  private EmployeeRepository employeeRepository;

  public List<EmployeeResponse> getAllEmployee() {
    try {
      List<EmployeeResponse> lsemployeeResponse =
          employeeRepository.findAll().stream().map(employee -> {
            EmployeeResponse employeeResponse = new EmployeeResponse();
            employeeResponse.setId(employee.getId());
            employeeResponse.setFirstName(employee.getFirstName());
            employeeResponse.setLastName(employee.getLastName());
            employeeResponse.setEmail(employee.getEmail());
            employeeResponse.setStatus(employee.getStatus());
            if (!employee.getStatus()) {
              employeeResponse.setStatusDisplay("Inactive");
            }
            return employeeResponse;
          }).collect(Collectors.toList());
      return lsemployeeResponse;
    } catch (Exception e) {
      logger.error("Internal Exception", e.getMessage(), e);
      throw new AppException("Contact to system admin at internalerror@evolent.com");
    }
  }

  @Transactional
  public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
    EmployeeResponse employeeResponse = new EmployeeResponse();
    employeeResponse.setSuccess(false);
    try {
      if (employeeRepository.existsByEmail(employeeRequest.getEmail())) {

        employeeResponse.setMessage("Email is already taken!");
      } else {
        Employee employee = setEmployee(employeeRequest);
        employee = employeeRepository.save(employee);
        BeanUtils.copyProperties(employee, employeeResponse);
        employeeResponse.setSuccess(true);
        employeeResponse.setMessage("User registered successfully!!");
      }
    } catch (Exception e) {
      logger.error("Internal Exception", e.getMessage(), e);
      throw new AppException("Contact to system admin at internalerror@evolent.com");
    }

    return employeeResponse;

  }

  @Transactional
  public EmployeeResponse updateStatus(Integer id) {
    EmployeeResponse employeeResponse = new EmployeeResponse();
    employeeResponse.setSuccess(false);
    employeeResponse.setMessage("user not updated status successfully");
    try {

      Employee employee = employeeRepository.findById(id).orElse(null);
      if (employee != null) {
        if (employee.getStatus().equals(true)) {

          employee.setStatus(false);

        } else if (employee.getStatus().equals(false)) {
          employee.setStatus(true);
        }
        employeeRepository.save(employee);
        employeeResponse.setSuccess(true);
        employeeResponse.setMessage("user updated status successfully");
        employeeResponse.setStatusDisplay("Inactive");
        BeanUtils.copyProperties(employee, employeeResponse);
        return employeeResponse;
      }

    } catch (Exception e) {
      logger.error("Internal Exception", e.getMessage(), e);
      throw new AppException("Contact to system admin at internalerror@evolent.com");
    }
    return employeeResponse;

  }

  private Employee setEmployee(EmployeeRequest employeeRequest) {
    Employee employee = new Employee();
    employee.setFirstName(employeeRequest.getFirstName());
    employee.setLastName(employeeRequest.getLastName());
    employee.setPhoneNumber(employeeRequest.getPhoneNumber());
    employee.setEmail(employeeRequest.getEmail());

    return employee;
  }

}
