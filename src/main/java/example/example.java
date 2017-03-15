package example;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

@RestController
@SpringBootApplication
public class example {

    @RequestMapping("/")
    String showMetadata() {
//        Video video = new Video();
//        video.setTitle("Peter");
//        video.setDescription("Gary");
//        videoRepository.save(video);
        return "Check out /videos for some cool stuff!";
    }

    @RequestMapping("/videos")
    String showAll() {
        String data = "";
        for (Video v : videoRepository.findAll()) {
            data += String.format("<br /><h2>%s:</h2><p>%s<br /><a href=\"/frames/%s\">frames</a></p>", v.getTitle(), v.getDescription(), v.getVideoId());
        }
        return data.length() == 0 ? "Nothing was found." : data;
    }

    @RequestMapping("/addRandomFrames")
    String bragAboutXuebin() {
        for (Video v : videoRepository.findAll()) {
            VideoFrames frames = new VideoFrames();
            v.setVideoFrames(frames);
            videoFrameStore.setContent(frames, (new ByteArrayInputStream("Xuebin is bae".getBytes())));
            videoRepository.save(v);
        }
        return "success!";
    }

    @RequestMapping("/parrot/{phrase}")
    String parrotPhrase(@PathVariable("phrase") String phrase) {
        return phrase;
    }

    @RequestMapping("/frames/{id}")
    String getFrames(@PathVariable("id") String videoId) {
        Video v = videoRepository.findOne(videoId);
        String resp;
        if (v == null) {
            resp = String.format("couldn't find video by id %s", videoId);
        }
        else {
            InputStream framesInput = videoFrameStore.getContent(v.getVideoFrames());
            StringWriter writer = new StringWriter();
            try {
                IOUtils.copy(framesInput, writer, "utf8");
                resp = writer.toString();
            }
            catch(IOException ioe) {
                resp = "Error reading frames";
            }
        }
        return resp;
    }

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoFrameStore videoFrameStore;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(example.class, args);
    }

    @Bean
    public String bucket() {
        return System.getenv("AWS_BUCKET");
    }

    @Bean
    public Region region() {
        return Region.getRegion(Regions.fromName(System.getenv("AWS_REGION")));
    }


}
