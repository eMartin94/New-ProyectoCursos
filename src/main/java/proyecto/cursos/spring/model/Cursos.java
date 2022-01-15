package proyecto.cursos.spring.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="cursos")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Cursos.findAll", query = "SELECT c FROM Cursos c"),
	@NamedQuery(name = "Cursos.findById", query = "SELECT c FROM Cursos c WHERE c.id = :id"),
	@NamedQuery(name = "Cursos.findByNombre", query = "SELECT c FROM Cursos c WHERE c.nombre = :nombre"),
	@NamedQuery(name = "Cursos.findByDuracion", query = "SELECT c FROM Cursos c WHERE c.duracion = :duracion"),
	@NamedQuery(name = "Cursos.findByCosto", query = "SELECT c FROM Cursos c WHERE c.costo = :costo"),
})
public class Cursos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional=false)
    @Column(name="id")
    private Integer id;


    @Basic(optional = false)
    @Column(name="nombre")
    private String nombre;

    @Basic(optional =false)
    @Column(name="duracion")
    private String duracion;

    @Basic(optional = false)
    @Column(name="costo")
    private double costo;

    /* @JoinColumn(name = "categoria", referencedColumnName = "id")
	@ManyToOne(optional=false)
	private Categoria categoria; */

    public Cursos() {
    }

    public Cursos(String nombre, String duracion, double costo) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.costo = costo;
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

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    /* public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    } */
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cursos)) {
            return false;
        }
        Cursos other = (Cursos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
	public String toString() {
		return "proyecto.cursos.spring.model.Cursos[id = "+id+",nombre="+nombre+",duracion="+duracion+",costo="+costo+"]";
	}
}
