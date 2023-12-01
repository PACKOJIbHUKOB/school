package ru.hogwarts.school.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;
public interface FacultyRepositories extends JpaRepository<Faculty,Long> {
}
