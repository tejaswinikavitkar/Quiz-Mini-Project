package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Logic {
	static Connection co1 = ConnectionClass.createC();

	public static void QuestionAns() {

		int queCount = 0;
		int ansCount = 0;
		queCount = checkQueData();
		ansCount = checkAnsData();
		if (queCount == 0) {
			addQuestions();
		}
		if (ansCount == 0) {
			addAnswers();
		}

	}

	private static int checkAnsData() {
		int c1 = 0;
		try {

			PreparedStatement ps = co1.prepareStatement("select count(queId) from questions");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c1 = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return c1;
	}

	private static int checkQueData() {
		int c2 = 0;
		try {

			PreparedStatement ps = co1.prepareStatement("select count(ansId) from answers");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c2 = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return c2;
	}

	// to add questions into database
	public static void addQuestions() {
		try {
			String que = "insert into Questions(question) values('Who invented Java Programming?'),('Which one of the following is not a Java feature?'),('Which of these cannot be used for a variable name in Java?'),('What is the extension of java code files?'),('Which of the following is not an OOPS concept in Java?'),('What is the extension of compiled java classes?'),('Which exception is thrown when java is out of memory?'),('Which of these are selection statements in Java?'),('Which of these keywords is used to define interfaces in Java?'),('Which of the following is a superclass of every class in Java?')";
			Statement cs = co1.createStatement();
			cs.execute(que);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

// to add answers into database
	public static void addAnswers() {
		try {
			String ans1 = "insert into Answers(answer,queId,correctAns,options) values('A.Guido van Rossum',1,'N','A'),('B.James Gosling',1,'Y','B'),('C.Dennis Ritchie',1,'N','C'),('D.Bjarne Stroustrup',1,'N','D')";
			String ans2 = "insert into Answers(answer,queId,correctAns,options) values('A.Object-oriented',2,'N','A'),('B.Use of pointers',2,'Y','B'),('C.Portable',2,'N','C'),('D.Dynamic and Extensible',2,'N','D')";
			String ans3 = "insert into Answers(answer,queId,correctAns,options) values('A.identifier & keyword',3,'N','A'),('B.identifier',3,'N','B'),('C.keyword',3,'Y','C'),('D.none of the mentioned',3,'N','D')";
			String ans4 = "insert into Answers(answer,queId,correctAns,options) values('A..js',4,'N','A'),('B..txt',4,'N','B'),('C..class',4,'N','C'),('D..java',4,'Y','D')";
			String ans5 = "insert into Answers(answer,queId,correctAns,options) values('A.Polymorphism',5,'N','A'),('B.Inheritance',5,'N','B'),('C.Compilation',5,'Y','C'),('D.Encapsulation',5,'N','D')";
			String ans6 = "insert into Answers(answer,queId,correctAns,options) values('A..txt',6,'N','A'),('B..js',6,'N','B'),('C..class',6,'Y','C'),('D..java',6,'N','D')";
			String ans7 = "insert into Answers(answer,queId,correctAns,options) values('A.MemoryError',7,'N','A'),('B.OutOfMemoryError',7,'Y','B'),('C.MemoryOutOfBoundsException',7,'N','C'),('D.MemoryFullException',7,'N','D')";
			String ans8 = "insert into Answers(answer,queId,correctAns,options) values('A.break',8,'N','A'),('B.continue',8,'N','B'),('C.for()',8,'N','C'),('D.if()',8,'Y','D')";
			String ans9 = "insert into Answers(answer,queId,correctAns,options) values('A.intf',9,'N','A'),('B.Intf',9,'N','B'),('C.interface',9,'Y','C'),('D.Interface',9,'N','D')";
			String ans10 = "insert into Answers(answer,queId,correctAns,options) values('A.ArrayList',10,'N','A'),('B.Abstract class',10,'N','B'),('C.Object class',10,'Y','C'),('D.String',10,'N','D')";

			Statement cs = co1.createStatement();
			cs.addBatch(ans1);
			cs.addBatch(ans2);
			cs.addBatch(ans3);
			cs.addBatch(ans4);
			cs.addBatch(ans5);
			cs.addBatch(ans6);
			cs.addBatch(ans7);
			cs.addBatch(ans8);
			cs.addBatch(ans9);
			cs.addBatch(ans10);
			cs.executeBatch();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

//to get questions and answers for display on the console
	public static void getQuizDetails(int id) {
		int correctAnsCount = 0;
		int wrongAnsCount = 0;
		
		Scanner sc = new Scanner(System.in);
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		

		try {
			String sql = "select Questions.queId, Questions.question,Answers.answer from Questions inner join Answers on Questions.queId= Answers.queId order by Questions.queId";
			Statement cs = co1.createStatement();
			ResultSet rs = cs.executeQuery(sql);

			while (rs.next()) {
				String que = rs.getString(2);
				String ans = rs.getString(3);

				if (map.containsKey(que)) {
					ArrayList<String> al = new ArrayList<>();
					al = map.get(que);
					al.add(ans);
					map.put(que, al);

				} else {
					ArrayList<String> arr = new ArrayList<>();
					arr.add(ans);
					map.put(que, arr);
					arr = null;
				}


			}
			Iterator<Map.Entry<String, ArrayList<String>>> itr = map.entrySet().iterator();
			while (itr.hasNext()) {			
				Map.Entry<String, ArrayList<String>> m = itr.next();
				System.out.println(m.getKey());
				ArrayList<String> aList = m.getValue();
			for (String a : aList) {
					System.out.println(a);
				}

				System.out.println("Enter Your Answer");
				Character c = sc.next().charAt(0);
				Character c1 = checkAnswer(m.getKey());

				int x = c.compareTo(c1);
				if (x == 0) {
					System.out.println("Correct answer");
					correctAnsCount++;

				} else {
					
					System.out.println("Incorrect answer");
					wrongAnsCount++;
				}

				// System.out.println(c1);

			}
			if (correctAnsCount >= 8) {
				System.out.println("Your Score is: " + correctAnsCount + " & Grade is A");
			} else if (correctAnsCount >= 6 && correctAnsCount < 8) {
				System.out.println("Your Score is: " + correctAnsCount + " & Grade is B");
			} else if (correctAnsCount < 6 && correctAnsCount == 5) {
				System.out.println("Your Score is: " + correctAnsCount + " & Grade is C");
			} else if (correctAnsCount < 5) {
				System.out.println("Your Score is: " + correctAnsCount + " & Grade is D");
			}

			ScoreDao.insertScoreDetails(id, correctAnsCount);
			cs.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//to check correct answers
	public static Character checkAnswer(String question) {
		Character opt = null;
		try {

			PreparedStatement ps = co1.prepareStatement(
					"select Answers.options from Questions inner join Answers on Questions.queId= Answers.queId where correctAns=? and question=?");
			ps.setString(1, "Y");
			ps.setString(2, question);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				opt = rs.getString(1).charAt(0);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return opt;
	}

}
