package it.polimi.se2.codekata.webserver.servlet;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TournamentsServlet", value = "/TournamentsServlet")
public class TournamentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession ss= request.getSession();
        if(ss.isNew()|| ss.getAttribute("uid")==null) {
            response.sendRedirect(getServletContext().getContextPath()+"/LoginServlet");
           return;
        }

        response.setStatus(200);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");


        request.getRequestDispatcher("/WEB-INF/page-jsp/MainPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
