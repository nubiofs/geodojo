package org.latinoware.geodojo.app.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.latinoware.geodojo.app.entity.Municipio;

public class TestaUnidadeFederativa {
private EntityManager em;
	
	@Before
	public void init() throws Exception {
		
		this.em = Persistence.createEntityManagerFactory("geodojodbspatial").createEntityManager();
	}
	
	@After
	public void release() throws Exception
	{
		this.em.close();
	}
	
	@Test
	public void testaConsultaEspacial()
	{
		Query query = this.em.createQuery("select m from Municipio m, UnidadeFederativa uf where uf.nome = :uf and within(m.poligono,uf.poligono) = true ").setParameter("uf", "SE");
		
		List<Municipio> municipiosSergipanos = query.getResultList();
		
		Assert.assertTrue(municipiosSergipanos.size() == 75);
		
	}
}
