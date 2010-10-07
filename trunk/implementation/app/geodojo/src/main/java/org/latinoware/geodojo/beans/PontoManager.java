package org.latinoware.geodojo.beans;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.latinoware.geodojo.entity.Ponto;

/**
 * Bean de teste para tratar a requisicao vinda da view.
 * Note que nao e um managed bean do JSF.. UHUUUU!!!
 * @author Rafael Soto
 *
 */
@RequestScoped
@Named
public class PontoManager {

	@Inject
	private EntityManager em;
	
	public void testeCriar()
	{
		Ponto p = new Ponto();
		
		p.setNome("Teste " + Math.random());
		
		em.getTransaction().begin();
		
			em.persist(p);
		
		em.getTransaction().commit();
	}
	
}
