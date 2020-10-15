package me.lab.in.action.graphql.repository;

import me.lab.in.action.graphql.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
}
