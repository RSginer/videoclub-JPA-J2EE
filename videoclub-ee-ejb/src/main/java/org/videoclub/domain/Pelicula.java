/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.videoclub.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@NamedQueries({
   @NamedQuery(name = "Pelicula.findAll", query = "SELECT p FROM Pelicula p ORDER BY p.id") 
})
@Table(name = "peliculas")
public class Pelicula implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private int id;
    
    private String nombre;
    
    private int duracion;
    
    @Column(name = "anyo_grabacion")
    private int anyoGrabacion;
   
    @ManyToOne
    @JoinColumn(name = "categoria")
    private Categoria categoria;
    
    @OneToMany(mappedBy = "pelicula", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    private Set<Cinta> cintas;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "actores_peliculas", joinColumns=@JoinColumn(name="actor"),
            inverseJoinColumns =@JoinColumn(name = "pelicula"))
    private Set<Actor> actores;
    
    
    public Pelicula() {
    }

    public Pelicula(String nombre, int duracion, int anyoGrabacion) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.anyoGrabacion = anyoGrabacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getAnyoGrabacion() {
        return anyoGrabacion;
    }

    public void setAnyoGrabacion(int anyoGrabacion) {
        this.anyoGrabacion = anyoGrabacion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pelicula other = (Pelicula) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "id=" + id + ", nombre=" + nombre + ", duracion=" + duracion + ", anyoGrabacion=" + anyoGrabacion + ", categoria=" + categoria + '}';
    }
    
    
}
