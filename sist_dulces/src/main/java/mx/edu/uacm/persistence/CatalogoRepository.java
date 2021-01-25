package mx.edu.uacm.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.uacm.domain.Catalogo;
import mx.edu.uacm.excepciones.AppExcepcion;




public interface CatalogoRepository extends CrudRepository<Catalogo, Long> {
	
	@Query(value="SELECT * FROM \"CATALOGO\"", nativeQuery=true)
	List<Catalogo> findAllEvent();
	
	@Query(value="SELECT * FROM \"CATALOGO\" WHERE id = :id", nativeQuery=true)
	Optional<Catalogo> buscarPorId(@Param("id") Long id);
	
	@Query(value="SELECT * FROM \"CATALOGO\" LIMIT 1", nativeQuery=true)
	Catalogo regresa_solo_un_catalogo();
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="update \"CATALOGO\" set nombre = :nombre WHERE id = :id", nativeQuery=true)
	int edita_catalogo_nombre(@Param("nombre") String nombre, @Param("id") int id) throws AppExcepcion;
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="update \"CATALOGO\" set imagen = :imagen WHERE id = :id", nativeQuery=true)
	int edita_catalogo_imagen(@Param("imagen") byte[] imagen, @Param("id") int id) throws AppExcepcion;
}
