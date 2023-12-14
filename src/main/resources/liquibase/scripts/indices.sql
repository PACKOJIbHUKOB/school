-- liquibase formatted sql

-- changeSet aspirin:1
CREATE INDEX student_name_index on student (name);


--changeSet aspirin:2
CREATE INDEX faculty_name_color_idx on faculty (name,color);




