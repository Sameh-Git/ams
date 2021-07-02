package com.sip.ams.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
//import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sip.ams.entities.Article;
@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
	
	
	@Query("FROM Article a WHERE a.label like %:label% ")
	List<Article> findArticlesByLabel(String label);
}