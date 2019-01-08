package dao;

import model.Book;

public interface BookDao {

	public void create(Book book);
	public Book read(String isbn);
	public void update(Book book);
	public void delete(Book book);
}
