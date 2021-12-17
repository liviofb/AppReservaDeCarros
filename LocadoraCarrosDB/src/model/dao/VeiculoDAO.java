/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import connection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Veiculo;
import model.bean.Agencia;
/**
 *
 * @author livio
 */
public class VeiculoDAO {
    
    public void create(Veiculo v){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       
        try {
            
            stmt = con.prepareStatement("INSERT INTO veiculo VALUES(default,?,?,?,?,?,?,?,?)");
            stmt.setString(1, v.getPlaca());
            stmt.setString(2, v.getModelo());
            stmt.setString(3, v.getCor());
            stmt.setString(4, v.getAno());
            stmt.setFloat(5, v.getValor_dia());
            stmt.setInt(6, v.getKm());
            stmt.setString(7, v.getCombustivel());
            stmt.setInt(8, v.getCodAgencia());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }  
    }  
    
    public List<Veiculo> read(){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       
       List<Veiculo> veiculos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM veiculo");
            rs= stmt.executeQuery();
            
            while (rs.next()){
                Veiculo veiculo = new Veiculo();
                Agencia agencia = new Agencia();
                veiculo.setId(rs.getInt("idveiculo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setAno(rs.getString("ano"));
                veiculo.setValor_dia(rs.getFloat("valor_dia"));
                veiculo.setKm(rs.getInt("km"));
                veiculo.setCombustivel(rs.getString("combustivel"));
                agencia.getIdAgencia();
                veiculos.add(veiculo);
                
            }
            } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return veiculos;
    }   
        public void update(Veiculo v){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
       
        try {
            //Lembrar que no valor em idagencia é necessário refgerenciar corretamente a agencia no qual o veiculo está sendo registrado.
            stmt = con.prepareStatement("UPDATE veiculo SET placa = ?, modelo = ?, cor = ?, ano = ?, valor_dia = ?, km = ?, combustivel = ?, agencia_idagencia = ?  WHERE idveiculo = ?");
            stmt.setString(1, v.getPlaca());
            stmt.setString(2, v.getModelo());
            stmt.setString(3, v.getCor());
            stmt.setString(4, v.getAno());
            stmt.setFloat(5, v.getValor_dia());
            stmt.setInt(6, v.getKm());
            stmt.setString(7, v.getCombustivel());
            stmt.setInt(8, v.getCodAgencia());
            stmt.setInt(9, v.getId());
            
        
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
       public void remove(Veiculo v){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       
        try {
            //Lembrar que no valor em idagencia é necessário refgerenciar corretamente a agencia no qual o veiculo está sendo registrado.
            stmt = con.prepareStatement("DELETE FROM veiculo WHERE idveiculo = ?");
            stmt.setInt(1, v.getId());
        
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
       
       public List<Veiculo> buscaB(String busc){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       
       List<Veiculo> veiculos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM veiculo WHERE modelo LIKE ?");
            stmt.setString(1, "%" + busc+ "%");
            rs= stmt.executeQuery();
            
            while (rs.next()){
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("idveiculo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setAno(rs.getString("ano"));
                veiculo.setValor_dia(rs.getFloat("valor_dia"));
                veiculo.setKm(rs.getInt("km"));
                veiculo.setCombustivel(rs.getString("combustivel"));
                veiculos.add(veiculo);
                
            }
            } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return veiculos;
    }
       
       public ResultSet listarAgencia(){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       
        try {
            stmt = con.prepareStatement("SELECT * FROM agencia ORDER BY idagencia");
            return stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       }
}
       
