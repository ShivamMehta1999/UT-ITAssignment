package com.knoldus.request

import com.knoldus.models.Employee
import com.knoldus.validator.EmployeeValidator
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.funsuite.AnyFunSuite

class EmployeeImplTest extends AnyFunSuite{
  val employeeValidator = mock[EmployeeValidator]
  val Shivam:Employee = new Employee("Shivam","Mehta",22,12000,"Intern","Knoldus","shivam.mehta@gmailcom")
  val userImpl = new EmployeeImpl(employeeValidator)

  test("User can be ceated"){
    when(employeeValidator.employeeIsValid(Shivam)) thenReturn(true)
    val result = userImpl.createEmployee(Shivam)
    assert(result.isDefined)
  }
  test("User can not be created"){
    when(employeeValidator.employeeIsValid(Shivam)) thenReturn(false)
    val result = userImpl.createEmployee(Shivam)
    assert(result.isEmpty)
  }
}