package com.hcl.ecommerce.Controller.Product.CRUD;

import com.hcl.ecommerce.Controller.Product.CRUD.DeleteProductServlet;
import com.hcl.ecommerce.Controller.Product.CRUD.InsertProductServlet;
import com.hcl.ecommerce.Dao.ProductDao;
import com.hcl.ecommerce.Model.DbCon;
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
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InsertProductServletTest extends TestCase {
    @InjectMocks
    DeleteProductServlet deleteProductServlet;
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

    }

    private final static String path = "ProductList.jsp";
    private int id;
    @Test
    void testInsertProductServletTrue() throws ServletException, IOException, SQLException, ClassNotFoundException {

        InsertProductServlet insertProductServlet = new InsertProductServlet();
        when(request.getParameter("name")).thenReturn("John");
        when(request.getParameter("category")).thenReturn("johnsmith@yahoo.com");
        when(request.getParameter("price")).thenReturn("123");
        when(request.getParameter("image")).thenReturn("image.jpg");


        insertProductServlet.doGet(request, response);
        verify(response,atLeast(1)).sendRedirect(path);
        deleteProduct("John");
    }

    public void deleteProduct(String name) throws SQLException, ClassNotFoundException {
        ProductDao pDao = new ProductDao(DbCon.getConnection());
        Product product = pDao.getProductByName(name);
        pDao.deleteProducts(product.getId());
    }

}