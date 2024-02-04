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
        String restApiGatewayUrl="http://localhost:8080";
        try{
            restApiGatewayUrl= System.getenv("APIGATEWAY_URL");
        }catch (Exception e){log("APIGATEWAY_URL not set, using http://localhost:8080 as default");}
        ss.setAttribute("restApiUrl",restApiGatewayUrl);
        response.setStatus(200);
        request.getRequestDispatcher("/WEB-INF/page-jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader postLoad= request.getReader();
        String isEdu="false";
        String uid;
        String sjson= postLoad.readLine();
        HashMap<String, String> result = new ObjectMapper().readValue(sjson, HashMap.class);
       // usname=result.get("username");

        uid= result.get("uid");
        HttpSession ses=request.getSession();
        isEdu=result.get("isEdu");
        /*if((.equals("true")))//todo test if this is good with register
            isEdu="yes";
        else isEdu="no";*/



        //todo token control
        ses.setAttribute("isEdu",isEdu);
        ses.setAttribute("uid", uid );
       // ses.setAttribute("username", usname );
        response.setStatus(200);

    }
}
