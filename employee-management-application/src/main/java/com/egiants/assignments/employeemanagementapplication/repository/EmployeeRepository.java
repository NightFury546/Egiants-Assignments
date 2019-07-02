package com.egiants.assignments.employeemanagementapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egiants.assignments.employeemanagementapplication.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}