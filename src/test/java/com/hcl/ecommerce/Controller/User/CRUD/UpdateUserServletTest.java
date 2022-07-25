package com.hcl.ecommerce.Controller.User.CRUD;

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
import static org.mockito.Mockito.atLeast;

class UpdateUserServletTest extends TestCase {
    @InjectMocks
    UpdateUserServlet updateUserServlet;
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
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn("186");
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter("firstname")).thenReturn("John");
        when(request.getParameter("email")).thenReturn("johnsmith@yahoo.com");
        when(request.getParameter("password")).thenReturn("johnsmith");

        when(request.getParameter("address")).thenReturn("1824 Auburn Dr.");
        when(request.getParameter("city")).thenReturn("Carrollton");
        when(request.getParameter("zip")).thenReturn("75007");
        when(request.getParameter("state")).thenReturn("tx");
        when(request.getParameter("country")).thenReturn("usa");
        when(request.getParameter("lastname")).thenReturn("Smith");
        when(request.getParameter("phonenumber")).thenReturn("42932123");
    }

    private final static String path = "UpdateUserForm.jsp";
    @Test
    void testUpdateUserServletAdmin() throws ServletException, IOException {

        when(request.getParameter("usertype")).thenReturn("Admin");

        updateUserServlet = new UpdateUserServlet();
        updateUserServlet.doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(response,atLeast(1)).sendRedirect("UserList.jsp");
    }

    @Test
    void testUpdateUserServletClient() throws ServletException, IOException {

        when(request.getParameter("usertype")).thenReturn("Client");

        updateUserServlet = new UpdateUserServlet();
        updateUserServlet.doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(response,atLeast(1)).sendRedirect("AccountPage.jsp");
    }
}