package servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import controllers.CustomerController;
import controllers.PackageController;
import models.Customer;
import models.Package;

@WebServlet("/packages")
public class PackageServlet extends HttpServlet {
    private PackageController packageController = new PackageController();
    private CustomerController customerController = new CustomerController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                	List<Customer> customers = customerController.getAllCustomers();
                	request.setAttribute("customers", customers);
                    request.getRequestDispatcher("/new-package.jsp").forward(request, response);
                    break;
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Package packageToUpdate = packageController.getPackageById(id);
                    request.setAttribute("package", packageToUpdate);
                    List<Customer> c = customerController.getAllCustomers();
                	request.setAttribute("customers", c);
                    request.getRequestDispatcher("/edit-package-form.jsp").forward(request, response);
                    break;
                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    packageController.deletePackage(deleteId);
                    response.sendRedirect("packages?action=list");
                    break;
                case "list":
                default:
                    request.setAttribute("packages", packageController.getAllPackages());
                    request.getRequestDispatcher("/package-list.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error handling package actions", e);
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
                    Package newPackage = new Package(
                    		Integer.parseInt(request.getParameter("customerId")),
                        0, // PackageID is auto-generated
                        request.getParameter("packageName"),
                        request.getParameter("dataLimit"),
                        Double.parseDouble(request.getParameter("price")),
                        request.getParameter("status")
                    );
                    packageController.addPackage(newPackage);
                    response.sendRedirect("packages?action=list");
                    break;
                case "update":
                    Package updatedPackage = new Package(
                        Integer.parseInt(request.getParameter("customerId")),
                        Integer.parseInt(request.getParameter("id")),
                        request.getParameter("packageName"),
                        request.getParameter("dataLimit"),
                        Double.parseDouble(request.getParameter("price")),
                        request.getParameter("status")
                    );
                    packageController.updatePackage(updatedPackage);
                    response.sendRedirect("packages?action=list");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error posting package data", e);
        }
    }
}
