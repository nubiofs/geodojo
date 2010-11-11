package org.latinoware.geodojo.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Servlet implementation class GeoRSS
 */
public class GeoRSS extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws Exception 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	String user = request.getParameter("user");
    	
    	if(user == null)
    	{
    		throw new Exception("Nenhum valor para o parametro de usuario definido!");
    	}
    	
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();        
        Twitter twitter = new TwitterFactory().getInstance();
        List<Status> statusList = getGeoStatusList(twitter, user);
        String template = "<entry>"
                + "<title>%s</title>"
                + "<link rel=\"alternate\" type=\"text/html\" href=\"%s\" />"
                + "<id>%s</id>"
                + "<updated>%s</updated>"
                + "<summary type=\"html\">%s</summary>"
                + "<geo:long>%s</geo:long>"
                + "<geo:lat>%st</geo:lat>"
                + "</entry>";
        try {
            out.println("<feed xmlns=\"http://www.w3.org/2005/Atom\" xmlns:geo=\"http://www.w3.org/2003/01/geo/wgs84_pos#\">");
            out.println("<rights>GeoDoJo, All rights reserved.</rights>");
            out.println("<title>GeoDojo</title>");
            out.println("<link href=\"http://www.latinoware.org\"/> ");
            out.println("<updated>2010-10-11T22:58:24-08:00</updated>");
            out.println("<author><name>Rafael Soto e Robert Anderson</name></author>");

            for (Status status : statusList) {
               
                   
                        String title = "&lt;img src=\"" +  status.getUser().getProfileImageURL().toString() + "\" /&gt;" +  status.getUser().getScreenName();
                        String alternate = status.getUser().getProfileImageURL().toString();
                        String id =  status.getUser().getScreenName();
                        String updated =  status.getUser().getCreatedAt().toString();
                        String summary =  status.getText();
                        GeoLocation geo = status.getGeoLocation();
                        String lon = Double.toString(geo.getLongitude());
                        String lat = Double.toString(geo.getLatitude());
                        out.println(String.format(template, title, alternate, id, updated, summary, lon, lat));
                    
                }
            

            out.println("</feed>");
        } finally {
            out.close();
        }
    }

    private List<Status> getGeoStatusList(Twitter twitter, String user) throws TwitterException {
    	
    	List<Status> statusList = twitter.getUserTimeline(user,new Paging(1, 10));
  	    
    	List<Status> geoStatusList = new ArrayList<Status>();
    	
  	    for (Status status : statusList) {
  	    	
  	    	if(status.getGeoLocation() != null )
  	    		geoStatusList.add(status);
  		}
  	    
  	    return geoStatusList;
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (TwitterException ex) {
            throw new ServerException("TwitterException", ex);
        } catch (Exception ex) {
        	throw new ServerException("Everything else exception", ex);
		}
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (TwitterException ex) {
            throw new ServerException("TwitterException", ex);
        } catch (Exception ex) {
        	throw new ServerException("Everything else exception", ex);
		}
        
    }
	
}
