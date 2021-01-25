package mx.edu.uacm.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mx.edu.uacm.domain.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	@Query(value="SELECT * FROM \"CLIENTE\" WHERE correo = :correo AND password = MD5(:password)", nativeQuery=true)
	Cliente findByEmailAndPassword(@Param("correo") String correo,@Param("password") String password);
	
	@Query(value="SELECT * FROM \"CLIENTE\" WHERE id = :id", nativeQuery=true)
	Optional<Cliente> findById(@Param("id") Long id);
}
