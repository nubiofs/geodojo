package org.latinoware.geodojo.app.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.latinoware.geodojo.app.entity.Geotwitt;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class TestaGeoTwitt {

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
	public void inserir()
	{

		//Ponto Central Foz do Iguacu
		Coordinate coord = new Coordinate(-54.4818658859215, -25.4677504589312);
		Point location = new GeometryFactory().createPoint(coord);
		location.setSRID(4326);
		
		
		Geotwitt twitt = new Geotwitt();
		twitt.setAutor("rafikdabahia");
		twitt.setMensagem("Testando o nosso primeiro #geotwitt com localizacao");
		twitt.setLocation(location);
		
		this.em.getTransaction().begin();
		
		this.em.persist(twitt);
		
		this.em.getTransaction().commit();
		
		
		
	}
	
	@Test
	public void atualizar()
	{

		Geotwitt twitt = (Geotwitt) this.em.createQuery("from Geotwitt").setMaxResults(1).getSingleResult();
		
		twitt.setAutor("rafikdabahia");
		twitt.setMensagem("Testando o nosso primeiro #geotwitt com localizacao. UPDATED");
		
		
		this.em.getTransaction().begin();
		
		this.em.merge(twitt);
		
		this.em.getTransaction().commit();
		
		
		
	}
	
	@Test
	public void procurarRemover()
	{

		List<Geotwitt> twitts = this.em.createQuery(" from Geotwitt").getResultList();
	
		Assert.assertTrue(twitts.size() > 0);
		
		this.em.getTransaction().begin();
		
		for (Geotwitt geotwitt : twitts) {
		
			this.em.remove(geotwitt);
			
		}
		
		this.em.getTransaction().commit();
		
		
		
		
		
	}
	
	
}
