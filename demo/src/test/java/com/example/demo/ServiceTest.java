package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    private final String TEST_FILE_PATH = "test_persons.xml";


    @BeforeEach
    public void setUp() throws JAXBException, IOException {
        Person person = new Person(1, "Krzysiek", "krzychu", "123456789", "krzychu@gmail.com", "123446752", Type.EXTERNAL);
        Service.create(person);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_FILE_PATH);
        if(file.exists()){
            file.delete();
        }
    }



    @Test
    public void testCreatePerson() throws JAXBException, IOException {
        Service.create(new Person(2, "testFirstName", "testLastName", "testMobile", "testEmail", "testPesel", Type.INTERNAL));

        assertTrue(Service.findById(2) != null);
    }

    @Test
    public void testFindPerson() throws JAXBException {
        Person person = Service.findById(1);
        assertNotNull(person);
        assertEquals("Krzysiek", person.getFirstName());
        assertEquals("krzychu", person.getLastName());
        assertEquals("123456789", person.getMobile());
        assertEquals("krzychu@gmail.com", person.getEmail());
        assertEquals("123446752", person.getPesel());
    }

    @Test
    public void testModifyPerson() throws JAXBException, IOException {
        Person updatedPerson = new Person(1, "updatedFirstName", "updatedLastName", "updatedMobile", "updatedEmail", "updatedPesel", Type.EXTERNAL);
        Service.modify(updatedPerson);

        Person person = Service.findById(1);

        assertNotNull(person);
        assertEquals("updatedFirstName", person.getFirstName());
        assertEquals("updatedLastName", person.getLastName());
        assertEquals("updatedMobile", person.getMobile());
        assertEquals("updatedEmail", person.getEmail());
        assertEquals("updatedPesel", person.getPesel());
    }

    @Test
    public void testRemovePerson() throws JAXBException, IOException {
        Service.remove(1);
        File file = new File(TEST_FILE_PATH);
        assertFalse(file.exists());
    }





}