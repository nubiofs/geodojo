package org.latinoware.geodojo.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.latinoware.geodojo.entity.Ponto;
import org.ol4jsf.util.WKTFeaturesCollection;

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
    private List<String> wkts = new ArrayList<String>();

    @PostConstruct
    public void fillFeatures() {
        wkts.add("POLYGON((-117.421875 58.7109375, -120.234375 36.2109375, -99.140625 29.1796875, -83.671875 41.1328125, -81.5625 46.7578125, -117.421875 58.7109375))");
        wkts.add("LINESTRING(21.796875 50.2734375, 53.4375 56.6015625, 51.328125 39.0234375, 75.234375 43.9453125, 98.4375 42.5390625, 96.328125 27.7734375, 106.875 34.8046875)");
        wkts.add("POINT(-56.25 -11.6015625)");
    }

    public void testeCriar() {
        Ponto p = new Ponto();

        p.setNome("Teste " + Math.random());

        em.getTransaction().begin();

        em.persist(p);

        em.getTransaction().commit();
    }

    public String getWkts() {
        WKTFeaturesCollection features = new WKTFeaturesCollection();
        features.addAllFeatures(wkts);

        return features.toMap();
    }
}
