package example;

import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.content.commons.annotations.Content;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document
public class Video {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.annotation.Id
    private String videoId = UUID.randomUUID().toString();

    private String title;
    private String description;

    @Content
    @Embedded
    private VideoFrames videoFrames;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VideoFrames getVideoFrames() {
        return videoFrames;
    }

    public void setVideoFrames(VideoFrames videoFrames) {
        this.videoFrames = videoFrames;
    }
}
