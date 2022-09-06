package com.dao;

import com.connection.ConnFactory;
import com.models.Cidade;
import com.models.Pais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO extends BaseDAO{
    public CidadeDAO() {
        table = "cidade";
    }

    public List<Cidade> all() {
        String sql = "SELECT c.id, c.nome AS cidade, p.nome AS pais FROM cidade as C JOIN pais p ON p.id = c.id_pais ORDER BY c.id";

        List<Cidade> cidades = new ArrayList<>();

        Connection conn = ConnFactory.createConnection();
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cidade c = new Cidade();
                c.setId(resultSet.getInt("id"));
                c.setNome(resultSet.getString("cidade"));
                c.setPais(resultSet.getString("pais"));
                cidades.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        return cidades;
    }

    public void create(Cidade cidade) {
        String sql = "INSERT INTO cidade (nome, id_pais) VALUES (?, ?)";
        Connection conn = ConnFactory.createConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cidade.getNome());
            preparedStatement.setInt(2, cidade.getId_pais());
            preparedStatement.execute();
            System.out.println("Cidade Salva com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Cidade show(int id) {
        String sql = "SELECT c.id, c.nome AS cidade, p.nome AS pais FROM cidade as C JOIN pais p ON p.id = c.id_pais WHERE c.id = ?";

        Cidade cidade = new Cidade();
        Connection conn = ConnFactory.createConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cidade.setId(resultSet.getInt("id"));
                cidade.setNome(resultSet.getString("cidade"));
                cidade.setPais(resultSet.getString("pais"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        return cidade;
    }

    public void update(Cidade cidade) {
        String sql = "UPDATE cidade SET nome = ? WHERE id = ?";

        Connection conn = ConnFactory.createConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, cidade.getNome());
            preparedStatement.setInt(2, cidade.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public List<Cidade> pegarPeloPais(int id_pais) {
        String sql = "SELECT id FROM cidade WHERE id_pais = ?";
        Connection conn = ConnFactory.createConnection();
        List<Cidade> cidades = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id_pais);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cidade c = new Cidade();
                cidades.add(c);
                c.setId(resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        return cidades;
    }
}
