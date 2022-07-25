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

class SelectUserServletTest extends TestCase {
    @InjectMocks
    SelectUserServlet selectUserServlet;
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

    }

    private final static String path = "UpdateUserForm.jsp";
    @Test
    void testSelectUserServlet() throws ServletException, IOException {


        when(user.getUsertype()).thenReturn("Admin");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn("186");
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);


        selectUserServlet = new SelectUserServlet();
        selectUserServlet.doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");
    }
}