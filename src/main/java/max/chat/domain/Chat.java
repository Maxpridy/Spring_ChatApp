package max.chat.domain;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Chat {

	private Integer id;
    private String chatName;
    private String chatContent;
    private Date date = new Date(System.currentTimeMillis());
    

	public Chat() {
    }
    
	public Chat(String chatName, String chatContent) {
        this.chatName = chatName;
        this.chatContent = chatContent;
    }
	
	public Chat(Integer id, String chatName, String chatContent, Date date){
		this.id = id;
		this.chatName = chatName;
		this.chatContent = chatContent;
		this.date = date;
	}


	
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChatName() {
		return chatName;
	}

	public void setChatName(String chatName) {
		this.chatName = chatName;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
