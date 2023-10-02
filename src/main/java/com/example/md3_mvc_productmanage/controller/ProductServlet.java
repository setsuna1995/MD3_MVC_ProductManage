package com.example.md3_mvc_productmanage.controller;

import com.example.md3_mvc_productmanage.model.Product;
import com.example.md3_mvc_productmanage.service.ProductService;
import com.example.md3_mvc_productmanage.service.ProductServiceImpl;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    private final ProductServiceImpl productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "view":
                viewProduct(req, resp);
                break;
            case "create":
                creatProduct(req, resp);
                break;
            case "edit":
            default:
                listProduct(req, resp);
                break;
        }
    }
    private void listProduct(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = this.productService.findAll();
        request.setAttribute("product", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void viewProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.productService.findByID(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("product", product);
            dispatcher = request.getRequestDispatcher("product/view.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                creatProduct(req, resp);
                break;
            default:
                break;
        }
    }

    private void creatProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        int id = (int) (Math.random() * 10000);
        Product product = new Product(id, name, price);
        this.productService.save(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        request.setAttribute("message", "New product was created");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

