package com.dao;

import com.connection.ConnFactory;
import com.models.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO extends BaseDAO {

    public ContatoDAO() {
        table = "contato";
    }

    public void create(Contato contato) {
        String sql = "INSERT INTO contato (nome, email) VALUES (?, ?)";
        Connection conn = ConnFactory.createConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, contato.getNome());
            preparedStatement.setString(2, contato.getEmail());
            preparedStatement.execute();
            System.out.println("Contato Salvo com sucesso!");
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

    public List<Contato> all() {
        String sql = "SELECT * FROM contato";
        List<Contato> contatos = new ArrayList<>();

        Connection conn = ConnFactory.createConnection();
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Contato c = new Contato();
                c.setId(resultSet.getInt("id"));
                c.setNome(resultSet.getString("nome"));
                c.setEmail(resultSet.getString("email"));
                contatos.add(c);
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

        return contatos;
    }

    public Contato show(int id) {
        String sql = "SELECT * FROM contato WHERE id = ?";

        Contato contato = new Contato();
        Connection conn = ConnFactory.createConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                contato.setId(resultSet.getInt("id"));
                contato.setNome(resultSet.getString("nome"));
                contato.setEmail(resultSet.getString("email"));
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

        return contato;
    }

    public void update(Contato contato) {
        String sql = "UPDATE contato SET nome = ?, email = ? WHERE id = ?";

        Connection conn = ConnFactory.createConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, contato.getNome());
            preparedStatement.setString(2, contato.getEmail());
            preparedStatement.setInt(3, contato.getId());
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

//    public void remove(int id) {
//        String sql = "DELETE FROM contato WHERE id = ?";
//
//        Connection conn = ConnFactory.createConnection();
//        try {
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            preparedStatement.execute();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
