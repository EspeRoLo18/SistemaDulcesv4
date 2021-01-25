package mx.edu.uacm.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mx.edu.uacm.domain.Tarjeta;

public interface TarjetaRepository extends CrudRepository<Tarjeta, Long> {
	
	@Query(value="SELECT * FROM \"TARJETA\"", nativeQuery=true)
	List<Tarjeta> findAllEvent();
	
	@Query(value="SELECT * FROM \"TARJETA\" WHERE id = :id", nativeQuery=true)
	Tarjeta buscarPorId(@Param("id") Long id);
	
	@Query(value="SELECT * FROM \"TARJETA\" WHERE id = :id", nativeQuery=true)
	Optional<Tarjeta> findById(@Param("id") Long id);
}
