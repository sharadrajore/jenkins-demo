package com.zensar.springbootdemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.springbootdemo.dto.StudentDto;
import com.zensar.springbootdemo.entity.Student;
import com.zensar.springbootdemo.exceptions.StudentNotFoundException;
import com.zensar.springbootdemo.service.StudentService;

@RestController
@RequestMapping(value = "/student-api", produces = { MediaType.APPLICATION_JSON_VALUE,
		MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE })
public class StudentController {
	
	@GetMapping("/hello")
	public String sayHello() {
		System.out.println("Added Lines");
		return "<h2> Spring Boot + Jenkins + GitHub !!!! </h2>";
	}

	@Autowired
	private StudentService studentService;

	// http://localhost:8080/student-api/students/Ram GET
	// http://localhost:8080/student-api/students/1001 GET
	// @RequestMapping(value = "/students/{studentId}",method=RequestMethod.GET)
	@GetMapping(value = "/students/{studentId}")
	public ResponseEntity<StudentDto> getStudent(@PathVariable("studentId") int studentId) throws StudentNotFoundException {
		if(studentService.getStudent(studentId)==null) {
			System.out.println("Hiiiiiiiiiiiiiiiiiiiii");
			throw new StudentNotFoundException("Student Not Found");
		}else {
			System.out.println("Helllllllloooooooooooooooooooo");
			new ResponseEntity<StudentDto>(studentService.getStudent(studentId), HttpStatus.OK);
		}
		return null;
	}
	
	
	
	@GetMapping(value = "/mystudents/{studentId}")
	public ResponseEntity<StudentDto> getMyStudent(@PathVariable("studentId") int studentId) {
		return new ResponseEntity<StudentDto>(studentService.getStudent(studentId), HttpStatus.OK);
	}

	// http://localhost:8080/students
	// @RequestMapping(value = { "/students", "/listOfStudents"
	// },method=RequestMethod.GET)
	@GetMapping(value = { "/students", "/listOfStudents" })
	public ResponseEntity<List<StudentDto>> getAllStudents(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {

		return new ResponseEntity<List<StudentDto>>(studentService.getAllStudents(pageNumber, pageSize), HttpStatus.OK);
	}

	// http://localhost:8080/students POST
	// @RequestMapping(value = "/students",method=RequestMethod.POST)
	@PostMapping(value = "/students")
	public ResponseEntity<StudentDto> insertStudent(@RequestBody StudentDto studentDto) {

		return new ResponseEntity<StudentDto>(studentService.insertStudent(studentDto), HttpStatus.CREATED);

		// System.out.println("HI");
	}

	// @RequestMapping(value="/students/{studentId}",method=RequestMethod.PUT)
	@PutMapping(value = "/students/{studentId}")
	public ResponseEntity<String> updateStudent(@PathVariable("studentId") int studentId,
			@RequestBody StudentDto studentDto) {
		studentService.updateStudent(studentId, studentDto);
		return new ResponseEntity<String>("Student updated successfullyyy ", HttpStatus.OK);

	}

	// http://localhost:8080/students/1001 -> Delete
	// @RequestMapping(value="/students/{studentId}",method=RequestMethod.DELETE)
	@DeleteMapping("/students/{studentId}")
	public ResponseEntity<String> deleteStudent(@PathVariable("studentId") int studentId) {
		studentService.deleteStudent(studentId);
		return new ResponseEntity<String>("Student deleted successfullyyy ", HttpStatus.OK);
	}

	// http://localhost:8080/student-api/students/studentname/Ram
	@GetMapping("/students/studentname/{studentName}")
	public ResponseEntity<List<StudentDto>> getByStudentName(@PathVariable("studentName") String studentName) {
		return new ResponseEntity<List<StudentDto>>(studentService.getByStudentName(studentName), HttpStatus.OK);
	}

	@GetMapping("/students/{studentName}/{studentAge}")
	public ResponseEntity<List<StudentDto>> findByStudentNameAndStudentAge(
			@PathVariable("studentName") String studentName, @PathVariable("studentAge") int age) {
		return new ResponseEntity<List<StudentDto>>(studentService.findByStudentNameAndStudentAge(studentName, age),
				HttpStatus.OK);
	}
	
	

}
