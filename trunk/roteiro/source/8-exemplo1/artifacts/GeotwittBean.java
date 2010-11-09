package org.latinoware.geodojo.app.bean;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.latinoware.geodojo.app.entity.Geotwitt;

import twitter4j.GeoLocation;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Model
public class GeotwittBean {

	private Geotwitt twitt = new Geotwitt();

	private String passwd;
	
	@Inject
	private EntityManager em;
	
	
	public Geotwitt getTwitt() {
		return twitt;
	}

	public void setTwitt(Geotwitt twitt) {
		this.twitt = twitt;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public void update() throws TwitterException
	{
		try {
			this.persistGeoTwitt();
			this.updateTwitter();
			
			this.inicializaBean();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Twitter Enviado!"));
		} catch (Throwable e) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ocorreu um Erro - [" + e.getCause() + "] - " + e.getMessage()));
		}
		
		
	}
	
	private void updateTwitter() throws TwitterException
	{
	    Twitter twitter = new TwitterFactory().getInstance(this.twitt.getAutor(),this.passwd);
	    twitter.updateStatus(this.twitt.getMensagem(),new GeoLocation(this.twitt.getLocation().getY(),this.twitt.getLocation().getX()));    
	}
	
	private void persistGeoTwitt()
	{
		this.em.getTransaction().begin();
		
			this.em.persist(this.twitt);
		
		this.em.getTransaction().commit();
	}
	
	private void inicializaBean()
	{
		this.twitt = new Geotwitt();
		this.passwd = "";
	}
	
	
}
