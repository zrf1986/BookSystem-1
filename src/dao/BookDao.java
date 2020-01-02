package dao;
/**
 * 图书相关增删改查方法
 * @author Administrator
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entity.Book;

public class BookDao {
	RandomAccessFile raf;
	
	
	
	//添加图书
	public void insertBook(Book book) {
		try {
			raf = new RandomAccessFile("Book.txt", "rw");
			
			raf.seek(raf.length());
			
			raf.write((book.getId()+",").getBytes("UTF-8"));
			raf.write((book.getName()+",").getBytes("UTF-8"));
			raf.write((book.getPublishing()+",").getBytes("UTF-8"));
			raf.write((book.getPrice()+",").getBytes("UTF-8"));
			raf.write((book.getStock()+",").getBytes("UTF-8"));
			raf.write("\r\n".getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//查询全部图书
	public List<Book> findAllBook() throws Exception{
		raf = new RandomAccessFile("Book.txt", "r");
		List<Book> books = new ArrayList<Book>();
		String book1="";
		while((book1= raf.readLine())!=null) {
			Book book = new Book();
			String[] message = new String(book1.getBytes("ISO-8859-1"),"utf-8").split(",");
			
			book.setId(Integer.parseInt(message[0]));
			book.setName(message[1]);
			book.setPublishing(message[2]);
			book.setPrice(Double.parseDouble(message[3]));
			book.setStock(Integer.parseInt(message[4]));
			
			books.add(book);
			
			raf.seek(raf.getFilePointer());
			
		}
		raf.close();
		return books;
	}
	//根据书名查询图书
	public Book findByName(String name) throws Exception {
		raf = new RandomAccessFile("Book.txt", "r");
		while(raf.getFilePointer()!=raf.length()) {
			Book book = new Book();
			String[] message = new String(raf.readLine().getBytes("ISO-8859-1"),"utf-8").split(",");
			if(message[1].equals(name)) {
				book.setId(Integer.parseInt(message[0]));
				book.setName(message[1]);
				book.setPublishing(message[2]);
				book.setPrice(Double.parseDouble(message[3]));
				book.setStock(Integer.parseInt(message[4]));
				raf.close();
				return book;
			}
		}
		raf.close();
		return null;
	}
	//删除图书信息
	public void deleteBook(String name) throws Exception {
		List<Book> books = findAllBook();
		raf = new RandomAccessFile("Book.txt", "rw");
		Iterator<Book> it = books.iterator();
		while(it.hasNext()) {
			Book book = it.next();
			if(book.getName().equals(name)) {
				it.remove();
			}
		}
		System.out.println(books);
		raf.close();
		File file = new File("Book.txt");
		if(file.exists()) {
			file.delete();
			System.out.println("文件已删除!");
		}else {
			System.out.println("文件不存在!");
		}
		
		raf = new RandomAccessFile("Book.txt", "rw");
		raf.seek(0);
		for(Book book:books) {
			raf.write((book.getId()+",").getBytes("UTF-8"));
			raf.write((book.getName()+",").getBytes("UTF-8"));
			raf.write((book.getPublishing()+",").getBytes("UTF-8"));
			raf.write((book.getPrice()+",").getBytes("UTF-8"));
			raf.write((book.getStock()+",").getBytes("UTF-8"));
			raf.write("\r\n".getBytes());
		}
		raf.close();
	}
	//修改图书信息
	public void updateBook(Book book) throws Exception {
		List<Book> books = findAllBook();
		raf = new RandomAccessFile("Book.txt", "rw");
		
		for(int i=0;i<books.size();i++) {
			if(books.get(i).getName().equals(book.getName())) {
				books.set(i, book);
			}
		}
		raf.close();
		File file = new File("Book.txt");
		if(file.exists()) {
			file.delete();
			System.out.println("文件已删除!");
		}else {
			System.out.println("文件不存在!");
		}
		
		raf = new RandomAccessFile("Book.txt", "rw");
		raf.seek(0);
		for(Book book1:books) {
			raf.write((book1.getId()+",").getBytes("UTF-8"));
			raf.write((book1.getName()+",").getBytes("UTF-8"));
			raf.write((book1.getPublishing()+",").getBytes("UTF-8"));
			raf.write((book1.getPrice()+",").getBytes("UTF-8"));
			raf.write((book1.getStock()+",").getBytes("UTF-8"));
			raf.write("\r\n".getBytes());
		}
		raf.close();
		
	}
}
