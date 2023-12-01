package ru.hogwarts.school.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyRepositories extends JpaRepository<Faculty,Long> {
    Collection<Faculty> findByColorIgnoreCase(String color);

    Collection<Faculty> findByNameIgnoreCase(String name);
}
