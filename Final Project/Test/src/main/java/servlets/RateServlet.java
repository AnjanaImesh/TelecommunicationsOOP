package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import controllers.RateController;
import models.ServiceRating;

@WebServlet("/ratings")
public class RateServlet extends HttpServlet {
    private RateController rateController = new RateController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    request.getRequestDispatcher("/new-rating-form.jsp").forward(request, response);
                    break;
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    ServiceRating rating = rateController.getServiceRatingById(id);
                    request.setAttribute("rating", rating);
                    request.getRequestDispatcher("/edit-rating-form.jsp").forward(request, response);
                    break;
                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    rateController.deleteServiceRating(deleteId);
                    response.sendRedirect("ratings?action=list");
                    break;
                case "list":
                default:
                    request.setAttribute("ratings", rateController.getAllServiceRatings());
                    request.getRequestDispatcher("/rating-list.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error handling rating actions", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "add":
                    ServiceRating newRating = new ServiceRating(
                        request.getParameter("name"),
                        request.getParameter("email"),
                        request.getParameter("experience"),
                        request.getParameter("likes"),
                        request.getParameter("comment")
                    );
                    rateController.addServiceRating(newRating);
                    response.sendRedirect("ratings?action=list");
                    break;
                case "update":
                    ServiceRating updatedRating = new ServiceRating(
                        request.getParameter("name"),
                        request.getParameter("email"),
                        request.getParameter("experience"),
                        request.getParameter("likes"),
                        request.getParameter("comment")
                    );
                    updatedRating.setId(Integer.parseInt(request.getParameter("id")));
                    rateController.updateServiceRating(updatedRating);
                    response.sendRedirect("ratings?action=list");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error posting rating data", e);
        }
    }
}
