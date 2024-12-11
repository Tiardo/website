package example.controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Controller
public class StreamController {
    private final StreamService streamService;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping("/videoStream/{index}")
    public String viewStream(@PathVariable int index, Model model) {
        model.addAttribute("videoStreamUrl", "/stream/" + index);
        return "videoStream";
    }

    @GetMapping("/stream/{index}")
    public StreamingResponseBody stream(@PathVariable int index) {
        return streamService.streamVideo(index);
    }
}


