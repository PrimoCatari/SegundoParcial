package com.emergentes.controlador;

import com.emergentes.dao.SeminarioDAO;
import com.emergentes.dao.SeminarioDAOimpl;
import com.emergentes.dao.ParticipanteDAO;
import com.emergentes.dao.ParticipanteDAOimpl;
import com.emergentes.modelo.Seminario;
import com.emergentes.modelo.Participante;
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

@WebServlet(urlPatterns = {"/ParticipanteControlador"})
public class ParticipanteControlador extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ParticipanteDAO dao = new ParticipanteDAOimpl();
            SeminarioDAO daoSeminario = new SeminarioDAOimpl();
            int id;
            List<Seminario> lista_seminarios = null;
            Participante participante = new Participante();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";            
            switch (action) {
                case "add":
                    //
                    lista_seminarios = daoSeminario.getAll();
                    request.setAttribute("lista_seminarios", lista_seminarios);
                    request.setAttribute("participante", participante);
                    request.getRequestDispatcher("frmparticipante.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    participante = dao.getById(id);
                    lista_seminarios = daoSeminario.getAll();
                    request.setAttribute("lista_seminarios", lista_seminarios);
                    request.setAttribute("participante", participante);
                    request.getRequestDispatcher("frmparticipante.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("ParticipanteControlador");
                    break;
                case "view":
                    List<Participante> lista = dao.getAll();
                    request.setAttribute("participantes", lista);
                    request.getRequestDispatcher("participantes.jsp").forward(request, response);
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
        int seminario_id = Integer.parseInt(request.getParameter("seminario_id"));
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        int confirmado = Integer.parseInt(request.getParameter("confirmado"));
        
        Participante participante = new Participante();
        
        participante.setId(id);
        participante.setSeminario_id(seminario_id);
        participante.setNombres(nombres);
        participante.setApellidos(apellidos);
        participante.setConfirmado(confirmado);
        
        ParticipanteDAO dao = new ParticipanteDAOimpl();
        if (id == 0) {
            try {
                // nuevo
                dao.insert(participante);
            } catch (Exception ex) {
                System.out.println("Error al insertar: " + ex.getMessage());
            }
        } else {
            try {
                // editar
                dao.update(participante);
            } catch (Exception ex) {
                System.out.println("Error al modificar: " + ex.getMessage());
            }
        }
        response.sendRedirect("ParticipanteControlador");
        
    }
    



}
