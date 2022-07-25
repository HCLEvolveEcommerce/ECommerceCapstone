package com.hcl.ecommerce.Controller.Product.CRUD;

import com.hcl.ecommerce.Dao.ProductDao;
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

class DeleteProductServletTest extends TestCase {
    @InjectMocks
    DeleteProductServlet deleteProductServlet;
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

    }

    private final static String path = "ProductList.jsp";
    private int id;
    @Test
    void testDeleteProductServlet() throws ServletException, IOException {


        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn("1234");
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);

        deleteProductServlet = new DeleteProductServlet();
        deleteProductServlet.doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(response,atLeast(1)).sendRedirect(path);

    }
}