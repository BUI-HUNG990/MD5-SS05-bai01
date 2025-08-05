package com.example.md5ss51.controller.servlet;

import com.example.md5ss51.model.dao.CustomerDAO;
import com.example.md5ss51.model.entity.Customer;
import com.example.md5ss51.model.entity.Role;
import com.example.md5ss51.model.service.UserSession;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        System.out.println("Đăng nhập với username: [" + username + "]");
        System.out.println("Password: [" + password + "]");

        if (username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Không được để trống.");
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
            return;
        }

        Customer customer = customerDAO.findByUsernameAndPassword(username, password);
        System.out.println("Customer tìm thấy: " + (customer != null ? customer.getUsername() : "null"));

        if (customer != null) {
            UserSession.customer = customer;
            if (customer.getRole() == Role.ADMIN) {
                System.out.println("Login thành công: Admin");
                response.sendRedirect("admin.jsp");
            } else {
                System.out.println("Login thành công: User");
                response.sendRedirect("home.jsp");
            }
        } else {
            System.out.println("Login thất bại: Sai username hoặc password.");
            request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu.");
            request.getRequestDispatcher("/view/login.jsp").forward(request, response);
        }
    }
}
