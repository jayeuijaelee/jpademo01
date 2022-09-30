package com.example.jpademo01.repository;


import com.example.jpademo01.Jpademo01ApplicationTests;
import com.example.jpademo01.model.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@WebAppConfiguration
public class PersonRepositoryTest extends Jpademo01ApplicationTests {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void create(){
        Person p = new Person();
        p.setName("Jimmy");
        p.setEmail("jimmy@email.com");
        p.setCreated_at(LocalDateTime.now());
        p.setCreated_by("Jimmy");

        System.out.println(p.toString());
        personRepository.save(p);
    }


    //디버깅
    @Test
    public void read(){
        Optional<Person> person = personRepository.findById(100);

        Assertions.assertFalse(person.isPresent());

        //Person p
        person.ifPresent( p -> {
            System.out.println(p);
        } );

        System.out.println("종료");
    }

    @Test
    @Transactional
    public void update(){
        Optional<Person> person = personRepository.findById(2);
        person.ifPresent( p -> {
            p.setName("aaa");
            personRepository.save(p);
        } );
    }

    @Test
    @Transactional
    public void delete(){
        Optional<Person> person = personRepository.findById(2);
        person.ifPresent( p -> {
            personRepository.delete(p);
        } );
    }
}
