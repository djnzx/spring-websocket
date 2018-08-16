package ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import ws.dto.RsDto;
import ws.dto.RqDto;

@Controller
public class AppController {
    private final String WS_PATH = "/front/endpoint1";

    @Autowired
    private SimpMessagingTemplate template;

    // receive only
    @MessageMapping("/hello2")
    // actually "/back/hello2", see WebSocketConfig.java and front.js
    public void listening_from_front(RqDto dto) {
        System.out.println(dto);
    }

    // receive and response
    @MessageMapping("/hello")
    // actually "/back/hello", see WebSocketConfig.java and front.js
    @SendTo(WS_PATH) // response path
    public RsDto get_and_respond(RqDto dto) {
        return new RsDto(String.format("Hello, %s", HtmlUtils.htmlEscape(dto.getName())));
    }

    // write directly to front
    @GetMapping("/a")
    @ResponseBody
    public String from_back_to_front_via_WebSocket() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            template.convertAndSend(WS_PATH,new RsDto("This message sent from code"));
            Thread.sleep(1000);
        }
        template.convertAndSend(WS_PATH,new RsDto("Done"));
        return "10 items sent";
    }

}
