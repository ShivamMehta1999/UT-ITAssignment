package com.knoldus.request

import com.knoldus.db.CompanyReadTo
import com.knoldus.models.Employee
import com.knoldus.validator.{EmailValidator, EmployeeValidator}
import org.scalatest.funsuite.AnyFunSuite


class EmployeeImplIntegrationTest extends AnyFunSuite {
  val companyName = new CompanyReadTo
  val validateEmail = new EmailValidator
  val employeeValidator = new EmployeeValidator(companyName, validateEmail)

  val employeeImpl = new EmployeeImpl(employeeValidator)

  test("User cannot be created because company does not exist"){
    val lucifer: Employee = new Employee("Lucifer", "MorningStar", 22, 10000, "Intern", "GoldMan Sachs", "lucifer.morningstar@gmalcom")
    val result = employeeImpl.createEmployee(lucifer)
    assert(result.isEmpty)
  }

  test("User not created because email is invalid"){
    val Shivam: Employee = new Employee("Shivam", "Mehta", 22, 12000, "Intern", "Knoldus", "shivam.mehta@gmailcom")
    val result = employeeImpl.createEmployee(Shivam)
    assert(result.isEmpty)
  }

  test("User cannot be created because email is invalid and company does not exist in DB"){
    val lucifer: Employee = new Employee("Lucifer", "MorningStar", 22, 10000, "Intern", "GoldMan Sachs", "lucifer.morningstar@gmalcom")
    val result = employeeImpl.createEmployee(lucifer)
    assert(result.isEmpty)
  }

  test("User can be created"){
    val Shivam: Employee = new Employee("Shivam", "Mehta", 22, 1000, "Intern", "Knoldus", "utkarsh.ambastha@gmail.com")
    val result = employeeImpl.createEmployee(Shivam)
    assert(result.isDefined)
  }

}
