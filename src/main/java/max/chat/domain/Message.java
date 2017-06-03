package max.chat.domain;


import java.util.ArrayList;
import java.util.List;

public class Message {

    private String name;
    private String msg;

    public Message() {
    }

//    public HelloMessage(String name) {
//        this.name = name;
//    }
    
    public Message(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }
    
//    public List<String> getMsg(){
//    	List<String> hm = new ArrayList<String>();
//    	hm.add(name);
//    	hm.add(msg);
//    	return hm;
//    }
    
    public String getMsg(){
    	return msg;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setMsg(String msg){
    	this.msg = msg;
    }
}
