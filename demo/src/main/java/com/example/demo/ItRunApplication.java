package com.example.demo;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class ItRunApplication extends Service{

	public static void main(String[] args) {
		try{

			create(new Person(1, "Krzysiek", "krzychu", "123456789", "krzychu@gmail.com", "123446752", Type.EXTERNAL));

			create(new Person(2, "Maciek", "maciuś", "987654321", "maciek@gmail.com", "563451234", Type.INTERNAL));
			create(new Person(3, "Leszek", "Lechu", "456123789", "lechu@gmail.com" ,"08723452345", Type.EXTERNAL));
			create(new Person(4, "Dawid", "dawidek", "567345890", "dawid@gmail.com", "12095708172", Type.INTERNAL));
			create(new Person(5, "Zdzislaw", "zdziszek", "612344456", "zdzichu@gmail.com" ,"032498509348", Type.EXTERNAL));

			modify(new Person(2, "Dawid", "dawidek", "567345890", "dawid@gmail.com", "12095708172", Type.INTERNAL));
			remove(3);
			System.out.println(findById(1).toString());
			System.out.println(findById(2).toString());
			System.out.println(findById(4).toString());
			System.out.println(findById(5).toString());
			System.out.println(findPerson(Type.EXTERNAL, "Zdzislaw", "zdziszek", "612344456").toString());

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
