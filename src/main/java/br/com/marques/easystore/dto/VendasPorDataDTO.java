package br.com.marques.easystore.dto;

import java.time.LocalDate;

public class VendasPorDataDTO {
    private double total;
    private LocalDate data;

    public VendasPorDataDTO(double total, LocalDate data) {
        this.total = total;
        this.data = data;
    }

    //Getters and Setters
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
}
