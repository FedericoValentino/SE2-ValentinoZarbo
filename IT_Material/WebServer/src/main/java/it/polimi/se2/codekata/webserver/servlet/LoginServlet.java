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

        response.setStatus(200);
        request.getRequestDispatcher("/WEB-INF/page-jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader postLoad= request.getReader();
        String usname="null";
        String isEdu="no";
        String uid;
        String sjson= postLoad.readLine();
        HashMap<String, String> result = new ObjectMapper().readValue(sjson, HashMap.class);
        usname=result.get("username");
        isEdu=result.get("isEdu");
        uid= result.get("uid");
        HttpSession ses=request.getSession();
        if(isEdu.equals("true"))//todo test if this is good with register
            isEdu="yes";
        else isEdu="no";



        //todo token control
        ses.setAttribute("isEdu",isEdu);
        ses.setAttribute("uid", uid );
        ses.setAttribute("username", usname );
        response.setStatus(200);

        /*
        if(  usname.equals("a") ){//insead of this check some kind of token that client received from auth

            if(isSt.equals("on")){
                isSt="yes";
            }
            ses.setAttribute("isEdu",isSt);
            ses.setAttribute("uid", uid );
            //response.getWriter().append("ok");
            response.setStatus(200);

        }
*/

    }
}
