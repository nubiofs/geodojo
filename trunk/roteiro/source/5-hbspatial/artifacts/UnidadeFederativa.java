package org.latinoware.geodojo.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.MultiPolygon;

@Entity
@Table(name="uf")
public class UnidadeFederativa {

	@Id
	@GeneratedValue
	public Long id;
	
	public String nome;
	
	@Column(name = "the_geom")
    @Type(type = "org.hibernatespatial.GeometryUserType")
    private MultiPolygon poligono;

	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public MultiPolygon getPoligono() {
		return poligono;
	}

	public void setPoligono(MultiPolygon poligono) {
		this.poligono = poligono;
	}
	
	
	
}
