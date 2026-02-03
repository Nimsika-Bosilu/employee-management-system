package edu.nimsika.ecom.repository;

import edu.nimsika.ecom.model.DepartmentEntity;
import edu.nimsika.ecom.model.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

    }

