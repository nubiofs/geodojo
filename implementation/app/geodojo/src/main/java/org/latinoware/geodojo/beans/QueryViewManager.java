package org.latinoware.geodojo.beans;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.ol4jsf.util.WKTFeaturesCollection;

/**
 *
 * @author ranophoenix
 */
@Named
@RequestScoped
public class QueryViewManager {

    @Inject
    EntityManager em;
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    private String wkts;

    public String getWkts() {
        return wkts;
    }

    public void setWkts(String wkts) {
        this.wkts = wkts;
    }
    private String qryLanguage = "POSTGIS";

    public String getQryLanguage() {
        return qryLanguage;
    }

    public void setQryLanguage(String qryLanguage) {
        this.qryLanguage = qryLanguage;
    }

    public String executeQuery() {
        try {
            WKTFeaturesCollection<String> wktFeatures = new WKTFeaturesCollection<String>();
            Query q;
            if ("POSTGIS".equals(qryLanguage)) {
                q = em.createNativeQuery(query); //Ex: select st_astext(localizacao) from ponto
            } else {
                q = em.createQuery(query); // select p.localizacao from Ponto p
            }
            List<String> result = (List<String>) q.getResultList();
            wktFeatures.addAllFeatures(result);

            setWkts(wktFeatures.toMap());
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
        }
        return null;
    }
}
