package max.chat.api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import max.chat.domain.Chatstring;
import max.chat.domain.Message;

@Controller
public class ChatController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Chatstring greeting(Message message) throws Exception {
        Thread.sleep(100); // simulated delay
        //return new Greeting("Hello, " + message.getName() + "!");
        System.out.println(message);
        return new Chatstring(message.getName() + " : " + message.getMsg());
        
    }

}
