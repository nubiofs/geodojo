package org.latinoware.geodojo.app.teste;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;


public class TestaFabricarEntityManager {

	@Test
	public void testEntityManagerFactory()
	{
				
		try {
			Persistence.createEntityManagerFactory("geodojodbspatial").createEntityManager().getTransaction().begin();
			
		} catch (Throwable e) {
			
			Assert.fail();
		}

	}
	
}
