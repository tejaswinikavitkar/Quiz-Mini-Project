package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDao {
	static Connection co2 = ConnectionClass.createC();
	
	//to store student data into the database which is entered by the student
	public static void getStudentDetails(Student student) {
				String q= "insert into studentInfo(studentName,studentCity,studentMob) values(?,?,?)";
			
		try {
			PreparedStatement ps = co2.prepareStatement(q);
			ps.setString(1, student.getName());
			ps.setString(2, student.getCity());
			ps.setString(3, student.getMobNum());
			int i=ps.executeUpdate();
			if(i==1) {
			System.out.println("Registration is Done...");
			}else {
				System.out.println("Registration is not Done ");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	//to check student's authority to give exam
	public static void checkStudentDetails(int id, String sname) {
		
		 try {
			PreparedStatement ps = co2.prepareStatement("select studentId,studentName from studentInfo where studentId=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int sid=rs.getInt(1);
				String stuName=rs.getString(2);
				if(id==sid && sname.equalsIgnoreCase(stuName)) {
					System.out.println("You authorized to take  quiz");
					Logic.getQuizDetails(sid);
				}else {
					System.out.println("You are not authorized give the quiz kindly do the registration");
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
}
