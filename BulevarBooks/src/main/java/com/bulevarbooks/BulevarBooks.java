package com.bulevarbooks;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class BulevarBooks {
	static EntityManagerFactory factory;
	static EntityManager entityManager;

	public static void main(String[] args) {

		// create();
		// find();
		// remove();
		// update();

		begin();
		query();
		end();

	}

	private static void remove() {
		Integer primaryKey = 4;
		Book reference = entityManager.getReference(Book.class, primaryKey);
		entityManager.remove(reference);

	}

	private static void query() {
		String pql = "Select b from Book b Where b.price > 4000";
		Query query = entityManager.createQuery(pql);
		List<Book> listBooks = query.getResultList();
		for (Book abook : listBooks) {
			System.out.println(abook.getTitle() + ", " + abook.getAuthor() + ", " + abook.getPrice() + ", "
					+ abook.getPublisher() + ", " + abook.getState());
		}

	}

	private static void update() {
		Book existBook = new Book();
		existBook.setBookId(1);
		existBook.setTitle("Java The Complete Reference ");
		existBook.setAuthor("Herbert Schildt");
		existBook.setPrice(4250);
		existBook.setPublisher("Oracle Press");
		existBook.setState(13);

		entityManager.merge(existBook);

	}

	private static void find() {
		Integer primaryKey = 2;
		Book book = entityManager.find(Book.class, primaryKey);
		System.out.println(book.getTitle());
		System.out.println(book.getAuthor());
		System.out.println(book.getPrice());
		System.out.println(book.getPublisher());
		System.out.println(book.getState());

	}

	private static void create() {
		Book newBook = new Book();
		newBook.setTitle("Thinking in Java ");
		newBook.setAuthor("Bruce Eckel");
		newBook.setPrice(3500);
		newBook.setPublisher("Prentice Hall");
		newBook.setState(11);

		entityManager.persist(newBook);

	}

	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

	private static void begin() {
		factory = Persistence.createEntityManagerFactory("BookUnit");
		entityManager = factory.createEntityManager();

		entityManager.getTransaction().begin();
	}

}
