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
import proyecto.cursos.spring.model.Categoria;
import proyecto.cursos.spring.repository.CategoriaRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repositorioCategoria;

    @GetMapping("/categoria")
    public List<Categoria> getAllCategorias(){
        return repositorioCategoria.findAll();
    }

	@GetMapping("/categoria/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable(value = "id") Integer id)
			throws ResourceNotFoundException {
				Categoria categoria = repositorioCategoria.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria not found for this id :: " + id));
		return ResponseEntity.ok().body(categoria);
	}

    @PostMapping("/categoria")
    public Categoria newCategoria(@RequestBody Categoria categoria){
        return repositorioCategoria.save(categoria);

    }
    
    @PutMapping("/categoria/{id}")
	public ResponseEntity<Categoria> updateCategoria(@PathVariable(value = "id") Integer id,
			@RequestBody Categoria categoriaDetails) throws ResourceNotFoundException {
    	Categoria categoria = repositorioCategoria.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria not found for this id :: " + id));

    	categoria.setId(categoriaDetails.getId());
    	categoria.setNombre(categoriaDetails.getNombre());
		final Categoria updatedCategoria = repositorioCategoria.save(categoria);
		return ResponseEntity.ok(updatedCategoria);
	}
    
    @DeleteMapping("/categoria/{id}")
	public Map<String, Boolean> deleteCategoria(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
		Categoria categoria = repositorioCategoria.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Categoria not found for this id :: " + id));

		repositorioCategoria.delete(categoria);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
