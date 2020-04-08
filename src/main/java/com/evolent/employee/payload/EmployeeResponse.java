/**
 * 
 */
package com.evolent.employee.payload;

/**
 * @author Ganagdhar
 *
 */
public class EmployeeResponse extends ApiResponse {



  private Integer id;

  private String firstName;

  private String lastName;

  private String email;

  private String phoneNumber;

  private Boolean status;

  private String statusDisplay = "Active";

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }



  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the phoneNumber
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * @param phoneNumber the phoneNumber to set
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * @return the status
   */
  public Boolean getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(Boolean status) {
    this.status = status;
  }

  /**
   * @return the statusDisplay
   */
  public String getStatusDisplay() {
    return statusDisplay;
  }

  /**
   * @param statusDisplay the statusDisplay to set
   */
  public void setStatusDisplay(String statusDisplay) {
    this.statusDisplay = statusDisplay;
  }

}
