package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactoForm {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    private String apellido;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El formato del correo electrónico no es válido")
    private String email;

    @Size(max = 20, message = "El teléfono no debe exceder los 20 caracteres")
    private String telefono;

    @NotBlank(message = "El asunto no puede estar vacío")
    @Size(min = 5, max = 255, message = "El asunto debe tener entre 5 y 255 caracteres")
    private String asunto;

    @NotBlank(message = "El mensaje no puede estar vacío")
    @Size(min = 10, max = 1000, message = "El mensaje debe tener entre 10 y 1000 caracteres")
    private String mensaje;

    @NotBlank(message = "Debes seleccionar un género de asesor")
    private String asesorGenero;

    public ContactoForm() {
        this.asesorGenero = "MASCULINO";
    }
}