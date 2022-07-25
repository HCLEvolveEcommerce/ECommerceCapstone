package com.hcl.ecommerce.Controller.User;

import com.hcl.ecommerce.Controller.User.CRUD.EditUFormServlet;
import com.hcl.ecommerce.Dao.UserDao;
import com.hcl.ecommerce.Model.User;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginServletTest extends TestCase {
    @InjectMocks
    LoginServlet loginServlet;
    @Mock
    UserDao userDao;
    @Mock
    User user;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    HttpSession session;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        userDao = mock(UserDao.class);

        when(request.getSession(true)).thenReturn(session);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testLoginServletTrueAdmin() throws IOException, ServletException {
        when(request.getParameter("login-email")).thenReturn("dsikes313@gmail.com");
        when(request.getParameter("login-password")).thenReturn("BeastMode");
        when(user.getUsertype()).thenReturn("Admin");

        loginServlet = new LoginServlet();
        loginServlet.doPost(request,response);

        verify(response,atLeast(1)).sendRedirect("UserList.jsp");
    }

    @Test
    void testLoginServletTrueClient() throws IOException, ServletException {
        when(request.getParameter("login-email")).thenReturn("jasonjohn333@yahoo.com");
        when(request.getParameter("login-password")).thenReturn("jasonjj33");
        when(user.getUsertype()).thenReturn("Client");

        loginServlet = new LoginServlet();
        loginServlet.doPost(request,response);

        verify(response,atLeast(1)).sendRedirect("index.jsp");
    }

    @Test
    void testLoginServletFalse() throws IOException, ServletException {
        when(request.getParameter("login-email")).thenReturn("asf");
        when(request.getParameter("login-password")).thenReturn("asfs");
        when(user.getUsertype()).thenReturn("Client");

        loginServlet = new LoginServlet();
        loginServlet.doPost(request,response);

        verify(response,atLeast(1)).sendRedirect("login.jsp");
    }

}