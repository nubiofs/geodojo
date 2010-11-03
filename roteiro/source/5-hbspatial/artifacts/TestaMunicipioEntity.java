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

public class TestaMunicipioEntity {

	
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
	public void testaConsulta()
	{
		Query query = this.em.createQuery("from Municipio where uf = :uf ").setParameter("uf", "SE");
		
		List<Municipio> municipiosSergipanos = query.getResultList();
		
		Assert.assertTrue(municipiosSergipanos.size() == 75);
		
	}
	
	
	

}
