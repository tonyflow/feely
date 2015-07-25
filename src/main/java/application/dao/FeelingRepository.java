package application.dao;

import org.springframework.data.repository.CrudRepository;

import application.model.Feeling;

public interface FeelingRepository extends CrudRepository<Feeling, Integer> {

}
