package test;

import java.util.List;

import org.junit.Test;

import dao.BookDao;
import entity.Book;

public class TestBook {
	BookDao dao= new BookDao();
	
	@Test
	public void testInsert() {
		Book book = new Book();
		book.setId(1);
		book.setName("新编汉语辞典");
		book.setPrice(105.0);
		book.setPublishing("商务印书馆");
		book.setStock(25);
		dao.insertBook(book);
		
		book.setId(2);
		book.setName("跟任何人都聊得来");
		book.setPrice(36.8);
		book.setPublishing("中国致公出版社");
		book.setStock(30);
		dao.insertBook(book);
		
		book.setId(3);
		book.setName("思维导图");
		book.setPrice(75.0);
		book.setPublishing("北京联合出版公司");
		book.setStock(15);
		
		dao.insertBook(book);
	}
	@Test
	public void testFindAll() throws Exception {
		List<Book> books = dao.findAllBook();
		System.out.println(books);
	}
	
	
	@Test
	public void testDelete() throws Exception {
//		dao.deleteBook(2);

	}
	@Test
	public void updateBook() throws Exception {
		Book book = new Book(4,"思路决定出路","吉林文史出版社",38.0,56);
		dao.updateBook(book);
	}
	
	
	@Test
	public void testparse() {
		String str = "123";
		double d = Double.parseDouble(str);
		System.out.println(d);
	}
	@Test
	public void testFindByName() throws Exception {
		Book book = dao.findByName("思维导图");
		System.out.println(book);
	}
}
