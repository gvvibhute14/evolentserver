/**
 * 
 */
package com.evolent.controllertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;

import com.evolent.employee.controller.EmployeeController;
 
import com.evolent.employee.payload.EmployeeResponse;
import com.evolent.employee.service.EmployeeReposServiceImpl;
 
/**
 * @author Gangadhar
 *
 */

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class EmployeeControllerUnitTests {

  @InjectMocks
  EmployeeController employeeController;
  
  @Mock
  EmployeeReposServiceImpl employeeReposService;
  
  @Test
  public void testGetAll() 
  {
      // given
    List<EmployeeResponse> employeeResponse = new ArrayList<EmployeeResponse>();
    EmployeeResponse employeeRequest = new EmployeeResponse();
    employeeRequest.setEmail("ghb@gmail.com");
    employeeRequest.setFirstName("ghbnm");
    employeeRequest.setLastName("ghbnm");
    employeeRequest.setPhoneNumber("56893214");

    employeeResponse.add(employeeRequest);
    
      when(employeeReposService.getAllEmployee()).thenReturn(employeeResponse);

      // when
      List<EmployeeResponse> result =  employeeReposService.getAllEmployee();

      // then
      assertThat(result.size()).isEqualTo(1);
      
      assertThat(result.get(0).getFirstName())
                      .isEqualTo(employeeRequest.getFirstName());
      
      assertThat(result.get(0).getLastName())
                      .isEqualTo(employeeRequest.getLastName());
  }
}
