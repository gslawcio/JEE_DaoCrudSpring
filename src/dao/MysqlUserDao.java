package dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import model.User;
import util.ConnectionProvider;

public class MysqlUserDao implements UserDao {
	
	private final static String CREATE = "INSERT INTO user(pesel,firstname,lastname) VALUES (:pesel,:firstname,:lastname);";
	private final static String READ = "SELECT pesel,firstname,lastname FROM user WHERE pesel =:pesel;";
	private final static String UPDATE = "UPDATE user SET pesel=:pesel,firstname=:firstname,lastname=:lastname WHERE pesel=:pesel;";
	private final static String DELETE = "DELETE FROM user WHERE pesel=:pesel;";

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public MysqlUserDao() {
	jdbcTemplate = new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());
	}
	
	@Override
	public void create(User user) {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
		jdbcTemplate.update(CREATE, parameterSource);
		
	}

	@Override
	public User read(String pesel) {
		User resultUser = null;
	
		SqlParameterSource parameterSource = new MapSqlParameterSource("pesel", pesel);
		List<User> userList = jdbcTemplate.query(READ, parameterSource, BeanPropertyRowMapper.newInstance(User.class));
		if(userList.get(0) != null) {
			resultUser = userList.get(0);
		}
			return resultUser;
	}

	@Override
	public void update(User user) {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
		jdbcTemplate.update(UPDATE, parameterSource);
	}

	@Override
	public void delete(User user) {
		SqlParameterSource parameterSource = new  MapSqlParameterSource("pesel",user.getPesel());
		jdbcTemplate.update(DELETE, parameterSource);
		
		
	}

}
