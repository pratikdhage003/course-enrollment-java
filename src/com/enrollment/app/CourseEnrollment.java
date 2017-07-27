package com.enrollment.app;

import java.util.HashMap;
import java.util.Map;

import com.enrollment.pojo.student.Student;

public class CourseEnrollment {

	public int countCommonStudents(Student[] courseA, Student[] courseB) {
		int n = courseA.length < courseB.length ? courseA.length : courseB.length;
		if (n == 0)
			return 0;
		CourseEnrollment.validateStudentInformation(courseA);
		CourseEnrollment.validateStudentInformation(courseB);

		Map<Student, Integer> map1 = new HashMap<Student, Integer>();
		Map<Student, String> outputMap = new HashMap<Student, String>();

		for (int i = 0; i < courseA.length; i++) {
			if (!map1.containsKey(courseA[i]))
				map1.put(courseA[i], 1);
			else
				map1.put(courseA[i], map1.get(courseA[i]) + 1);
		}

		for (int i = 0; i < n; i++) {
			if (!map1.containsKey(courseB[i])) {
				i++;
			} else {
				outputMap.put(courseB[i], "map2");
			}
		}
		return outputMap.size();
	}
	
	private static void validateStudentInformation(Student[] course) {
		Student student;
		for (int i = 0; i < course.length; i++) {
			student = course[i];
			if (student == null)
				i++;
			else {
				if (!(student.getAge() > 0 && isAlphabet(student.getFirstName()) && isAlphabet(student.getLastName())))
					throw new IllegalArgumentException("Invalid inputs, Wrong Age/Name for the Student : " + student);
			}
		}
	}

	private static boolean isAlphabet(String studentName) {
		int i;
		char[] nameArray = studentName.toCharArray();
		for (char ch : nameArray) {
			i = (int) ch;
			if (!((i >= 65 && i <= 90) || (i >= 97 && i <= 122))) {
				return false;
			}
		}
		return true;
	}
}