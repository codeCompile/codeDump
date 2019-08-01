package aristoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import aristoweb.model.Post;

public interface PostRepository extends JpaRepository<Post,Long>{

	//ArticleTitle is exact propertyname otherwise exception
	Post findByArticleTitle(String article_title);
	
}
