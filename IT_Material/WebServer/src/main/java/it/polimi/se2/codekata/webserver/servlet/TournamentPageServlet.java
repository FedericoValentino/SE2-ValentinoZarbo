package it.polimi.se2.codekata.webserver.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
//param ?tid=id
@WebServlet(name = "TournamentPageServlet", value = "/TournamentPageServlet")
public class TournamentPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ss= request.getSession();
        if(ss.isNew()) {
            return;
        }
        String tournamentId= request.getParameter("tid");
        ss.setAttribute("tid",tournamentId);

        response.setStatus(200);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");



        request.getRequestDispatcher("/WEB-INF/page-jsp/tournament.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
