package com.delcons.features.employee.repository;

import com.delcons.features.employee.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findEmployeesByActive(Boolean active);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.active = false WHERE e.id= :id")
    int deleteEmployeeById(long id);

    List<Employee> getEmployeesByActive(Boolean active);

    Employee findEmployeesByDni(Long dni);
}
