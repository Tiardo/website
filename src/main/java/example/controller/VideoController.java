package example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VideoController {

    @GetMapping("/video")
    @ResponseBody
    public String serveVideo() {
        return "<video width='800' height='600' controls> <source src='/videos/video.mp4' type='video/mp4'> error </video>";
    }
}