
//package com.hcl.ecommerce.Controller.User.CRUD;
//
//import com.hcl.ecommerce.Dao.UserDao;
//import com.hcl.ecommerce.Model.User;
//import junit.framework.TestCase;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class EditUFormServletTest extends TestCase {
////    @InjectMocks
////    EditUFormServlet editUFormServlet;
//    @Mock
//    UserDao userDao;
//    @Mock
//    User user;
//    final int USER_ID = 324234;
//    @BeforeEach
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//
//
//
//        userDao = mock(UserDao.class);
//
//    }
//
//    private final static String path = "UpdateUserForm.jsp";
//    @Test
//    void testEditUFormReturnPathAdmin() throws ServletException, IOException {
//        HttpServletRequest  request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
//        HttpSession session = mock(HttpSession.class);
//
//        when(user.getUsertype()).thenReturn("Admin");
//        when(request.getSession()).thenReturn(session);
//        when(request.getParameter("id")).thenReturn("8");
//        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
//
//
//        editUFormServlet = new EditUFormServlet(userDao, user);
//        editUFormServlet.doGet(request, response);
//
//        verify(request, atLeast(1)).getParameter("id");
//
//    }
//}
//=======
//package com.hcl.ecommerce.Controller.User.CRUD;
//
//import com.hcl.ecommerce.Dao.UserDao;
//import com.hcl.ecommerce.Model.User;
//import junit.framework.TestCase;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class EditUFormServletTest extends TestCase {
//    @InjectMocks
//    EditUFormServlet editUFormServlet;
//    @Mock
//    UserDao userDao;
//    @Mock
//    User user;
//    @Mock
//    HttpServletRequest  request;
//    @Mock
//    HttpServletResponse response;
//    @Mock
//    RequestDispatcher dispatcher;
//    @Mock
//    HttpSession session;
//    @BeforeEach
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//        request = mock(HttpServletRequest.class);
//        response = mock(HttpServletResponse.class);
//        dispatcher = mock(RequestDispatcher.class);
//        session = mock(HttpSession.class);
//        userDao = mock(UserDao.class);
//
//        when(request.getSession()).thenReturn(session);
//        when(request.getParameter("id")).thenReturn("185");
//    }
//
//    @Test
//    void testEditUFormReturnPathAdmin() throws ServletException, IOException {
//
//
//        when(user.getUsertype()).thenReturn("Admin");
//        when(request.getRequestDispatcher("UpdateUserForm.jsp")).thenReturn(dispatcher);
//
//        editUFormServlet = new EditUFormServlet(userDao, user);
//        editUFormServlet.doGet(request, response);
//
//        verify(request, atLeast(1)).getParameter("id");
//        verify(request, atLeast(1)).getRequestDispatcher("UpdateUserForm.jsp");
//
//     }
//
//    @Test
//    void testEditUFormReturnPathClient() throws ServletException, IOException {
//
//
//        when(user.getUsertype()).thenReturn("Client");
//        when(request.getRequestDispatcher("ClientUpdateForm.jsp")).thenReturn(dispatcher);
//
//        editUFormServlet = new EditUFormServlet(userDao, user);
//        editUFormServlet.doGet(request, response);
//
//        verify(request, atLeast(1)).getParameter("id");
//        verify(request, atLeast(1)).getRequestDispatcher("ClientUpdateForm.jsp");
//    }
//}
