package example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "video", path = "video")
public interface VideoRepository extends CrudRepository<Video, String> {

}