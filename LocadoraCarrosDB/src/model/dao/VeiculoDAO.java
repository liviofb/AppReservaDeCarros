/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import connection.ConnectionFactory;
import java.sql.*;
import javax.swing.JOptionPane;
import model.bean.Veiculo;
/**
 *
 * @author livio
 */
public class VeiculoDAO {
    
    public void create(Veiculo v){
       Connection con = ConnectionFactory.getConnection();
       PreparedStatement stmt = null;
       
        try {
            //Lembrar que no valor em idagencia é necessário refgerenciar corretamente a agencia no qual o veiculo está sendo registrado.
            stmt = con.prepareStatement("INSERT INTO veiculo (idVeiculo, placa, modelo, cor, ano, valor_dia, km,combustivel, agencia_idagencia)VALUES(@@IDENTITY,?,?,?,?,?,?,?, 1)");
            stmt.setString(1, v.getPlaca());
            stmt.setString(2, v.getModelo());
            stmt.setString(3, v.getCor());
            stmt.setString(4, v.getAno());
            stmt.setFloat(5, v.getValor_dia());
            stmt.setInt(6, v.getKm());
            stmt.setString(7, v.getCombustivel());
            
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
       
       
    }
    
    
}
