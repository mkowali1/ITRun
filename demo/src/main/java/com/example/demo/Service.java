package com.example.demo;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Service {
    static String projectPath = "xmlFiles/";

    //Szuka ścieżki do persony o podanym Id
    public static String findFilePath(int personId){
        String finalPath = "";

        String directoryPathExternal = projectPath + Type.EXTERNAL + "s";
        String directoryPathInternal = projectPath + Type.INTERNAL + "s";
        String filePathExternal = directoryPathExternal + File.separator + personId + ".xml";
        String filePathInternal = directoryPathInternal + File.separator + personId + ".xml";

        File fileExternal = new File(filePathExternal);
        File fileInternal = new File(filePathInternal);
        if(fileExternal.exists()){
            finalPath = filePathExternal;
        } else if(fileInternal.exists()){
            finalPath = filePathInternal;
        } else {
            System.out.println("Plik nie istnieje");
        }
         return finalPath;
    }


    public static void create(Person person) throws IOException, JAXBException {
        String directoryPath = projectPath + person.getType() + "s";
        File directory = new File(directoryPath);
        if(!directory.exists()){
            directory.mkdirs();
        }
        String filePath = directoryPath + File.separator + person.getPersonId() + ".xml";

        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(person, new File(filePath));
    }
    public static Person findById(int id) throws JAXBException {
        String finalPath = findFilePath(id);
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Person person = (Person) unmarshaller.unmarshal(new File(finalPath));
        return person;
    }


    public static void remove(int personId) throws JAXBException, IOException {
        String finalPath = findFilePath(personId);
        File file = new File(finalPath);
        if(file.delete()){
            System.out.println("Osoba o id: " + personId + " zostala usunieta");
        }
        else {
            System.out.println("Nie udało się usunąć danych o tej osobie");
        }
    }

    public static void modify(Person updatedPerson) throws JAXBException, IOException {
        remove(updatedPerson.getPersonId());
        create(updatedPerson);
    }
    public static Person findPerson(Type type,  String firstName, String lastName, String mobile) throws JAXBException {
        String directoryPath = projectPath + type + "s";

        File folder = new File(directoryPath);

        JAXBContext context = JAXBContext.newInstance(Person.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        if(folder.isDirectory()) {
            File[] files = folder.listFiles();
            if(files != null) {
                for(File file : files){
                    Person person = (Person) unmarshaller.unmarshal(file);
                    if(person.getFirstName().equals(firstName) &&
                            person.getLastName().equals(lastName) &&
                            person.getMobile().equals(mobile)){
                        return person;
                    }
                }

            } else {
                System.out.println("Brak plików w folerze");
            }
        } else{
            System.out.println("Podana ścieżka nie prowadzi do folderu");
        }
        return null;
    }
}
