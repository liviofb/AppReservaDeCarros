/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Cliente;
import model.bean.Reserva;
import model.bean.Veiculo;

/**
 *
 * @author livio
 */
public class ReservaDAO {
    
      public void create(Reserva r){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       
        try {
            //Lembrar que no valor em idagencia é necessário refgerenciar corretamente a agencia no qual o veiculo está sendo registrado.
            
            stmt = con.prepareStatement("INSERT INTO reserva VALUES(default, ?, ?, ?)");
            stmt.setString(1, r.getDataRetirada());
            stmt.setString(2, r.getDataEntrega());
            stmt.setFloat(3, r.getValor());
            stmt.setInt(4, r.getCodAgencia());
            stmt.setInt(5, r.getCodVeiculo());
            stmt.setString(6, r.getNomeCliente());
           
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
  
    }
      
    public List<Reserva> read(){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       
       List<Reserva> reservas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM reserva");
            rs= stmt.executeQuery();
            
            while (rs.next()){
                Reserva reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("idreserva"));
                reserva.setDataRetirada(rs.getString("data_retirada"));
                reserva.setDataEntrega(rs.getString("idreserva"));
                reserva.setValor(rs.getInt("valor"));
                
                reservas.add(reserva);     
            }
            } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return reservas;
    }
      
    public void update(Reserva r){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
       
        try {
            //Lembrar que no valor em idagencia é necessário refgerenciar corretamente a agencia no qual o veiculo está sendo registrado.
            stmt = con.prepareStatement("UPDATE reserva SET data_retirada = ?, data_entrega = ?, velor = ? WHERE idreserva = ?");
            stmt.setString(1, r.getDataRetirada());
            stmt.setString(2, r.getDataEntrega());
            stmt.setFloat(3, r.getValor());
            stmt.setInt(4, r.getIdReserva());

            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }  
     
     public void remove(Reserva r){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       
        try {
            //Lembrar que no valor em idagencia é necessário refgerenciar corretamente a agencia no qual o veiculo está sendo registrado.
            stmt = con.prepareStatement("DELETE FROM reserva WHERE idreserva = ?");
            stmt.setInt(1, r.getIdReserva());
        
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
     public List<Reserva> buscaB(String busc){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       
       List<Reserva> reservas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM reserva WHERE data_retirada LIKE ?");
            stmt.setString(1, "%" + busc+ "%");
            rs= stmt.executeQuery();
            
            while (rs.next()){
               Reserva reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("idreserva"));
                reserva.setDataRetirada(rs.getString("data_retirada"));
                reserva.setDataEntrega(rs.getString("idreserva"));
                reserva.setValor(rs.getInt("valor"));         
                reservas.add(reserva);
                
            }
            } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return reservas;
     
    }
     
    public static void ListarR(String dato){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;

          try {
              stmt = con.prepareStatement("select reserva.idreserva, reserva.data_retirada, reserva.data_entrega,\n" +
                                            "reserva.valor, cliente.nome_cliente, agencia.idagencia, veiculo.idveiculo \n" +
                                            "from reserva\n" +
                                            "inner join cliente on reserva.cliente_idcliente = cliente.idcliente\n" +
                                            "inner join agencia on reserva.agencia_idagencia = agencia.idagencia\n" +
                                            "inner join veiculo on reserva.veiculo_idveiculo = veiculo.idveiculo;");
          
          
          
          } catch (SQLException ex) {
              Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
          }                   
    }
}