package com.models;

import com.dao.CidadeDAO;
import com.dao.DestinoDAO;

public class Cidade extends Model {
    private String nome;
    private int id_pais;
    private String pais;

    public Cidade() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public static boolean verificarCidadePossuiDestinos(int id_cidade) {
        DestinoDAO destinoDAO = new DestinoDAO();
        return destinoDAO.pegarPelaCidade(id_cidade).size() > 0;
    }

    public static boolean validarId(int id) {
        CidadeDAO cidadeDAO = new CidadeDAO();
        boolean valido = false;
        for (Cidade c: cidadeDAO.all()) {
            if (id == c.getId()) {
                valido = true;
                break;
            }
        }

        return valido;
    }
}
