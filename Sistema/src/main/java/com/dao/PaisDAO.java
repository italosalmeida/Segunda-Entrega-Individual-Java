package com.dao;

import com.connection.ConnFactory;
import com.models.Contato;
import com.models.Pais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO extends BaseDAO {

    public PaisDAO() {
        table = "pais";
    }

    public List<Pais> all() {
        String sql = "SELECT * FROM pais";
        List<Pais> paises = new ArrayList<>();

        Connection conn = ConnFactory.createConnection();
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Pais p = new Pais();
                p.setId(resultSet.getInt("id"));
                p.setNome(resultSet.getString("nome"));
                paises.add(p);
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

        return paises;
    }

    public void create(Pais pais) {
        String sql = "INSERT INTO pais (nome) VALUES (?)";
        Connection conn = ConnFactory.createConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, pais.getNome());
            preparedStatement.execute();
            System.out.println("Pais Salvo com sucesso!");
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

    public Pais show(int id) {
        String sql = "SELECT * FROM pais WHERE id = ?";

        Pais pais = new Pais();
        Connection conn = ConnFactory.createConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pais.setId(resultSet.getInt("id"));
                pais.setNome(resultSet.getString("nome"));
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

        return pais;
    }

    public void update(Pais pais) {
        String sql = "UPDATE pais SET nome = ? WHERE id = ?";

        Connection conn = ConnFactory.createConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, pais.getNome());
            preparedStatement.setInt(2, pais.getId());
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
}
