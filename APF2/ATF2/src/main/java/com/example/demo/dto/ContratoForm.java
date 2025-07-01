package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContratoForm {

    private String nombreUsuario;
    private String apellidoUsuario;
    private String emailUsuario;

    @NotNull(message = "Debe seleccionar un plan.")
    private Integer idPlan;

    @NotBlank(message = "La dirección de instalación es obligatoria.")
    @Size(max = 255, message = "La dirección no puede exceder los 255 caracteres.")
    private String direccionInstalacion;

    @NotBlank(message = "El número de teléfono de contacto es obligatorio.")
    @Pattern(regexp = "\\d{7,20}", message = "El número de teléfono debe contener entre 7 y 20 dígitos.")
    private String numeroTelefonoContacto;

    @NotBlank(message = "El método de pago es obligatorio.")
    @Size(max = 50, message = "El método de pago no puede exceder los 50 caracteres.")
    private String metodoPago;

    @DecimalMin(value = "0.00", message = "El costo de instalación no puede ser negativo.")
    private BigDecimal costoInstalacion;

    @Size(max = 1000, message = "Las observaciones no pueden exceder los 1000 caracteres.")
    private String observaciones;

    private LocalDate fechaInicioServicio;

    public ContratoForm() {
        this.costoInstalacion = BigDecimal.ZERO;
    }
}