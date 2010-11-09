package org.latinoware.geodojo.app.bean.producer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Producer(CDI) criado para disponibilizar o EntityManager em toda a aplicacao.
 * @author ranophoenix
 *
 */
@ApplicationScoped
public class EntityManagerProducer {

    private EntityManagerFactory emf;

    @PostConstruct
    public void createEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("geodojodbspatial");
    }

    @Produces
    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public void disposeEntityManager(@Disposes EntityManager em) {
        em.close();
    }
}
	 
