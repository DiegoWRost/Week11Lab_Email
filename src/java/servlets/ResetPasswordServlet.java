/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.AccountService;

/**
 *
 * @author Diego Weidle Rost
 */
public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String uuid = request.getParameter("uuid");
        request.setAttribute("uuid", uuid);
        
        UserDB userDB = new UserDB();
        User user = userDB.getByUUID(uuid);
        request.setAttribute("user", user);
        
        if (uuid != null && !uuid.isEmpty() && user == null) {
             request.setAttribute("message", "Invalid UUID");
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String uuid = request.getParameter("uuid");
        String email = request.getParameter("email");
        String path = getServletContext().getRealPath("/WEB-INF");
        
        AccountService accServ = new AccountService();
        
        if (uuid != null) {
           accServ = new AccountService();
           String password = request.getParameter("password");
           if(accServ.changePassword(uuid, password)) {
               request.setAttribute("message", "Password reseted, please login");
               getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
               return;
           }
           request.setAttribute("message", "Failed to reset password. Try again");
           getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
           return;
        }

        if (email != null && !email.isEmpty()) {
            String url = request.getRequestURL().toString();
            accServ.resetPassword(email, path, url);
            request.setAttribute("message", "Please, check your e-mail for reset instructions");
        } else {
            request.setAttribute("message", "Enter a valid email");
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    }
}