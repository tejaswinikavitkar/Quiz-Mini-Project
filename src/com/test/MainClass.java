package com.test;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		int num = -1;
		String s = "";
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("Which operation you want to perform");
			System.out.println(
					"Enter 0 to add questions & answers \nEnter 1 for Student registration \nEnter 2 to give Exam \nEnter 3 to see the score \nEnter 4 to see all student score details ");
			num = sc.nextInt();
			switch (num) {
			case 0:
				// add question and answers
				Logic.QuestionAns();
				break;
			case 1:
				// student registration
				System.out.println("Enter the student name:");
				String name = sc.next();
				System.out.println("Enter the student city:");
				String city = sc.next();
				System.out.println("Enter the student mobile:");
				String mobile = sc.next();

				Student student = new Student(name, city, mobile);
				StudentDao.getStudentDetails(student);
				break;
			case 2:

				// enter id and name to give quiz
				System.out.println("Enter id :");
				int id = sc.nextInt();
				System.out.println("Enter name:");
				String sname = sc.next();

				// to check id and name
				StudentDao.checkStudentDetails(id, sname);

				break;
			case 3:
				// logic to see score of students

				System.out.println("Enter the studentId");
				int newId = sc.nextInt();
				ScoreDao.getStudentScore(newId);

				break;
			case 4:
				// to get list of student details like id name,score of students
				ScoreDao.getAllStudentScoreList();
				break;

			default:
				System.out.println("Invalid");

			}
			System.out.println("Do you want to continue Yes/No");
			s = sc.next();

		} while (s.equalsIgnoreCase("Yes"));

	}

}
