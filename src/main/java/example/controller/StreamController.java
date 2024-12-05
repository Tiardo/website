package example.controller;



import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Controller
public class StreamController {
    private final StreamService streamService;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping("/videoStream")
    public String viewStream(Model model) {
        String rtspUrl = "http://61.211.241.239/nphMotionJpeg?Resolution=320x240&Quality=Standard";
        model.addAttribute("videoStreamUrl", rtspUrl);
        return "videoStream";
    }

    @GetMapping("/stream")
    public StreamingResponseBody stream() {
        String rtspUrl = "http://61.211.241.239/nphMotionJpeg?Resolution=320x240&Quality=Standard";
        return streamService.streamVideo(rtspUrl);
    }
}


