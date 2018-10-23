package twitter.web;

import twitter.model.Dashboard;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class TwitterServlet extends HttpServlet {

    private final Dashboard dashboard;

    public TwitterServlet(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tweets", dashboard.load().collect(Collectors.toList()));
        req.getRequestDispatcher("twitter.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dashboard.create(req.getParameter("message"), req.getParameter("author"));
        resp.sendRedirect("/twitter");
    }
}
