package proyecto.cursos.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.cursos.spring.exception.ResourceNotFoundException;
import proyecto.cursos.spring.model.Cursos;
import proyecto.cursos.spring.repository.CursosRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest/cursos")
public class CursosController {

    @Autowired
    private CursosRepository repositorioCurso;

    @GetMapping("/curso")
    public List<Cursos> getAllCursos() {
        return repositorioCurso.findAll();
    }

    @GetMapping("/curso/{id}")
	public ResponseEntity<Cursos> getCursoById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
				Cursos curso = repositorioCurso.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Curso not found for this id :: " + id));
		return ResponseEntity.ok().body(curso);
	}

    @PostMapping("/curso")
    public Cursos newCategoria(@RequestBody Cursos curso) {
        return repositorioCurso.save(curso);

    }

    @PutMapping("/curso/{id}")
    public ResponseEntity<Cursos> updateCurso(@PathVariable(value = "id") Integer id,
            @RequestBody Cursos cursoDetails) throws ResourceNotFoundException {
        Cursos curso = repositorioCurso.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso not found for this id :: " + id));

        curso.setId(cursoDetails.getId());
        curso.setNombre(cursoDetails.getNombre());
        curso.setDuracion(cursoDetails.getDuracion());
        curso.setCosto(cursoDetails.getCosto());
        final Cursos updatedCurso = repositorioCurso.save(curso);
        return ResponseEntity.ok(updatedCurso);
    }
    
    @DeleteMapping("/curso/{id}")
	public Map<String, Boolean> deleteCurso(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
		Cursos curso = repositorioCurso.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Curso not found for this id :: " + id));

		repositorioCurso.delete(curso);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
