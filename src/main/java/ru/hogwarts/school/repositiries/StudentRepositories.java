package ru.hogwarts.school.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepositories extends JpaRepository<Student,Long> {
    Collection<Student> findAllByAgeBetween(Integer min, Integer max);
    List<Student> findByFacultyId(Long facultyId);

}