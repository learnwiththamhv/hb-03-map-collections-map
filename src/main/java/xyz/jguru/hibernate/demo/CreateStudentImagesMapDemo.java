package xyz.jguru.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import xyz.jguru.hibernate.demo.entity.Student;

import java.util.Map;

public class CreateStudentImagesMapDemo
{
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                                                .configure("hibernate.cfg.xml")
                                                .addAnnotatedClass(Student.class)
                                                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

            //create object

            Student tempStudent = new Student("John","Doe","john@jguru.xyz");
            Map<String,String> theImages = tempStudent.getImages();

            theImages.put("photo1.jpg","Photo 1");
            theImages.put("photo2.jpg","Photo 2");
            theImages.put("photo3.jpg","Photo 3");



            //start a transaction
            session.beginTransaction();

            //save the object
            System.out.println("Saving the student and images ...");
            session.persist(tempStudent);

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!!");


        } finally {
            //cleanup code
            session.close();
            factory.close();

        }
    }
}
