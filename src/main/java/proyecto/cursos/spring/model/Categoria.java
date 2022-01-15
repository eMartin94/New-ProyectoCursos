package proyecto.cursos.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="categoria")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
	@NamedQuery(name = "Categoria.findById", query = "SELECT c FROM Categoria c WHERE c.id = :id"),
	@NamedQuery(name = "Categoria.findByNombre", query = "SELECT c FROM Categoria c WHERE c.nombre = :nombre"),
})
public class Categoria implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional=false)
    @Column(name="id")
    private Integer id;


    @Basic(optional = false)
    @Column(name="nombre")
    private String nombre;

    /* @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria", fetch=FetchType.LAZY)
	@JsonBackReference(value = "categoria_cursos")
	private List<Cursos> ListCursos; */

    public Categoria(){

    }

    public Categoria(Integer id,String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    /* @XmlTransient
    public List<Cursos> getListCursos() {
        return ListCursos;
    }

    public void setListCursos(List<Cursos> ListCursos) {
        this.ListCursos = ListCursos;
    } */

    @Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
		
	}
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Categoria)) {
			return false;
		}
		Categoria other = (Categoria) object;
		if((this.id == null && other.id != null) || (this.id != null && this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

    @Override
	public String toString() {
		return "proyecto.cursos.spring.model.Categoria[id = "+id+",nombre="+nombre+"]";
	}
}
