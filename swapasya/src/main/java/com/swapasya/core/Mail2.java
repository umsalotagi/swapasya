package com.swapasya.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  


/**
 * Servlet implementation class Mail2
 */
@WebServlet("/Mail2")
public class Mail2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mail2() {
        super();
        // TDO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String to = "vinirmititech@gmail.com";//change accordingly  
	      String from = request.getParameter("email");//change accordingly  
	      String host = "127.0.0.1";//or IP address  
	  String subject=request.getParameter("subject");
	  String msg=request.getParameter("msg");
		sendMail(to,from,host,subject,msg);
		response.sendRedirect("index.html");
}
	  
	  
	 static void sendMail(String to,String from,String host,String subject,String msg){ 
	  
	     //Get the session object  
	      Properties properties = System.getProperties();  
	      properties.setProperty("mail.smtp.host", host);  
	      Session session = Session.getDefaultInstance(properties);  
	  
	     //compose the message  
	      try{  
	         MimeMessage message = new MimeMessage(session);  
	         message.setFrom(new InternetAddress(from));  
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	         message.setSubject(subject);  
	         message.setText(msg);  
	  
	         // Send message  
	         Transport.send(message);  
	         System.out.println("message sent successfully....");  
	  
	      }catch (MessagingException mex) {mex.printStackTrace();}  
	   }  
	}  
