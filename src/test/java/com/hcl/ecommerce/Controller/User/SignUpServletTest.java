package com.hcl.ecommerce.Controller.User;

import com.hcl.ecommerce.Dao.UserDao;
import com.hcl.ecommerce.Model.DbCon;
import com.hcl.ecommerce.Model.User;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.*;
import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignUpServletTest extends TestCase {
    @InjectMocks
    SignUpServlet signUpServlet;
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
    @Mock
    ServletContext servletContext;
    @Mock
    ServletConfig servletConfig;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        userDao = mock(UserDao.class);
        servletContext = mock(ServletContext.class);
        servletConfig = mock(ServletConfig.class);
        GenericServlet genericServlet = mock(GenericServlet.class);

        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/email-signup")).thenReturn(dispatcher);

        when(request.getParameter("firstname")).thenReturn("John");
        when(request.getParameter("email")).thenReturn("johnsmith@yahoo.com");
        when(request.getParameter("password")).thenReturn("johnsmith");
        when(request.getParameter("usertype")).thenReturn("Admin");
        when(request.getParameter("address")).thenReturn("1824 Auburn Dr.");
        when(request.getParameter("city")).thenReturn("Carrollton");
        when(request.getParameter("zip")).thenReturn("75007");
        when(request.getParameter("state")).thenReturn("tx");
        when(request.getParameter("country")).thenReturn("usa");
        when(request.getParameter("lastname")).thenReturn("Smith");
        when(request.getParameter("phonenumber")).thenReturn("42932123");
    }

    @Test
    public void testSignUpServlet() throws ServletException, IOException, SQLException, ClassNotFoundException {
        signUpServlet = new SignUpServlet();
        signUpServlet.init(servletConfig);
        signUpServlet.doGet(request,response);

        verify(dispatcher,atLeast(1)).forward(request,response);
        deleteUser();
    }

    public void deleteUser() throws SQLException, ClassNotFoundException {
        UserDao uDao = new UserDao(DbCon.getConnection());
        int id = uDao.userLogin("johnsmith@yahoo.com","johnsmith").getId();
        uDao.deleteUser(id);
    }


}