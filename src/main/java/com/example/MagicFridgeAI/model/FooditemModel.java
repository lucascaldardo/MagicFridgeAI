package com.example.MagicFridgeAI.model;

import com.example.MagicFridgeAI.Enum.FooditemEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "food_item")
@NoArgsConstructor

public class FooditemModel {

    private Long id;
    private String nome;
    private FooditemEnum categoria;
    private int quantidade;
    private LocalDateTime validade;

    public FooditemModel(Long id, String nome, FooditemEnum categoria, int quantidade, LocalDateTime validade) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.validade = validade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public FooditemEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(FooditemEnum categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getValidade() {
        return validade;
    }

    public void setValidade(LocalDateTime validade) {
        this.validade = validade;
    }
}
