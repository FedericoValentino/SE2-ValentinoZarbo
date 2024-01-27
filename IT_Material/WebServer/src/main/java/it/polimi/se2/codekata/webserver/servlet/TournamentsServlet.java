package it.polimi.se2.codekata.webserver.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TournamentsServlet", value = "/TournamentsServlet")
public class TournamentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        /*if (session.isNew()){
            response.sendRedirect(getServletContext().getContextPath()+"");
            return;
        }*/
        HttpSession ss= request.getSession();
        if(!ss.isNew()) {
            ss.invalidate();
            ss=request.getSession();
        }
        ss.setAttribute("isStud", true);
        response.getWriter().append("ok");
        response.setStatus(200);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");



        request.getRequestDispatcher("/WEB-INF/page-jsp/MainPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
