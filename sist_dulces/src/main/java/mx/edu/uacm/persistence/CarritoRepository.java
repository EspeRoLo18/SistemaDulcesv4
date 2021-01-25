package mx.edu.uacm.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mx.edu.uacm.domain.Carrito;

public interface CarritoRepository extends CrudRepository<Carrito, Long> {

	@Query(value="SELECT * FROM \"CARRITO\"", nativeQuery=true)
	List<Carrito> findAllEvent();
	
	@Query(value="SELECT * FROM \"CARRITO\" WHERE id = :id", nativeQuery=true)
	Carrito buscarPorId(@Param("id") Long id);
	
	@Query(value="SELECT * FROM \"CARRITO\" WHERE id = :id", nativeQuery=true)
	Optional<Carrito> findById(@Param("id") Long id);
}
