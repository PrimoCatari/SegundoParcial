package com.emergentes.dao;

import com.emergentes.modelo.Seminario;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeminarioDAOimpl extends ConexionDB implements SeminarioDAO {

    @Override
    public void insert(Seminario seminario) throws Exception {
        String sql = "insert into seminarios (titulo,fecha,cupo) values (?,?,?)";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, seminario.getTitulo());
        ps.setDate(2, seminario.getFecha());
        ps.setInt(3, seminario.getCupo());
        ps.executeUpdate();
        this.desconectar();
    }

    @Override
    public void update(Seminario seminario) throws Exception {
        String sql = "update seminarios set titulo=?,Fecha=?,cupo=? where id=?";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, seminario.getTitulo());
        ps.setDate(2, seminario.getFecha());
        ps.setInt(3, seminario.getCupo());
        ps.setInt(4, seminario.getId());
        ps.executeUpdate();
        this.desconectar();
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "delete from seminarios where id = ?";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        this.desconectar();
    }

    @Override
    public Seminario getById(int id) throws Exception {
        Seminario sem = new Seminario();
        String sql = "select * from seminarios where id = ?";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            sem.setId(rs.getInt("id"));
            sem.setTitulo(rs.getString("titulo"));
            sem.setFecha(rs.getDate("fecha"));
            sem.setCupo(rs.getInt("cupo"));
        }
        this.desconectar();
        return sem;
    }

    @Override
    public List<Seminario> getAll() throws Exception {
        List<Seminario> list = null;
        String sql = "select * from seminarios";
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        list = new ArrayList<Seminario>();
        while (rs.next()) {            
            Seminario sem = new Seminario();
            sem.setId(rs.getInt("id"));
            sem.setTitulo(rs.getString("titulo"));
            sem.setFecha(rs.getDate("fecha"));
            sem.setCupo(rs.getInt("cupo"));
            list.add(sem);
        }
        this.desconectar();
        return list;
    }
    
}
