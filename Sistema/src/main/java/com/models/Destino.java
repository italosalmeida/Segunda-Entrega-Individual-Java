package com.models;

public class Destino extends Model {
    private int cidade_partida_id;
    private int cidade_destino_id;
    private String partida;
    private String destino;

    public Destino() {

    }

    public int getCidade_partida_id() {
        return cidade_partida_id;
    }

    public void setCidade_partida_id(int cidade_partida_id) {
        this.cidade_partida_id = cidade_partida_id;
    }

    public int getCidade_destino_id() {
        return cidade_destino_id;
    }

    public void setCidade_destino_id(int cidade_destino_id) {
        this.cidade_destino_id = cidade_destino_id;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
