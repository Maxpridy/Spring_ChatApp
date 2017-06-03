package max.chat.service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import max.chat.domain.Chat;
import max.chat.persistence.ChatDao;

@Service
public class ChatService {

	private ChatDao dao;
	
	public ChatService(ChatDao dao){
		this.dao = dao;
	}
	
	public Chat findById(Integer id){
		return dao.selectById(id);
	}
	
	public Collection<Chat> findAll(){
		return dao.selectAll();
	}
	
	public Collection<Chat> findByCompleted(Integer completed){
		return dao.selectByCompleted(completed);
	}
	
	public Chat create(Chat chat){
		Integer id = dao.insert(chat);
		chat.setId(id);
		return chat;
	}
	
	public boolean update(Chat todo){
		int affected = dao.update(todo);
		return affected == 1;
	}
	
	public boolean delete(Integer id){
		int affected = dao.deleteById(id);
		return affected == 1;
	}
	
	public boolean clearCompleted(){
		int affected = dao.clearCompleted();
		return affected == 1;
	}
}
