package example;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class example {

    @RequestMapping("/")
    String showMetadata() {
//        Video video = new Video();
//        video.setTitle("Peter");
//        video.setDescription("Gary");
//        videoRepository.save(video);
//        Iterable<videoMetadata> videoMetadatas = videoRepository.findAll();
//        for (VideoMetadata videoMetadata : videoMetadatas) {
//
//        }
        return "Check out /videos for some cool stuff!";
    }

    @RequestMapping("/videos")
    String hello_bucket_region() {
        String data = "";
        for (Video v : videoRepository.findAll()) {
            data += String.format("<br /><h2>%s:</h2><p>%s</p>", v.getTitle(), v.getDescription());
        }
        return data.length() == 0 ? "Nothing was found." : data;
    }

    @Autowired
    private VideoRepository videoRepository;

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
