package com.models;

import com.dao.CidadeDAO;

public class Pais extends Model {
    private String nome;

    public Pais() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static boolean verificarPossuiCidadesFilhas(int id_pais) {
        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO.pegarPeloPais(id_pais).size() > 0;
    }
}
