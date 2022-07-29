package com.hcl.ecommerce.Controller.User;

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

import static org.mockito.Mockito.*;

class LogoutServletTest extends TestCase {
    @InjectMocks
    LogoutServlet logoutServlet;
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
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        userDao = mock(UserDao.class);

        when(request.getSession()).thenReturn(session);
        when(session.getId()).thenReturn("123");
    }

    @Test
    public void testLogoutServletTrue() throws ServletException, IOException {
        when(session.getAttribute("auth")).thenReturn("dummyUser");

        logoutServlet = new LogoutServlet();
        logoutServlet.doGet(request, response);

        verify(response,atLeast(1)).sendRedirect("login.jsp");
    }

    @Test
    public void testLogoutServletFalse() throws ServletException, IOException {
        when(session.getAttribute("auth")).thenReturn(null);

        logoutServlet = new LogoutServlet();
        logoutServlet.doGet(request, response);

        verify(response,atLeast(1)).sendRedirect("index.jsp");
    }


}