package application.elasticsearch;

import java.util.List;

public interface ElasticsearchPersistenceService<T> {

	T index(T t);
	T get(Long id);
	T get(String id);
	Boolean delete(Long id);
	Boolean delete(String id);
	T update(T t);
	T updateByScript();
	T upsert(T t);
	T search(T t);
	Boolean bulk(List<T> t);
	
}
