package aristoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import aristoweb.model.Post;

public interface PostRepository extends JpaRepository<Post,Long>{

	Post findByArticleTitle(String article_title);
	
}
