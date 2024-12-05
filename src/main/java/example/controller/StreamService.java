package example.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class StreamService {

    private final String ffmpegPath = "C:/ffmpeg/bin/ffmpeg.exe";


    public StreamingResponseBody streamVideo(String rtspUrl) {
        List<String> command = new ArrayList<>();
        command.add(ffmpegPath);
        command.add("-i");
        command.add(rtspUrl); // Входной RTSP поток
        command.add("-f");
        command.add("mp4"); // Формат выхода (можно изменить, если нужно)
        command.add("-b:v");
        command.add("1000k"); // Битрейт видео
        command.add("-preset");
        command.add("fast"); // Предустановка кодирования для ускорения процесса
        command.add("-"); // Вывод в стандартный вывод

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        try {
            Process process = pb.start();
            InputStream inputStream = process.getInputStream();

            return outputStream -> {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            };
        } catch (IOException e) {
            e.printStackTrace();
            return outputStream -> {};
        }
    }


}


























//
//
//
//
//
//
//import example.service.VideoStreamService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class VideoController {
//    private final VideoStreamService videoStreamService;
//
//    public VideoController(VideoStreamService videoStreamService) {
//        this.videoStreamService = videoStreamService;
//    }
//
//    @GetMapping("/videoStream")
//    public String getVideoStream(Model model) {
//        String rtspUrl = "rtsp://62.109.19.230:554/iloveyou";
//        videoStreamService.startStream(rtspUrl);
//        model.addAttribute("videoUrl", rtspUrl);
//        return "videoStream"; // имя Вашего шаблона Thymeleaf
//    }
//}



//
//import com.github.kokorin.jaffree.StreamType;
//import com.github.kokorin.jaffree.ffmpeg.FFmpeg;
//import com.github.kokorin.jaffree.ffmpeg.PipeOutput;
//
//
//import lombok.extern.log4j.Log4j2;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
//
//import java.util.concurrent.TimeUnit;
//
//@RestController
//@RequestMapping("/videoStream")
//@Log4j2
//public class VideoController {
//    @GetMapping(value = "/live.mp4")
//    @ResponseBody
//    public ResponseEntity<StreamingResponseBody> livestream(@PathVariable("id") Long tipperId) throws Exception {
//
//        String rtspUrl = "http://61.211.241.239/nphMotionJpeg?Resolution=320x240&Quality=Standard";
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(os -> {
//                    FFmpeg.atPath()
//                            .addArgument("-re")
//                            .addArguments("-acodec", "pcm_s16le")
//                            .addArguments("-rtsp_transport", "tcp")
//                            .addArguments("-i", rtspUrl)
//                            .addArguments("-vcodec", "copy")
//                            .addArguments("-af", "asetrate=22050")
//                            .addArguments("-acodec", "aac")
//                            .addArguments("-b:a", "96k" )
//                            .addOutput(PipeOutput.pumpTo(os)
//                                    .disableStream(StreamType.AUDIO)
//                                    .disableStream(StreamType.SUBTITLE)
//                                    .disableStream(StreamType.DATA)
//                                    .setFrameCount(StreamType.VIDEO, 100L)
//                                    //1 frame every 10 seconds
//                                    .setFrameRate(0.1)
//                                    .setDuration(1, TimeUnit.HOURS)
//                                    .setFormat("ismv"))
//                            .addArgument("-nostdin")
//                            .execute();
//                });
//
//    }
//}
//
//
//












