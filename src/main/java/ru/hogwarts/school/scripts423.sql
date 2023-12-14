SELECT s.id as student_id , f.id as faculty_id, * FROM student s
JOIN faculty f ON s.faculty_id = f.id;

select s.*
from student s
join avatar a on s.id = a.student_id;

