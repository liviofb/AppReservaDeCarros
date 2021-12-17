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
import model.bean.Cliente;

/**
 *
 * @author livio
 */
public class ClienteDAO {
    
     public void create(Cliente c){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       
        try {
            //Lembrar que no valor em idagencia é necessário refgerenciar corretamente a agencia no qual o veiculo está sendo registrado.
            stmt = con.prepareStatement("INSERT INTO cliente VALUES(default, ?, ?, ?, ?, ?,  ?, ?, ?,?)");
            stmt.setString(1, c.getNomeCliente());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getRua());
            stmt.setString(5, c.getBairro());
            stmt.setString(6, c.getCidade());
            stmt.setString(7, c.getUf());
            stmt.setString(8, c.getCep());
            stmt.setString(9, c.getTelefone());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
  
    }
     
      public List<Cliente> read(){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       
       List<Cliente> clienteS = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs= stmt.executeQuery();
            
            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNomeCliente(rs.getString("nome_cliente"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email_cliente"));
                cliente.setRua(rs.getString("rua"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));
                cliente.setCep(rs.getString("cep"));
                cliente.setTelefone(rs.getString("celular"));
                clienteS.add(cliente);     
            }
            } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clienteS;
    }
      
    public void update(Cliente c){
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
       
        try {
            //Lembrar que no valor em idagencia é necessário refgerenciar corretamente a agencia no qual o veiculo está sendo registrado.
            stmt = con.prepareStatement("UPDATE cliente SET nome_cliente = ?, cpf = ?, email_cliente = ?, rua = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, celular = ? WHERE idcliente = ?");
            stmt.setString(1, c.getNomeCliente());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getRua());
            stmt.setString(5, c.getBairro());
            stmt.setString(6, c.getCidade());
            stmt.setString(7, c.getUf());
            stmt.setString(8, c.getCep());
            stmt.setString(9, c.getTelefone());
            stmt.setInt(10, c.getIdCliente());
        
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
    public void remove (Cliente c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
       
        try {
            //Lembrar que no valor em idagencia é necessário refgerenciar corretamente a agencia no qual o veiculo está sendo registrado.
            stmt = con.prepareStatement("DELETE FROM cliente WHERE idcliente = ?");
            stmt.setInt(1, c.getIdCliente());
   
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
     public List<Cliente> buscaB(String busc){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       ResultSet rs = null;
       
       List<Cliente> clienteS = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE nome_cliente LIKE ?");
            stmt.setString(1,"%" +  busc + "%");
            rs= stmt.executeQuery();
            
            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNomeCliente(rs.getString("nome_cliente"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email_cliente"));
                cliente.setRua(rs.getString("rua"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));
                cliente.setCep(rs.getString("cep"));
                cliente.setTelefone(rs.getString("celular"));
                clienteS.add(cliente);     
            }
            } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clienteS;
    }
}
