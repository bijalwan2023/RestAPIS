package com.loginAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loginAPI.Entity.Employee;

public interface EmpRepository extends JpaRepository<Employee, Long> {

}
