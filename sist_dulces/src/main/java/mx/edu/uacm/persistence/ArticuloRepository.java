package mx.edu.uacm.persistence;

import org.springframework.data.repository.CrudRepository;

import mx.edu.uacm.domain.Articulo;

public interface ArticuloRepository extends CrudRepository<Articulo, Long> {

}
