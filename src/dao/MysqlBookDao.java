package dao;


import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import model.Book;
import util.ConnectionProvider;

/*
 *  DAO - Data Access Object. G³ówn¹ ide¹ jest stworzenie modelu danych w postaci klas odpowiadaj¹cych modelowi bazy danych,
 *   tzn. dla ka¿dej znacz¹cej tabeli w bazie danych powinna istnieæ klasa, która odpowiada jej w aplikacji. 
 *   Klasa taka powinna stanowiæ standardow¹ klasê Java Beans, czyli posiadaæ pola reprezentuj¹ce podstawowe typy danych, settery i gettery 
 *   oraz co najmniej konstruktor bezparametrowy
 */


public class MysqlBookDao implements BookDao {

	private final static String CREATE = "INSERT INTO book(isbn,title,description) VALUES (:isbn,:title,:description);";
	private final static String READ = "SELECT isbn,title,description FROM book WHERE isbn=:isbn;";
	private final static String UPDATE = "UPDATE book SET isbn=:isbn, title=:title, description=:description WHERE isbn=:isbn;";
	private final static String DELETE = "DELETE FROM book WHERE isbn=:isbn;";
	
	private NamedParameterJdbcTemplate template;
	
	public MysqlBookDao() {
		template = new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());
	}
	@Override
	public void create(Book book) {
		BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(book);
		template.update(CREATE, beanParamSource);
	}
	
	
	public Book read(String isbn) {
		Book resultBook = null;	// tworzymy obiekt klasy Book - na razie pusty
		
		SqlParameterSource namedParameter = new MapSqlParameterSource("isbn",isbn);
		List<Book> bookList = template.query(READ, namedParameter, BeanPropertyRowMapper.newInstance(Book.class));
		if(bookList.get(0) != null) {
			resultBook = bookList.get(0);
		}
		return resultBook;  //zwracamy nowy obiekt - odczytane dane z zapytania w nowym obiekcie (Book)
	}
	
	
	public void update(Book book) {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(book);
		template.update(UPDATE, parameterSource);
	}
	
	
	public void delete(Book book) {
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("isbn", book.getIsbn());
		template.update(DELETE, sqlParameterSource);
	}
	
}
