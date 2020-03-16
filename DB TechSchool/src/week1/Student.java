package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student {
	private String firstName;
	private String secondName;
	private String mail;
	private int creditsNum;
	private double grade;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getCreditsNum() {
		return creditsNum;
	}
	public void setCreditsNum(int creditsNum) {
		this.creditsNum = creditsNum;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public Student(String secondName, String firstName, String mail, int creditsNum, double grade) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.mail = mail;
		this.creditsNum = creditsNum;
		this.grade = grade;
	}
	
	 @Override
	    public String toString() { 
	        return String.format(this.secondName + " " + this.firstName + " " + this.mail + " " + this.creditsNum + " " + this.grade); 
	    } 
	
	
	
	public static void main(String []args) {
		ArrayList<Student> students_list = new ArrayList<>();
		try (Stream<Path> walk = Files.walk(Paths.get("D:\\Eclipse Workspace\\DB TechSchool\\facultate"))) {

			System.out.println(walk);
			
			List<String> result = walk.filter(Files::isRegularFile)
					.map(x -> x.toString()).collect(Collectors.toList());

			result.forEach(System.out::println);
			
			for(String fileName:result) {
				
				 try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
				  
					  //lines.forEach(System.out::println);
				  
					  String []all_lines = lines.toArray(String[]::new);
					 
					  
					  for(String line:all_lines) {
						  String []student = line.split(",");
						  students_list.add(new Student(student[0].toString(), student[1].toString(), student[2].toString(), Integer.parseInt(student[3]), Double.parseDouble(student[4])) );
						  //System.out.println(student[0]+" "+student[1] +" "+student[2] +" "+student[3] + " " + student[4] );
						  
					  }
					  
					  
				  
				  
				 } catch (IOException e) { e.printStackTrace(); }
				 	
			}
			Comparator<Student> compareBy = Comparator.comparing(Student::getGrade).reversed().thenComparing(Student::getSecondName).thenComparing(Student::getFirstName);
			  Collections.sort(students_list,compareBy);
			  //students_list.forEach(System.out::println);
			  
			  PrintWriter writer = new PrintWriter("studenti.txt", "UTF-8");
			  
			  for(Student student:students_list) {
				  writer.println(student);
				  System.out.println(student);
			  }
			  writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
