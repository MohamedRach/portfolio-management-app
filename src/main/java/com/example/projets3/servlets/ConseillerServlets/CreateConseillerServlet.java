package com.example.projets3.servlets.ConseillerServlets;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.example.projets3.bean.ConseillerBean;
import com.example.projets3.dao.ConseillerDao.ConseillerDao;
import com.example.projets3.dao.ConseillerDao.ConseillerDaoImpl;
import com.example.projets3.dao.DAOException;
import com.example.projets3.dao.daoFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


@MultipartConfig
public class CreateConseillerServlet extends HttpServlet {
    private ConseillerDao conseillerDao;
    private static final String WEB_CONTENT_DIR   = "C:/Users/User0/Desktop/PROJET S3/portfolio-management-app/src/main/webapp";
    private static final String BLOGS_IMAGES_DIR = "/photos/";
    public void init() {
        daoFactory daoFactory = com.example.projets3.dao.daoFactory.getInstance();
        this.conseillerDao = new ConseillerDaoImpl(daoFactory);
    }
    private static String getMeidaExt(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            System.out.println("Content-Disposition Header: " + cd); // Ajout de cette ligne pour débogue
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");

                return filename.substring(filename.lastIndexOf('.') + 1);
            }
        }
        return null;
    }

    private static String savePart(Part image) {
        long id = System.currentTimeMillis();
        String ext = getMeidaExt(image);
        String uploadPath = WEB_CONTENT_DIR + BLOGS_IMAGES_DIR + id + "." + ext;

        try (InputStream input = image.getInputStream();
             OutputStream output = new FileOutputStream(uploadPath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return BLOGS_IMAGES_DIR + id + "." + ext;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Forward the request to the JSP page for creating a new conseiller
        RequestDispatcher dispatcher = request.getRequestDispatcher("/createConseiller.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Extract conseiller data from the form
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");

        Part image = request.getPart("image");
        String imageLink = savePart(image);





        // Create a new ConseillerBean with the extracted data
        ConseillerBean newConseiller = new ConseillerBean();
        newConseiller.setNom(nom);
        newConseiller.setPrenom(prenom);
        newConseiller.setEmail(email);
        newConseiller.setPassword("DEFAULTPASSWORD");
        newConseiller.setImageLink(imageLink);


        try {
            // Add the new conseiller to the database
            conseillerDao.createConseiller(newConseiller);

            // Redirect to the /conseillers page after successful creation
            response.sendRedirect(request.getContextPath() + "/conseillerList");
        } catch (DAOException e) {
            // Handle errors (you might want to display an error message or log the exception)
            e.printStackTrace();
            response.getWriter().println("Error creating conseiller");
        }
    }
}
