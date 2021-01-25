package mx.edu.uacm.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.uacm.domain.Dulce;
import mx.edu.uacm.domain.TipoDulce;
import mx.edu.uacm.excepciones.AppExcepcion;

public interface DulceRepository extends CrudRepository<Dulce, Long> {
	
	@Query(value="SELECT * FROM \"DULCE\"", nativeQuery=true)
	List<Dulce> findAllEvent();
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="update \"DULCE\" set nombre = :nombre WHERE id = :id", nativeQuery=true)
	int edita_nombre(@Param("nombre") String nombre, @Param("id") int id) throws AppExcepcion;
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="update \"DULCE\" set marca = :marca WHERE id = :id", nativeQuery=true)
	int edita_marca(@Param("marca") String marca, @Param("id") int id) throws AppExcepcion;
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="update \"DULCE\" set cantidad = :cantidad WHERE id = :id", nativeQuery=true)
	int edita_cantidad(@Param("cantidad") int cantidad, @Param("id") int id) throws AppExcepcion;
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="update \"DULCE\" set descripcion = :descripcion WHERE id = :id", nativeQuery=true)
	int edita_descripcion(@Param("descripcion") String descripcion, @Param("id") int id) throws AppExcepcion;
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="update \"DULCE\" set descuento = :descuento WHERE id = :id", nativeQuery=true)
	int edita_descuento(@Param("descuento") double descuento, @Param("id") int id) throws AppExcepcion;
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="update \"DULCE\" set tipo = :tipo WHERE id = :id", nativeQuery=true)
	int edita_tipo(@Param("tipo") String tipo, @Param("id") int id) throws AppExcepcion;
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="update \"DULCE\" set precio = :precio WHERE id = :id", nativeQuery=true)
	int edita_precio(@Param("precio") double precio, @Param("id") int id) throws AppExcepcion;
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value="update \"DULCE\" set imagen = :imagen WHERE id = :id", nativeQuery=true)
	int edita_imagen(@Param("imagen") byte[] imagen, @Param("id") int id) throws AppExcepcion;
	
}
