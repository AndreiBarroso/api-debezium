package com.andreibarroso.apidebezium.service;

import com.andreibarroso.apidebezium.entity.Student;
import com.andreibarroso.apidebezium.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.debezium.data.Envelope.Operation;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentService {

    /**
     * Handle to ElasticSearch
     */
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Updates/Inserts/Delete student data.
     *
     * @param studentData
     * @param operation
     */
    public void maintainReadModel(Map<String, Object> studentData, Operation operation) {
        final ObjectMapper mapper = new ObjectMapper();
        final Student student = mapper.convertValue(studentData, Student.class);

        if (Operation.DELETE.name().equals(operation.name())) {
            studentRepository.deleteById(student.getId());
        } else {
            studentRepository.save(student);
        }
    }
}
