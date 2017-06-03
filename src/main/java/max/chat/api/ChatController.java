package max.chat.api;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import max.chat.domain.ChatString;
import max.chat.service.ChatService;
import max.chat.domain.Chat;

@RestController
@RequestMapping("/api/chats")
public class ChatController {


	private final ChatService service;
	private final Logger log = LoggerFactory.getLogger(ChatController.class); 
	
	
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ChatString greeting(Chat chat) throws Exception {
        return new ChatString(chat.getName() + " : " + chat.getChat());
    }
    
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Chat create(@RequestBody Chat chat){
		Chat newChat = service.create(chat);
		log.info("book created : {}", newChat);
		return chat;
	}

	@Autowired
	public ChatController(ChatService service){
		this.service = service;
	}
	
	@GetMapping("/hello")
	String hello(){
		return "¾È³ç! á¦Í£!";
	}
	
	@GetMapping
	Collection<Chat> readList(){
		return service.findAll();
	}
	
	@GetMapping("/active")
	Collection<Chat> getActiveChats(){
		return service.findByCompleted(0);
	}
	
	@GetMapping("/completed")
	Collection<Chat> getCompletedChats(){
		return service.findByCompleted(1);
	}
	
	@GetMapping("/{id}")
	Chat read(@PathVariable Integer id){
		return service.findById(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void update(@PathVariable Integer id, @RequestBody Chat chat){
		chat.setId(id);
		service.update(chat);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Integer id){
		service.delete(id);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void clearCompleted() {
		service.clearCompleted();
	}

}
