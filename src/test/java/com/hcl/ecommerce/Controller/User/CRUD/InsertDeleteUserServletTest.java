package com.hcl.ecommerce.Controller.User.CRUD;

import com.hcl.ecommerce.Dao.UserDao;
import com.hcl.ecommerce.Model.DbCon;
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
import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

class InsertDeleteUserServletTest extends TestCase {
    @InjectMocks
    DeleteUserServlet deleteUserServlet;
    @Mock
    UserDao userDao;
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

    private final static String path = "UserList.jsp";
    private int id;
    @Test
    void testDeleteUserServletFalse() throws ServletException, IOException {


        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn("8");
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);

        deleteUserServlet = new DeleteUserServlet(userDao);
        deleteUserServlet.doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(response,atLeast(1)).sendRedirect(path);
        assertFalse(deleteUserServlet.isDeleted);

    }

    @Test
    void testInsertUserServletTrue() throws ServletException, IOException, SQLException, ClassNotFoundException {
        UserDao uDao = new UserDao(DbCon.getConnection());

        InsertUserServlet insertUserServlet = new InsertUserServlet();
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

        insertUserServlet.doGet(request, response);

        int id = uDao.userLogin("johnsmith@yahoo.com","johnsmith").getId();
        testDeleteUserServletTrue(id);
    }
    //Is called by testInsertUserServletTrue
    public void testDeleteUserServletTrue(int id) throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn(String.valueOf(id));


        deleteUserServlet = new DeleteUserServlet(userDao);
        deleteUserServlet.doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");
        assertTrue(deleteUserServlet.isDeleted);
    }
}