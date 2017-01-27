package com.swapasya.core;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Myservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Myservlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String btn=request.getParameter("sbtn");
		if("btn1".equals(btn))
		{
			callingBtn1();
		}
		else if("btn2".equals(btn))
		{
			callingBtn2();
		}
		else if("btn3".equals(btn))
		{
			callingBtn3();
		}
		else if("btn4".equals(btn))
		{
			callingBtn4();
		}
		else if("btn5".equals(btn))
		{
			callingBtn5();
		}
		else if("btn6".equals(btn))
		{
			callingBtn6();
		}
		else if("btn7".equals(btn))
		{
			callingBtn7();
		}
		else if("btn8".equals(btn))
		{
			callingBtn8();
		}
		else if("btn9".equals(btn))
		{
			callingBtn9();
		}
		else if("btn10".equals(btn))
		{
			callingBtn10();
		}
		
	}
	
	
	
	void callingBtn1()
	{
		
	}
	void callingBtn2()
	{
		
	}
	void callingBtn3()
	{
		
	}
	void callingBtn4()
	{
		
	}
	void callingBtn5()
	{
		
	}
	void callingBtn6()
	{
		
	}
	void callingBtn7()
	{
		
	}
	void callingBtn8()
	{
		
	}
	void callingBtn9()
	{
		
	}
	void callingBtn10()
	{
		
	}

}
