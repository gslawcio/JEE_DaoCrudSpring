package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.DaoFactory;
import model.Book;


@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8"); // kodowanie znaków
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String option = request.getParameter("option");
		
		try {
			DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL_DAO);
			BookDao bookDao = daoFactory.getBookDao();
			Book book = null;			// tworzymy obiket klasy Book ¿eby mieæ jak przesy³aæ dane miêdzy klasami
			String operation = null;
			if("search".equals(option)) {
				book = bookDao.read(isbn);
				operation = "search";
			}else if("add".equals(option)) {
				book = new Book(isbn,title,description);
				bookDao.create(book);
				operation = "add";
			}else if("update".equals(option)) {
				book = new Book(isbn,title,description);
				bookDao.update(book);
				operation = "update";
			}else if("delete".equals(option)) {
				book = new Book(isbn,title,description);
				bookDao.delete(book);
				operation = "delete";
			}
			
			request.setAttribute("option", operation);
			request.setAttribute("book", book);
			request.getRequestDispatcher("result.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	
	}

}
