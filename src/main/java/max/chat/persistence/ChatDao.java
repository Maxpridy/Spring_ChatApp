package max.chat.persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import max.chat.domain.Chat;

@Repository
public class ChatDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	public ChatDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("chat")
				.usingGeneratedKeyColumns("id");
	}
	
	public int countChats(){
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.queryForObject(ChatSqls.COUNT_CHAT, params, Integer.class);
	}
	
	public Chat selectById(Integer id){
		RowMapper<Chat> rowMapper = BeanPropertyRowMapper.newInstance(Chat.class);
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return jdbc.queryForObject(ChatSqls.SELECT_BY_ID, params, rowMapper);
	}
	
	public Integer insert(Chat chat){
		SqlParameterSource params = new BeanPropertySqlParameterSource(chat);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	private RowMapper<Chat> rowMapper = BeanPropertyRowMapper.newInstance(Chat.class);
	public List<Chat> selectAll(){
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.query(ChatSqls.SELECT_ALL,  params, rowMapper);
	}
	
	public List<Chat> selectByCompleted(Integer completed){
		RowMapper<Chat> rowMapper = BeanPropertyRowMapper.newInstance(Chat.class);
		Map<String, Object> params = new HashMap<>();
		params.put("completed", completed);
		return jdbc.query(ChatSqls.SELECT_BY_COMPLETED, params, rowMapper);
	}	
	
	public int deleteById(Integer id){
		Map<String, ?> params = Collections.singletonMap("id",  id);
		return jdbc.update(ChatSqls.DELETE_BY_ID,  params);
	}
	
	public int update(Chat chat){
		SqlParameterSource params = new BeanPropertySqlParameterSource(chat);
		return jdbc.update(ChatSqls.UPDATE, params);
	}
	
	public int clearCompleted(){
		Map<String, ?> params = Collections.emptyMap();
		return jdbc.update(ChatSqls.DELETE_ALL_COMPLETED, params);
	}
}
