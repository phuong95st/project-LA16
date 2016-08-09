/**
 * Copyright(C) 2016 Luvina Software Company
 * LogoutServlet.java, Aug 7, 2016 PhuongNH
 */
package net.luvina.manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.luvina.manageuser.utils.Constant;

/**
 * @author PhuongNH
 *
 */
public class LogoutServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet{
	static final long serialVersionUID = 1L;

    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
    }

    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Remove session
        HttpSession session = request.getSession();
        session.removeAttribute("loginId");
        session.removeAttribute("password");

        session.removeAttribute("key_search_full_name");
        session.removeAttribute("key_search_group_id");

        //Goto page login
        RequestDispatcher req = request.getRequestDispatcher(Constant.ADM001);
        req.forward(request, response);
    }

    /*
     * (non-Java-doc)
     *
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
     * HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
