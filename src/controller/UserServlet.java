package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.UserDao;
import model.User;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pesel = request.getParameter("pesel");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String option = request.getParameter("option");
		
		try {
			DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL_DAO);
			UserDao userDao = daoFactory.getUserDao();
			User user = null;			// tworzymy obiket klasy Book ¿eby mieæ jak przesy³aæ dane miêdzy klasami
			String operation = null;
			if("search".equals(option)) {
				user = userDao.read(pesel);
				operation = "search";
			}else if("add".equals(option)) {
				user = new User(pesel,firstname,lastname);
				userDao.create(user);
				operation = "add";
			}else if("update".equals(option)) {
				user = new User(pesel,firstname,lastname);
				userDao.update(user);
				operation = "update";
			}else if("delete".equals(option)) {
				user = new User(pesel,firstname,lastname);
				userDao.delete(user);
				operation = "delete";
			}
			
			request.setAttribute("option", operation);
			request.setAttribute("user", user);
			request.getRequestDispatcher("userResult.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		
	}

}
