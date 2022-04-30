package cl.duoc.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.app.entity.Pais;

@Repository
public interface PaisRepository extends CrudRepository<Pais, Long> {

	//void delete(Integer id);
	
	Optional<Pais> findById(Integer id);
	
	List<Pais> findAll();
}
