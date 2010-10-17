package org.latinoware.geodojo.servlet;

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
import twitter4j.PagableResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

/**
 *
 * @author ranophoenix
 */
public class GeoRSS extends HttpServlet {

    private ArrayList<User> getFollowersUsers(Twitter twitter, String userName) {
        PagableResponseList<User> tmp = null;
        ArrayList<twitter4j.User> followersUsers = new ArrayList<User>();
        long cursor = -1;
        try {
            tmp = twitter.getFollowersStatuses(userName, cursor);
            while (tmp != null && !tmp.isEmpty()) {
                followersUsers.addAll(tmp);
                if (!tmp.hasNext()) {
                    break;
                }
                cursor = tmp.getNextCursor();
                tmp = twitter.getFollowersStatuses(userName, cursor);
            }
        } catch (TwitterException e) {
            return null;
        }

        return followersUsers;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TwitterException {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();        
        Twitter twitter = new TwitterFactory().getInstance();
        List<User> followers = getFollowersUsers(twitter, "latinoware");
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

            for (User follower : followers) {
                if (follower.isGeoEnabled()) {
                    if (follower.getStatus() != null && follower.getStatus().getGeoLocation() != null) {
                        String title = "&lt;img src=\"" + follower.getProfileImageURL().toString() + "\" /&gt;" + follower.getScreenName();
                        String alternate = follower.getProfileImageURL().toString();
                        String id = follower.getScreenName();
                        String updated = follower.getCreatedAt().toString();
                        String summary = follower.getDescription();
                        GeoLocation geo = follower.getStatus().getGeoLocation();
                        String lon = Double.toString(geo.getLongitude());
                        String lat = Double.toString(geo.getLatitude());
                        out.println(String.format(template, title, alternate, id, updated, summary, lon, lat));
                    }
                }
            }

            out.println("</feed>");
        } finally {
            out.close();
        }
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
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
