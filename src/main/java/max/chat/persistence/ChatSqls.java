package max.chat.persistence;

public class ChatSqls {
	static final String COUNT_CHAT = 
			"SELECT COUNT(*) FROM CHAT";
	static final String SELECT_ALL = 
			"SELECT id, name, chat, date FROM chat";
	static final String SELECT_BY_ID = 
			"SELECT id, name, chat, date FROM chat WHERE id = :id";
	static final String SELECT_BY_COMPLETED = 
			"SELECT id, name, chat, date FROM chat WHERE name= :name";
	static final String DELETE_BY_ID =
			"DELETE FROM chat WHERE id= :id";
	static final String DELETE_ALL_COMPLETED =
			"DELETE FROM chat WHERE name = :name";
	static final String UPDATE = 
			"UPDATE chat SET\n"
			+ "name = :name,"
			+ "chat = :chat,"
			+ "date = :date\n"
			+ "WHERE id = :id";
	
	
}
