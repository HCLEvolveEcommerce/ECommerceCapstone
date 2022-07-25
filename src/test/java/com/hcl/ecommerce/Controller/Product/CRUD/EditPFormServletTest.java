package com.hcl.ecommerce.Controller.Product.CRUD;

import com.hcl.ecommerce.Controller.User.CRUD.EditUFormServlet;
import com.hcl.ecommerce.Dao.ProductDao;
import com.hcl.ecommerce.Model.Product;
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

class EditPFormServletTest extends TestCase {
    @InjectMocks
    EditPFormServlet editPFormServlet;
    @Mock
    ProductDao productDao;
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
        productDao = mock(ProductDao.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn("4");
    }

    @Test
    void testEditPFormReturnPath() throws ServletException, IOException {


        when(request.getRequestDispatcher("UpdateProductForm.jsp")).thenReturn(dispatcher);

        editPFormServlet = new EditPFormServlet();
        editPFormServlet.doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getRequestDispatcher("UpdateProductForm.jsp");

    }

}