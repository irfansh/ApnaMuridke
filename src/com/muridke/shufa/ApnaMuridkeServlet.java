package com.muridke.shufa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

public class ApnaMuridkeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private FacebookClient facebookClient;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request,response);
	}
	public void processRequest(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		String signedRequest = (String) request.getParameter("signed_request");
		FacebookSignedRequest facebookSR = null;
		try {
			facebookSR = FacebookSignedRequest.getFacebookSignedRequest(signedRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String oauthToken = facebookSR.getOauth_token();
		PrintWriter writer = response.getWriter();
		if(oauthToken == null) {
			
			//this is nice
			
			response.setContentType("text/html");
			String authURL = "https://www.facebook.com/dialog/oauth?client_id="
								+ Constants.API_KEY + "&redirect_uri=https://apps.facebook.com/meramuridke&scope=";
			writer.print("<script> top.location.href='"	+ authURL + "'</script>");
			writer.close();

		}
		else {
			
			facebookClient = new DefaultFacebookClient(oauthToken);
			Connection<User> myFriends = facebookClient.fetchConnection("1292453198/friends", User.class);
			writer.print("<table><tr><th>Photo</th><th>Name</th><th>Id</th></tr>");
			for (List<User> myFriendsList : myFriends) {
	
				for(User user: myFriendsList)
					writer.print("<tr><td><img src=\"https://graph.facebook.com/" + user.getId() + "/picture\"/></td><td>" + user.getName() +"</td><td>" + user.getId() + "</td></tr>");
	
			}
			writer.print("</table>");
			writer.close();
			
		}

	}

}