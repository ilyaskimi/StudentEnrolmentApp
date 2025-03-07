/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unikl.studentenrolmentapp;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Amirr
 */
//
//Bachelor of Information Technology (Hons.) in Software Engineering
//Bachelor of Information Technology (Hons) in Computer System Security
//Bachelor of Multimedia Technology (Hons.) in Computer Animation
public class Database {
    
    public static ArrayList<Student> tableStudent = new ArrayList<Student>();
    public static ArrayList<Course> tableCourse = new ArrayList<Course>();
    public static ArrayList<Semester> tableSemester = new ArrayList<Semester>();
    public static ArrayList<Enrolment> tableEnrolment = new ArrayList<Enrolment>();
    
    private static Database database = new Database();
    
    private Database() {
        Student test1 = new Student("000","John Smith","Bachelor of Information Technology (Hons.) in Software Engineering");
        tableStudent.add(test1);
        Student test2 = new Student("000","Jane Doe","Bachelor of Information Technology (Hons) in Computer System Security");
        tableStudent.add(test2);
        Student test3 = new Student("000","Alex Jones","Bachelor of Multimedia Technology (Hons.) in Computer Animation");
        tableStudent.add(test3);
        tableStudent.add(new Student("000000","asd","Test Student", "Test Program"));
        
        Course course1 = new Course("ISB42603", "ADVANCED PROGRAMMING", 3, "Learn Full-stack development using ASP.NET core MVC Framework");
        Course course2 = new Course("ISB42403", "WEB-APPLICATION DEVELOPMENT", 3, "Learn Full-stack development using ASP.NET Framework");
        Course course3 = new Course("ISB37603", "SOFTWARE DEVELOPMENT & INTEGRATION", 3, "This course aims to teach student on how to use a systematic approach to developing software that meets industry standard by introducind software patterns and architecture as well as emphasizing on sustainable software design");
        Course course4 = new Course("ISB36403", "INTERACTION DESIGN", 3, "Learn principles of UI/UX design as well as front-end development");
        tableCourse.add(course1);
        tableCourse.add(course2);
        tableCourse.add(course3);
        tableCourse.add(course4);
        tableCourse.add(new Course("IGB10403", "DISCRETE MATHEMATICS FOR IT",3,""));
        tableCourse.add(new Course("WEB10302", "FUNDAMENTAL ENGLISH",2,""));
        tableCourse.add(new Course("IDB20203", "OPERATING SYSTEMS",3,""));
        tableCourse.add(new Course("ISB10103", "PRINCIPLES OF COMPUTER PROGRAMMING",3,""));
        tableCourse.add(new Course("WEB20202", "PROFESSIONAL ENGLISH 1",2,""));
        tableCourse.add(new Course("MPU3123", "TAMADUN ISLAM & TAMADUN ASIA (TITAS)",3,""));
        tableCourse.add(new Course("WBB20103","TECHNOPRENEURSHIP",3,""));
        tableCourse.add(new Course("IKB20803","COMPUTER ORGANIZATION",3,""));
        tableCourse.add(new Course("MPU3113","HUBUNGAN ETNIK",3,""));
        tableCourse.add(new Course("IDB10103","	INTRODUCTION TO COMPUTING AND INFORMATION SYSTEMS",3,""));
        
        
    }
    
    public static Database getInstance(){
        return database;
    }
    
    public static String[] getCourseNames(){
        String[] temp = new String[tableCourse.size()];
        for (int i = 0; i < tableCourse.size(); i++){
            temp[i] = tableCourse.get(i).getTitle();
        }
        return temp;
    }
    
    public static void printTable(String tableName){
        if (tableName == "ENROLMENT"){
            for (int i = 0; i < tableEnrolment.size(); i++){
                System.out.println(tableEnrolment.get(i));
            }
        }
        
        System.out.println("-------------------------------");
    }
    
    public static Student getStudent(String studentID){
        Student currStudent = null;
        for(int i = 0; i < tableStudent.size(); i++){
            if (tableStudent.get(i).getId().equals(studentID)){
                currStudent = tableStudent.get(i);
            }
        }
        return currStudent;
    }
    
    public static DefaultTableModel getRequestedEnrolmentModel(String stdID){
        DefaultTableModel model = new DefaultTableModel();
        Vector<String> dataVector = new Vector<String>();
        Vector<String> columnNames = new Vector<String>();
        columnNames.addElement("Course Title");
        columnNames.addElement("Credit Hours");
        columnNames.addElement("Status");
        model.setColumnIdentifiers(columnNames);
        
        for (int i = 0; i < tableEnrolment.size(); i++){
            String currSrudentID = tableEnrolment.get(i).getStudentID();
            if(currSrudentID.equals(stdID)){
                dataVector.add(tableEnrolment.get(i).getCourseTitle());
                dataVector.add(String.valueOf(tableEnrolment.get(i).getCourseCreditHours()));
                dataVector.add(tableEnrolment.get(i).getStatus());
                model.addRow(dataVector);
            }
        }
        
        return model;
    }
    
    
    
    public static DefaultTableModel getAllFromTableStudent(){
        Vector<String> columnNames = new Vector<String>();
        columnNames.addElement("ID");
        columnNames.addElement("Name");
        columnNames.addElement("Program"); 
        columnNames.addElement("Accumulated Credit Hours");
        
        DefaultTableModel model = new DefaultTableModel(columnNames,0);
        
        
        
        for (int i = 0; i < tableStudent.size(); i++){
            
            String studentID = tableStudent.get(i).getId();
            String studentName = tableStudent.get(i).getName();
            String studentProgram = tableStudent.get(i).getProgram();
            int studentCreditHours = tableStudent.get(i).getAccumulatedCreditHours();
            Object[] data = {studentID, studentName, studentProgram, studentCreditHours};
            model.addRow(data);
        }
        
        return model;
    }
    
    public static int select_SumOfRequestedCreditHours_Where_StudentID(String stdID){
        int sum = 0;
        for (int i = 0; i < tableEnrolment.size(); i++){
            String currSrudentID = tableEnrolment.get(i).getStudentID();
            if(currSrudentID.equals(stdID)){
                
                sum += tableEnrolment.get(i).getCourseCreditHours();
                
            }
        }
        return sum;
    }
}
