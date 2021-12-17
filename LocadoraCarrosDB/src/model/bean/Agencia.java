/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;
import model.bean.Veiculo;
/**
 *
 * @author livio
 */
public class Agencia {
    private int idAgencia;
    private String rua;
    private String numero;
    private String bairro;
    private String uf;
    private String email;
    private String celular;
    
// o código abaixo referencia a classe bean veiculo
//    private Veiculo agIdVeiculo;
//
//    public Veiculo getAgIdVeiculo() {
//        return agIdVeiculo;
//    }
//
//    public void setAgIdVeiculo(Veiculo agIdVeiculo) {
//        this.agIdVeiculo = agIdVeiculo;
//  }

    public int getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(int idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    
}
