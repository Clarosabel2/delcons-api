package com.delcons.features.employee.repository;

import com.delcons.features.employee.enums.BusinessArea;
import com.delcons.features.employee.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findAllByArea(BusinessArea businessArea, Pageable pageable);
    Page<Employee> findAllByPerson_Active(Pageable pageable, Boolean active);

    Optional<Employee> findEmployeesByPerson_Dni(Long dni);
    Optional<Employee> findByPerson_Email(String email);

    @Transactional @Modifying @Query("UPDATE Person e SET e.active = false WHERE e.id= :id")
    int deleteEmployeeById(long id);
}
