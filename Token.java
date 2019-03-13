package com.woniu.token;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//令牌
@WebServlet("/Token.do")
public class Token extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method=request.getParameter("method");
		if("goInput".equals(method)){
			goInput(request,response);
		}else if ("save".equals(method)){
			save(request,response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void goInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String token=UUID.randomUUID().toString();//生成一个令牌
		request.getSession().setAttribute("token", token);//放入session
		request.getRequestDispatcher("user.jsp").forward(request, response);

	}

	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //校验令牌
		String cTaken=request.getParameter("token");
		String sTaken=(String)request.getSession().getAttribute("token");
		if(cTaken==null || sTaken==null ||!cTaken.equals(sTaken)){
			response.sendRedirect("error.jsp");
			return;
		}else{
			request.getSession().removeAttribute("token");
			
		}
		
		String name= request.getParameter("name");
        String pass=request.getParameter("pass");
        System.out.println(name+"    "+pass);
	}

}
