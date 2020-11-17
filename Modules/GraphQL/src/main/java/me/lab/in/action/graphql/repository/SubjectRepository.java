package me.lab.in.action.graphql.repository;

import me.lab.in.action.graphql.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
