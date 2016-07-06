package application.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import application.model.Feeler;

public interface FeelerRepository extends CrudRepository<Feeler, Long> {

	// @Query("delete f from Feeler where f.username = :usename")
	public void deleteByUsername(@Param(value = "username") String username);

	// @Query("select f from Feeler where f.username = :usename")
	public Feeler findByUsername(@Param(value = "username") String username);

	public Feeler findByUsernameAndPassword(
			@Param(value = "username") String username,
			@Param(value = "password") String password);

	public Feeler findByEmail(@Param(value = "email") String email);

}
