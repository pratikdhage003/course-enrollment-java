package com.enrollment.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.enrollment.app.CourseEnrollment;
import com.enrollment.pojo.student.Student;

public class CourseEnrollmentTest {

	CourseEnrollment courseEnrollment;

	@Before
	public void initializeStudentEnrollment() {
		courseEnrollment = new CourseEnrollment();
	}

	@After
	public void cleanUpStudentEnrollment() {
		courseEnrollment = null;
	}
	
	@Test
	public void testCountCommonStudentsWithEqualSizedCoursesAndOneCommonStudent() {
		Student student_A = new Student("George", "Washington", 23);
		Student student_B = new Student("John", "Adams", 25);
		Student student_C = new Student("Thomas", "Jefferson", 27);

		Student[] course1 = { student_A, student_B };
		Student[] course2 = { student_A, student_C };

		int commonStudentsCount = courseEnrollment.countCommonStudents(course1, course2);
		assertEquals(1, commonStudentsCount);
	}

	@Test
	public void testCountCommonStudentsWithDifferentSizedCoursesAndTwoCommonStudents() {
		Student student_A = new Student("George", "Washington", 23);
		Student student_B = new Student("John", "Adams", 25);
		Student student_C = new Student("Thomas", "Jefferson", 27);

		Student[] course1 = { student_A, student_B };
		Student[] course3 = { student_A, student_B, student_C };

		int commonStudentsCount = courseEnrollment.countCommonStudents(course1, course3);
		assertEquals(2, commonStudentsCount);
	}

	@Test
	public void testCountCommonStudentsWithDifferentSizedCoursesAndNoCommonStudent() {
		Student student_A = new Student("George", "Washington", 23);
		Student student_B = new Student("John", "Adams", 25);
		Student student_C = new Student("Thomas", "Jefferson", 27);

		Student[] course1 = { student_A, student_B };
		Student[] course4 = { student_C };

		int commonStudentsCount = courseEnrollment.countCommonStudents(course1, course4);
		assertEquals(0, commonStudentsCount);
	}

	@Test
	public void testCountCommonStudentsWithOneCourseHavingNoStudents() {
		Student student_A = new Student("George", "Washington", 23);
		Student student_C = new Student("Thomas", "Jefferson", 27);

		Student[] course1 = {};
		Student[] course2 = { student_A, student_C };

		int commonStudentsCount = courseEnrollment.countCommonStudents(course1, course2);
		assertEquals(0, commonStudentsCount);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCountCommonStudentsWithCourseContainingStudentWithInvalidAge() {
		Student student_A = new Student("George", "Washington", -23);
		Student student_B = new Student("John", "Adams", 25);
		Student student_C = new Student("Thomas", "Jefferson", 27);

		Student[] course1 = { student_A, student_B };
		Student[] course2 = { student_A, student_C };

		int commonStudentsCount = courseEnrollment.countCommonStudents(course1, course2);
		assertEquals(IllegalArgumentException.class, commonStudentsCount);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCountCommonStudentsWithCourseContainingStudentWithInvalidName() {
		Student student_A = new Student("George", "Washington1234@", 23);
		Student student_B = new Student("John", "Adams", 25);
		Student student_C = new Student("Thomas", "Jefferson", 27);

		Student[] course1 = { student_A, student_B };
		Student[] course2 = { student_A, student_C };

		int commonStudentsCount = courseEnrollment.countCommonStudents(course1, course2);
		assertEquals(IllegalArgumentException.class, commonStudentsCount);
	}

	@Test
	public void testCountCommonStudentsWithDuplicateStudentInAnotherCourse() {
		Student student_A = new Student("George", "Washington", 23);
		Student student_B = new Student("John", "Adams", 25);

		Student student_D = new Student("George", "Washington", 23);

		Student[] course1 = { student_A, student_B };
		Student[] course5 = { student_D };

		int commonStudentsCount = courseEnrollment.countCommonStudents(course1, course5);
		assertEquals(1, commonStudentsCount);
	}

	@Test
	public void testCountCommonStudentsWithDuplicateStudentInSameCourse() {
		Student student_A = new Student("George", "Washington", 23);
		Student student_B = new Student("John", "Adams", 25);

		Student student_D = new Student("George", "Washington", 23);

		Student[] course1 = { student_A, student_D };
		Student[] course5 = { student_B };

		int commonStudentsCount = courseEnrollment.countCommonStudents(course1, course5);
		assertEquals(0, commonStudentsCount);
	}
	
	@Test
	public void testCountCommonStudentsWithDuplicateStudentsInBothCourses() {
		Student student_A = new Student("George", "Washington", 23);
	
		Student student_D = new Student("George", "Washington", 23);

		Student[] course1 = { student_A, student_D };
		Student[] course5 = { student_D };

		int commonStudentsCount = courseEnrollment.countCommonStudents(course1, course5);
		assertEquals(1, commonStudentsCount);
	}
	
	@Test
	public void testCountCommonStudentsWithOneCommonCourseAndOneNullStudentInputInACourse() {
		Student student_A = new Student("George", "Washington", 23);
		Student student_C = new Student("Thomas", "Jefferson", 27);

		Student[] course1 = { student_A, null };
		Student[] course2 = { student_A, student_C };

		int commonStudentsCount = courseEnrollment.countCommonStudents(course1, course2);
		assertEquals(1, commonStudentsCount);
	}

	@Test
	public void testCountCommonStudentsWithNoCommonCoursesAndTwoNullStudentInputsInACourse() {
		Student student_A = new Student("George", "Washington", 23);
		Student student_C = new Student("Thomas", "Jefferson", 27);

		Student[] course1 = { null, null };
		Student[] course2 = { student_A, student_C };

		int commonStudentsCount = courseEnrollment.countCommonStudents(course1, course2);
		assertEquals(0, commonStudentsCount);
	}

}
