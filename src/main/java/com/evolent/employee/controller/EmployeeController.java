package com.evolent.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evolent.employee.payload.ApiResponse;
import com.evolent.employee.payload.EmployeeRequest;
import com.evolent.employee.payload.EmployeeResponse;
import com.evolent.employee.repository.EmployeeRepository;
import com.evolent.employee.service.EmployeeReposServiceImpl;

/**
 * @author Gangadhar
 *
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

  @Autowired
  EmployeeReposServiceImpl employeeReposService;
 

  @GetMapping("/allemployee")
  public List<EmployeeResponse> allEmployee() {
    return employeeReposService.getAllEmployee();
  }

  @DeleteMapping(path = {"/changestatus/{id}"},produces="application/json")
  public ResponseEntity<?> delete(@PathVariable("id") int id) {
    EmployeeResponse employeeResponse = employeeReposService.updateStatus(id);

    return ResponseEntity.accepted().body(employeeResponse);
  }

  @PostMapping(path ="/createmployee",produces="application/json")
  public ResponseEntity<?> create(@Valid @RequestBody EmployeeRequest employeeRequest) {
    EmployeeResponse employeeResponse = employeeReposService.createEmployee(employeeRequest);
    return ResponseEntity.accepted().body(employeeResponse);
  }

}
