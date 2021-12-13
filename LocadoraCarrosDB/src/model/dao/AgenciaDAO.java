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
import model.bean.Agencia;

/**
 *
 * @author livio
 */
public class AgenciaDAO {
    
    
     public void create(Agencia ag){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       
        try {
            //Lembrar que presis criar agencias para poder cadastrar veiculos
            stmt = con.prepareStatement("INSERT INTO agencia (rua, numero, bairro, uf, email, celular)VALUES(Sete de Setembro, 607, Centro, AP, agencia1@email.com, (96)911111111)");
            
            stmt.setInt(0, ag.getIdAgencia());
            //stmt.executeUpdate();
            
            //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
       
       
    }
    
}
