package com.example.demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlanForm {

    private Integer idPlan;

    @NotBlank(message = "El nombre del plan es obligatorio")
    private String nombrePlan;

    @NotNull(message = "La velocidad de descarga es obligatoria")
    @Min(value = 1, message = "La velocidad de descarga debe ser al menos 1 Mbps")
    private Integer velocidadDescargaMbps;

    @NotNull(message = "La velocidad de carga es obligatoria")
    @Min(value = 1, message = "La velocidad de carga debe ser al menos 1 Mbps")
    private Integer velocidadCargaMbps;

    @NotNull(message = "Debe indicar si incluye WiFi (0 para No, 1 para Sí)")
    @Min(value = 0, message = "El valor para WiFi debe ser 0 o 1")
    private Integer wifiIncluido;

    @NotNull(message = "Debe indicar si hay promoción de mes gratis")
    private Boolean mesGratisPromocion;

    private Integer puertosEthernet;

    @NotNull(message = "El precio mensual es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio mensual debe ser mayor a 0")
    private BigDecimal precioMensual;

    private String descripcion;

    @NotNull(message = "Debe indicar si el plan está activo")
    private Boolean activo;
}
