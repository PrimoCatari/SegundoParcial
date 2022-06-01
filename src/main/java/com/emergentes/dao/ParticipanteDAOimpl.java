
package com.emergentes.dao;

import com.emergentes.modelo.Participante;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ParticipanteDAOimpl extends ConexionDB implements ParticipanteDAO {

    @Override
    public void insert(Participante participante) throws Exception {
        String sql = "insert into participantes (apellidos, nombres,seminario_id,confirmado) values (?,?,?,?)";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, participante.getApellidos());
        ps.setString(2, participante.getNombres());
        ps.setInt(3, participante.getSeminario_id());
        ps.setInt(4, participante.getConfirmado());
        ps.executeUpdate();
        this.desconectar();
    }

    @Override
    public void update(Participante participante) throws Exception {
        String sql = "update participantes set apellidos=?,nombres=?,seminario_id=?, confirmado=? where id=?";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, participante.getApellidos());
        ps.setString(1, participante.getNombres());
        ps.setInt(2, participante.getSeminario_id());
        ps.setInt(3, participante.getConfirmado());
        ps.setInt(4, participante.getId());
        ps.executeUpdate();
        this.desconectar();
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "delete from participantes where id = ?";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        this.desconectar();
    }

    @Override
    public Participante getById(int id) throws Exception {
        Participante par = new Participante();
        String sql = "select * from participantes where id = ?";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            par.setId(rs.getInt("id"));
            par.setSeminario_id(rs.getInt("seminario_id"));
            par.setApellidos(rs.getString("apellidos"));
            par.setNombres(rs.getString("nombres"));
            par.setConfirmado(rs.getInt("confirmado"));
        }
        this.desconectar();
        return par;
    }

    @Override
    public List<Participante> getAll() throws Exception {
        List<Participante> list = null;
        String sql = "SELECT p.*,s.titulo as seminario from participantes p ";
        sql += "left join seminarios s on p.seminario_id = s.id ";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        list = new ArrayList<Participante>();
        while (rs.next()) {            
            Participante par = new Participante();
            par.setId(rs.getInt("id"));
            par.setSeminario_id(rs.getInt("seminario_id"));
            par.setApellidos(rs.getString("apellidos"));
            par.setNombres(rs.getString("nombres"));
            par.setConfirmado(rs.getInt("confirmado"));
            par.setSeminario(rs.getString("seminario"));
            
            list.add(par);
        }
        this.desconectar();
        return list;
    }
    
}
