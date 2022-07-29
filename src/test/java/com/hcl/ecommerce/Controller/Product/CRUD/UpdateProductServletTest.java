package com.hcl.ecommerce.Controller.Product.CRUD;

import com.hcl.ecommerce.Dao.ProductDao;
import com.hcl.ecommerce.Model.Product;
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

class UpdateProductServletTest extends TestCase {
    @InjectMocks
    UpdateProductServlet updateProductServlet;
    @Mock
    ProductDao productDao;
    @Mock
    Product product;
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
        when(request.getParameter("id")).thenReturn("123");
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter("name")).thenReturn("John");
        when(request.getParameter("category")).thenReturn("johnsmith@yahoo.com");
        when(request.getParameter("price")).thenReturn("123");
        when(request.getParameter("image")).thenReturn("image.jpg");


    }

    private final static String path = "UpdateProductForm.jsp";
    @Test
    void testUpdateProductServlet() throws ServletException, IOException {


        updateProductServlet = new UpdateProductServlet();
        updateProductServlet.doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(response,atLeast(1)).sendRedirect("ProductList.jsp");
    }

}