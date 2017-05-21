package com.omidbiz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public UploadServlet()
    {
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String header = request.getHeader("x_filename");
        if(header != null && header.trim().length() > 0)
        {
            ServletInputStream inputStream = request.getInputStream();
            byte[] byteArray = IOUtils.toByteArray(inputStream);
            
            Path path = Paths.get(System.getProperty("user.home")+File.separator + header);
            Files.deleteIfExists(path);
            Files.createFile(path);
            Files.write(path, byteArray);
        }
    }


}
