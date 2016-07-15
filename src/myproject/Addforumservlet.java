/* this java code creates Forums Entity in Google App Engine DataStore*/
package myproject;

import java.io.IOException;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import javax.servlet.RequestDispatcher;

//@SuppressWarnings("serial")
public class Addforumservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/plain");
		
		String forumname=req.getParameter("forumname");
		String datepick=req.getParameter("datepick");
		String content=req.getParameter("content");
		
		
		Entity e;
		int forumid,count;
		
		DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
		try{
			//tc= ds.prepare(new Query("forums"));//.asSingleEntity();
			count=ds.prepare(new Query("forums")).countEntities(FetchOptions.Builder.withDefaults());
		if(count== 0)//(tc==null)
		{
			
		e=new Entity("forums");
		forumid=1;
		    e.setProperty("forumid", forumid);
			e.setProperty("forumname",forumname);
			e.setProperty("datepick",datepick);
			e.setProperty("content",content);
			ds.put(e);
		
		}else{
			
						
			e=new Entity("forums");
			forumid=count+1;
			    e.setProperty("forumid", forumid);
				e.setProperty("forumname",forumname);
				e.setProperty("datepick",datepick);
				e.setProperty("content",content);
				ds.put(e);
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("/output.jsp");
				dispatcher.forward(req, resp);	
			}	
		}
		catch(NullPointerException n)
		{n.printStackTrace();}
		
		
		
		
	/*	
		
	
		Entity e=new Entity("forums",forumname);
		e.setProperty("forumid", forumid);
		e.setProperty("forumname",forumname);
		e.setProperty("datepick",datepick);
		e.setProperty("content",content);
		ds.put(e);*/
		
		
		
		
		
		
	//	resp.sendRedirect("/mainpage.jsp");//?guestbookName=" + guestbookName);
	//	RequestDispatcher dispatcher = req.getRequestDispatcher("/mainpage.jsp");
	//	dispatcher.forward(req, resp);
	//	Key key=KeyFactory.createKey("forumid-1500798692",1500798692);
		
	//	try{
		// Entity e1=ds.get(key);
		 
		
		 
		/* Query q = new Query("forum");//.setFilter(heightRangeFilter);
		 PreparedQuery pq = ds.prepare(q);


		 for (Entity result : pq.asIterable()) {
		   String fname = (String) result.getProperty("forumname");
		   String fdate = (String) result.getProperty("datepick");
		   String desc=(String)result.getProperty("content");
		   System.out.println(fname+""+fdate+""+desc);
		 }*/
		
	//	}
			
		/// catch (EntityNotFoundException e1) {
			// System.out.println(e1);
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
		//}
	
		
		
		
	}

	

}
