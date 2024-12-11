package example.controller;

        import org.springframework.stereotype.Service;
        import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

        import java.io.IOException;
        import java.io.InputStream;
        import java.util.List;

@Service
public class StreamService {
    private final String ffmpegPath = "C:/ffmpeg/bin/ffmpeg.exe";
    private final List<String> rtspUrls = List.of(
            "rtsp://admin:@192.168.1.101:554/Streaming/channels/1",
            "rtsp://admin:@192.168.1.101:554/h264/ch1/main/av_strem",
            "rtsp://admin:@192.168.1.101:554/Streaming/channels/1"
    );

    public StreamingResponseBody streamVideo(int index) {
        if (index < 0 || index >= rtspUrls.size()) {
            throw new IllegalArgumentException("Неверный индекс потока");
        }
        String rtspUrl = rtspUrls.get(index);

                List<String> command = List.of(
                ffmpegPath,
                "-i",
                rtspUrl,
                "-c:v", "libx264", // Выбор кодека H.264 для видео
                "-b:v", "500k",
                "-preset", "fast",
                "-c:a", "aac", // Кодирование аудио в AAC
                "-b:a", "32", // Установка битрейта для аудио
                "-f", "mp4",
               "C:/website/src/main/resources/static/videos/index.mp4"

        );


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


