package it.polimi.se2.codekata.webserver.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ss= request.getSession();
        if(!ss.isNew()) {
            ss.invalidate();
            ss=request.getSession();
        }
        //ss.setAttribute("isStud", true);
        response.setStatus(200);
        //send html page w/ login page, the login form comunicate with rest api to the api gateway, and w/ response receives url for next servlet
        request.getRequestDispatcher("/WEB-INF/page-jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader postLoad= request.getReader();
        String usname="null";
        String isSt="no";
        String sjson= postLoad.readLine();
        HashMap<String, String> result = new ObjectMapper().readValue(sjson, HashMap.class);
        usname=result.get("username");
        isSt=result.get("isStud");


        // request.getParameter("username");
        //
        if(  usname.equals("a") ){//insead of this check some kind of token that client received from auth

            if(isSt.equals("on")){
                isSt="yes";
            }
            request.getSession().setAttribute("isStud",isSt);
            response.getWriter().append("ok");
            response.setStatus(200);

        }

    }
}
