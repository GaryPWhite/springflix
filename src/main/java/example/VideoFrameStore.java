package example;

import org.springframework.content.commons.renditions.Renderable;
import org.springframework.content.commons.repository.ContentStore;

import internal.org.springframework.content.rest.annotations.ContentStoreRestResource;

@ContentStoreRestResource(path="video")
public interface VideoFrameStore extends ContentStore<VideoFrames, String>, Renderable<VideoFrames> {

}