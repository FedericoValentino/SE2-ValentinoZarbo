package it.polimi.se2.codekata.webserver.servlet;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TournamentsServlet", value = "/TournamentsServlet")
public class TournamentsServlet extends HttpServlet {
    private TemplateEngine tEngine;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession ss= request.getSession();
        if(ss.isNew()) {
            response.sendRedirect(getServletContext().getContextPath()+"LoginServlet");
           return;
        }
        //ss.setAttribute("isStud", true);
        response.getWriter().append("ok");
        response.setStatus(200);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");



        request.getRequestDispatcher("/WEB-INF/page-jsp/MainPage.jsp").forward(request, response);





/*
        ///thymeleah vù
        HttpSession sessione=request.getSession();
        if(sessione.isNew()){
            response.sendRedirect(getServletContext().getContextPath()+"");
            return;
        }
        WebContext ctx = new WebContext(request, response, getServletContext(),request.getLocale());

        tEngine.process("WEB-INF/templatesTHY/main.html", ctx,response.getWriter());*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
