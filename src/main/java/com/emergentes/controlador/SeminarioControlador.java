package com.emergentes.controlador;

import com.emergentes.dao.SeminarioDAO;
import com.emergentes.dao.SeminarioDAOimpl;
import com.emergentes.modelo.Seminario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SeminarioControlador", urlPatterns = {"/SeminarioControlador"})
public class SeminarioControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            SeminarioDAO dao = new SeminarioDAOimpl();
            Seminario sem = new Seminario();
            int id;
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";            
            switch (action) {
                case "add":
                    request.setAttribute("seminario", sem);
                    request.getRequestDispatcher("frmseminario.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    sem = dao.getById(id);
                    request.setAttribute("seminario", sem);
                    request.getRequestDispatcher("frmseminario.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("SeminarioControlador");
                    break;
                case "view":
                    List<Seminario> lista = dao.getAll();
                    request.setAttribute("seminarios", lista);
                    request.getRequestDispatcher("seminarios.jsp").forward(request, response);
                    break;
                    
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String fecha = request.getParameter("fecha");
        int cupo = Integer.parseInt(request.getParameter("cupo"));
        Seminario sem = new Seminario();
        sem.setId(id);
        sem.setTitulo(titulo);
        sem.setFecha(convierteFecha(fecha));
        sem.setCupo(cupo);
        SeminarioDAO dao = new SeminarioDAOimpl();
        if (id == 0) {
            try {
                // Nuevo
                dao.insert(sem);
            } catch (Exception ex) {
                System.out.println("Error al insertar: " + ex.getMessage());
            }
        } else {
            try {
                // Editar
                dao.update(sem);
            } catch (Exception ex) {
                System.out.println("Error al actualizar: " + ex.getMessage());
            }
        }
        response.sendRedirect("SeminarioControlador");

    }
    
        public Date convierteFecha(String fecha){
        Date fechaBD = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaTMP;
        try {
            fechaTMP = formato.parse(fecha);
            fechaBD = new Date(fechaTMP.getTime());
        } catch (ParseException ex) {
            System.out.println("Error en la conversion de fecha: " + ex.getMessage());
        }
        
        return fechaBD;
    }

}
