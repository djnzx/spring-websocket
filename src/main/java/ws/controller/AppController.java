package ws.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import ws.dto.RsDto;
import ws.dto.RqDto;

@Controller
public class AppController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public RsDto greeting(RqDto message) {
        return new RsDto(String.format("Hello, %s", HtmlUtils.htmlEscape(message.getName())));
    }

    @GetMapping("/a")
    @ResponseBody
    public String one() {
        return "one";
    }

}
