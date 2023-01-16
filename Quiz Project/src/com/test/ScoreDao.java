package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScoreDao {
	static Connection co3 = ConnectionClass.createC();
// to add or update the student's score
	public static void insertScoreDetails(int id, int correctAnsCount) {
		int studId = getStudScoreDetails(id);
		if (id == studId) {
			try {
				PreparedStatement ps = co3.prepareStatement("update studentScore set score=? where studentId=?");
				ps.setInt(1, correctAnsCount);
				ps.setInt(2, id);

				int x = ps.executeUpdate();
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {
			try {
				PreparedStatement ps = co3.prepareStatement("insert into studentScore(studentId,score) values(?,?)");
				ps.setInt(1, id);
				ps.setInt(2, correctAnsCount);

				int x = ps.executeUpdate();

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	}
// to get the existing student's id
	private static int getStudScoreDetails(int id) {
		int sid = 0;
		try {
			PreparedStatement ps = co3.prepareStatement("select studentId from studentScore where studentId=?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sid = rs.getInt(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return sid;
	}
//to get the student score
	public static void getStudentScore(int newId) {
		try {
			PreparedStatement ps = co3.prepareStatement("select score from studentScore where studentId=?");
			ps.setInt(1, newId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Your score out of 10 is:" + rs.getInt(1));

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
// to get list of student details like students id, name and score
	public static void getAllStudentScoreList() {
		List<Student> al = new ArrayList<Student>();
		try {
			String sql = "select studentInfo.studentId,studentName ,studentScore.score from studentInfo inner join studentScore on studentInfo.studentId=studentScore.studentId order by score desc";
			Statement cs = co3.createStatement();
			ResultSet rs = cs.executeQuery(sql);
			while (rs.next()) {

				int id = rs.getInt(1);
				String name = rs.getString(2);
				int score = rs.getInt(3);
				Student student = new Student(id, name, score);
				al.add(student);

			}
			System.out.println("-----------------------------------------------");
			System.out.printf("%10s%15s%15s","student id","student name","student score");
			System.out.println();
			System.out.println("-----------------------------------------------");
			for (Student s : al) {
				
				System.out.printf("%7s%12s%12s",s.getId(),s.getName(),s.getScore());
				System.out.println();
				System.out.println("-----------------------------------------------");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
