/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            stmt = con.prepareStatement("INSERT INTO cliente (nome_cliente, cpf, email_cliente, rua, bairro, cidade,uf, cep, celular)VALUES(?,?,?,?,?,?,?,?,?)");
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
    
}
