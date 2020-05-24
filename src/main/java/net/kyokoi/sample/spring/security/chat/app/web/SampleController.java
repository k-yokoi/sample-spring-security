package net.kyokoi.sample.spring.security.chat.app.web;

import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/portal")
    public String portal(Model model, HttpSession httpSession) {
        String sessionId = httpSession.getId();
        model.addAttribute("sessinId", sessionId);
        return "portal";
    }

    @GetMapping("/invalidateSession")
    public ResponseEntity<String> invalidateSession(HttpSession httpSession) {
        String sessionId = httpSession.getId();
        httpSession.invalidate();
        return ResponseEntity.ok("Session Invalidate: " + sessionId);
    }

    @GetMapping("/timeout")
    public String timeout() {
        return "timeout";
    }
}
