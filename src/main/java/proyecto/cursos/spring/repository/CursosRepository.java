package proyecto.cursos.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.cursos.spring.model.Cursos;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, Integer>{
    
}
