/* This code creates reply messages of the users in replies Entity*/
package myproject;

import java.io.IOException;


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
import javax.servlet.RequestDispatcher;
//@SuppressWarnings("serial")
public class replyservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/plain");
	
		
		String uname=req.getParameter("uname");
		String fid=req.getParameter("fid"); 
		String pid=req.getParameter("postid");
		String replycontent=req.getParameter("replycont");
		//System.out.println("fid="+fid);
		req.setAttribute("uname", uname);
		
		Entity e;
		int replyid,count;
		
		DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
		
		try{
			//tc= ds.prepare(new Query("posts")).asSingleEntity();
			count=ds.prepare(new Query("replies")).countEntities(FetchOptions.Builder.withDefaults());
			
		if(count== 0)
		{
		
		    e=new Entity("replies");
		    replyid=1;
		    e.setProperty("replyid", replyid);
		    e.setProperty("fid", fid);
		    e.setProperty("pid", pid);
		    e.setProperty("uname",uname );
			e.setProperty("replycontent",replycontent);
			e.setProperty("posteddate",new Date());
			e.setProperty("rcount",replyid);
			ds.put(e);
		
		}else{
			
			e=new Entity("replies");
			replyid=(int)count+1;
			e.setProperty("replyid", replyid);
		    e.setProperty("pid", pid);
			e.setProperty("fid", fid);
			e.setProperty("uname",uname );
			e.setProperty("replycontent",replycontent);
			e.setProperty("posteddate",new Date());
			e.setProperty("rcount",replyid);
			ds.put(e);
				
			
			}	
		RequestDispatcher dispatcher = req.getRequestDispatcher("/output.jsp");
		dispatcher.forward(req, resp);
		}
		catch(Exception n)
		{n.printStackTrace();}
		
		
		
		
	
	}
}
