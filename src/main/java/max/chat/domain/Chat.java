package max.chat.domain;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Chat {

	private Integer id;
    private String name;
    private String chat;
    private Date date = new Date(System.currentTimeMillis());
    
    public Chat() {
    }
    
	public Chat(String name, String chat) {
        this.name = name;
        this.chat = chat;
    }
	
	public Chat(Integer id, String name, String chat, Date date){
		this.id = id;
		this.name = name;
		this.chat = chat;
		this.date = date;
	}


    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getChat(){
    	return chat;
    }
    
    public void setChat(String chat){
    	this.chat = chat;
    }
    
    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
