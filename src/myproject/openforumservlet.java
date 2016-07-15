/* This java file creates posts entity in Google App Engine DataStore*/

package myproject;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

import java.util.Date;
/**
 * Servlet implementation class openforumservlet
 */
//@SuppressWarnings("serial")
public class openforumservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public openforumservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");
		String fid=req.getParameter("fid"); 
		String uname=req.getParameter("uname");
		String cont=req.getParameter("tcont");
		
		req.setAttribute("uname",uname);
		
		Entity e;
		int postid,count;
		
		DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
		
		try{
			//tc= ds.prepare(new Query("posts")).asSingleEntity();
			count=ds.prepare(new Query("posts")).countEntities(FetchOptions.Builder.withDefaults());
		
		if(count== 0)
		{
		
		e=new Entity("posts");
		 postid=1;
		 e.setProperty("fid", fid);
		 e.setProperty("pid", postid);
		 e.setProperty("uname",uname );
		 e.setProperty("message",cont);
		 e.setProperty("posteddate",new Date());
		 e.setProperty("pcount",postid);
		 ds.put(e);
		
		}else{
			
		//	count=ds.prepare(new Query("posts")).countEntities(FetchOptions.Builder.withDefaults());
		//	totalEntities = (long) tc.getProperty("count");
	
   		 e=new Entity("posts");
		 postid=(int)count+1;
		 e.setProperty("fid", fid);
		 e.setProperty("pid", postid);
		 e.setProperty("uname",uname );
		 e.setProperty("message",cont);
		 e.setProperty("posteddate",new Date());
		 e.setProperty("pcount",postid);
		 ds.put(e);
				
		 RequestDispatcher dispatcher = req.getRequestDispatcher("/output.jsp");
				dispatcher.forward(req, resp);	
			}	
		}
		catch(NullPointerException n)
		{n.printStackTrace();}
		
		
		
	}

	

}
