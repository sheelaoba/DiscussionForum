/* This Code Deletes the Forums from the main page*/

package myproject;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Key;


@SuppressWarnings("serial")
public class deleteservlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		
		String indexkey=req.getParameter("fid1");
		int index=Integer.parseInt(indexkey);
		String user=req.getParameter("luser");
		System.out.println("user="+user);
		req.setAttribute("uname", user);
		System.out.println("indexkey="+indexkey);
		
		DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
		
		    
		Key key=KeyFactory.createKey("forums",index);
	
		ds.delete(key);
		
				
		RequestDispatcher dispatcher = req.getRequestDispatcher("/delete.jsp");
		try{dispatcher.forward(req, resp);}
		catch(Exception e){}
	}}
	/*	try
		{
		Entity e=new Entity("forums");
		e=ds.get(key);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("delete.jsp");
		dispatcher.forward(req, resp);	
		}
		catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(ServletException e){}
		ds.delete(key);*/


	

