package com.example.appfiapviagem.model;

import android.widget.EditText;

import java.util.Objects;

public class Destino {

    private String id;
    private String pais;
    private String estado;
    private String endereco;
    private String hospedagem;
    private String valorGasto;
    private String avaliacao;
    private String descricao;

    public Destino() {
    }

    public Destino(String pais, String estado, String endereco, String hospedagem, String valorGasto, String avaliacao, String descricao) {
        this.pais = pais;
        this.estado = estado;
        this.endereco = endereco;
        this.hospedagem = hospedagem;
        this.valorGasto = valorGasto;
        this.avaliacao = avaliacao;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(String hospedagem) {
        this.hospedagem = hospedagem;
    }

    public String getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(String valorGasto) {
        this.valorGasto = valorGasto;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destino)) return false;
        Destino destino = (Destino) o;
        return Objects.equals(getId(), destino.getId()) &&
                Objects.equals(getPais(), destino.getPais()) &&
                Objects.equals(getEstado(), destino.getEstado()) &&
                Objects.equals(getEndereco(), destino.getEndereco()) &&
                Objects.equals(getHospedagem(), destino.getHospedagem()) &&
                Objects.equals(getValorGasto(), destino.getValorGasto()) &&
                Objects.equals(getAvaliacao(), destino.getAvaliacao()) &&
                Objects.equals(getDescricao(), destino.getDescricao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPais(), getEstado(), getEndereco(), getHospedagem(), getValorGasto(), getAvaliacao(), getDescricao());
    }

    @Override
    public String toString() {
        return "Destino{" +
                "id='" + id + '\'' +
                ", pais='" + pais + '\'' +
                ", estado='" + estado + '\'' +
                ", endereco='" + endereco + '\'' +
                ", hospedagem='" + hospedagem + '\'' +
                ", valorGasto='" + valorGasto + '\'' +
                ", avaliacao='" + avaliacao + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
