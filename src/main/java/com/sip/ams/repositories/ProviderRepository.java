package com.sip.ams.repositories;


import org.springframework.data.jpa.repository.Query;
import com.sip.ams.entities.Article;
import java.util.List;
//import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sip.ams.entities.Provider;
@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {
	
	@Query("FROM Article a WHERE a.provider.id = ?1")
	List<Article> findArticlesByProvider(long id);
	
	@Query("FROM Provider p WHERE p.name like %:name% ")
	List<Provider> findProviderByName(String name);
}