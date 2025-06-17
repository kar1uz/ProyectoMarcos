/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.modelo;

public class registroUsuarios {

    private String email;
    private String contraseña;
    private String confirmarContraseña;

    public registroUsuarios() {
    }

    public registroUsuarios(String email, String contraseña, String confirmarContraseña) {
        this.email = email;
        this.contraseña = contraseña;
        this.confirmarContraseña = confirmarContraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getConfirmarContraseña() {
        return confirmarContraseña;
    }

    public void setConfirmarContraseña(String confirmarContraseña) {
        this.confirmarContraseña = confirmarContraseña;
    }
}
