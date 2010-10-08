package org.latinoware.geodojo.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Managed Bean(CDI) criado para disponibilizar o EntityManager em toda a aplicacao.
 * A cada nova sessao aberta um novo componente e criado e deve ser injetado nos beans que necessitarem
 * do acesso a base de dados.
 * @author Rafael Soto
 *
 */
@SuppressWarnings("serial")
@SessionScoped
public class EntityManagerBean implements EntityManager,Serializable {

	private EntityManager emDelegate;

	@PostConstruct
	public void createEntityManager() {
	
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("geodojodbspatial");
			emDelegate = emf.createEntityManager();
		
	}
	
	public void destroyEntityManager()
	{
		if (emDelegate == null) {
			emDelegate.flush();
			emDelegate.close();
		}
	}
	
	@Override
	public void clear() {
		
		this.emDelegate.clear();
		
	}

	@Override
	public void close() {
		
		this.emDelegate.close();
		
	}

	@Override
	public boolean contains(Object arg0) {
		
		return this.emDelegate.contains(arg0);
	}

	@Override
	public Query createNamedQuery(String arg0) {
		
		return this.emDelegate.createNamedQuery(arg0);
	}

	@Override
	public Query createNativeQuery(String arg0) {
		
		return this.emDelegate.createNamedQuery(arg0);
	}

	@Override
	public Query createNativeQuery(String arg0, Class arg1) {
		
		return this.emDelegate.createNativeQuery(arg0, arg1);
	}

	@Override
	public Query createNativeQuery(String arg0, String arg1) {
		
		return this.emDelegate.createNativeQuery(arg0, arg1);
	}

	@Override
	public Query createQuery(String arg0) {
		
		return this.emDelegate.createQuery(arg0);
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1) {
		
		return this.emDelegate.find(arg0, arg1);
	}

	@Override
	public void flush() {
		this.emDelegate.flush();
		
	}

	@Override
	public Object getDelegate() {
		
		return this.emDelegate.getDelegate();
	}

	@Override
	public FlushModeType getFlushMode() {
		// TODO Auto-generated method stub
		return this.emDelegate.getFlushMode();
	}

	@Override
	public <T> T getReference(Class<T> arg0, Object arg1) {
		// TODO Auto-generated method stub
		return this.emDelegate.getReference(arg0, arg1);
	}

	@Override
	public EntityTransaction getTransaction() {
		// TODO Auto-generated method stub
		return this.emDelegate.getTransaction();
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return this.emDelegate.isOpen();
	}

	@Override
	public void joinTransaction() {
		this.emDelegate.joinTransaction();
		
	}

	@Override
	public void lock(Object arg0, LockModeType arg1) {
		this.emDelegate.lock(arg0, arg1);
		
	}

	@Override
	public <T> T merge(T arg0) {
		
		return this.emDelegate.merge(arg0);
	}

	@Override
	public void persist(Object arg0) {
		this.emDelegate.persist(arg0);
		
	}

	@Override
	public void refresh(Object arg0) {
		this.emDelegate.refresh(arg0);
		
	}

	@Override
	public void remove(Object arg0) {
		this.emDelegate.remove(arg0);
		
	}

	@Override
	public void setFlushMode(FlushModeType arg0) {
		this.emDelegate.setFlushMode(arg0);
		
	}

	
	
	
	
}
