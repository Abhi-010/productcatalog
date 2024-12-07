package dev.abhi.productcatalog.inheritancerelations;

import dev.abhi.productcatalog.inheritancerelations.respository.StudentRepo;
import dev.abhi.productcatalog.inheritancerelations.joinedtable.Student;
import dev.abhi.productcatalog.inheritancerelations.joinedtable.User;
import dev.abhi.productcatalog.inheritancerelations.respository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private UserRepo testRep ;
    private StudentRepo studentRepo ;

    public DataLoader(UserRepo testRep, StudentRepo studentRepo){
        this.testRep = testRep ;
        this.studentRepo = studentRepo ;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setId(3L);
        user.setName("abhi");
        user.setEmail("abc@gmail.com");
        testRep.save(user) ;

        Student student = new Student() ;
        student.setId(5L);
        student.setName("Richard");
        student.setEmail("ric@gmail.com");
        student.setPsp(89);
        student.setAttendance(76);
        studentRepo.save(student) ;

    }
}
