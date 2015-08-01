package application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import application.model.Feeling;

public interface FeelingRepository extends CrudRepository<Feeling, Integer> {

	public void deleteByName(@Param(value = "name") String name);

	public Feeling findByName(@Param(value = "name") String name);
}
