package it.polimi.se2.codekata.webserver.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ManualEvalServlet", value = "/ManualEvalServlet")
public class ManualEvalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ss= request.getSession();
        if(ss.isNew()|| ss.getAttribute("uid")==null) {
            response.sendRedirect(getServletContext().getContextPath()+"/LoginServlet");
            return;
        }
        if(!ss.getAttribute("isEdu").equals("true")){
            response.sendError(403, "students can't access this resource");
            return;
        }

        response.setStatus(200);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("/WEB-INF/page-jsp/manualEval.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
