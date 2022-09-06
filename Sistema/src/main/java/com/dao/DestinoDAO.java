package com.dao;

import com.connection.ConnFactory;
import com.models.Cidade;
import com.models.Destino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DestinoDAO extends BaseDAO {
    public DestinoDAO() {
        table = "destinos";
    }

    public List<Destino> all() {
        String sql = "SELECT d.id, c1.nome AS partida, c2.nome AS destino FROM destinos d JOIN cidade c1 ON c1.id = d.cidade_partida_id JOIN cidade c2 ON c2.id = d.cidade_destino_id";

        List<Destino> destinos = new ArrayList<>();

        Connection conn = ConnFactory.createConnection();
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Destino d = new Destino();
                d.setId(resultSet.getInt("id"));
                d.setPartida(resultSet.getString("partida"));
                d.setDestino(resultSet.getString("destino"));
                destinos.add(d);
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

        return destinos;
    }

    public void create(Destino destino) {
        String sql = "INSERT INTO destinos (cidade_partida_id, cidade_destino_id) VALUES (?, ?)";
        Connection conn = ConnFactory.createConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, destino.getCidade_partida_id());
            preparedStatement.setInt(2, destino.getCidade_destino_id());
            preparedStatement.execute();
            System.out.println("Destino Salvo com sucesso!");
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

    public List<Destino> pegarPelaCidade(int id_cidade) {
        String sql = "SELECT id FROM destinos WHERE cidade_partida_id = ? OR cidade_destino_id = ?";

        Connection conn = ConnFactory.createConnection();
        List<Destino> destinos = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id_cidade);
            preparedStatement.setInt(2, id_cidade);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Destino d = new Destino();
                destinos.add(d);
                d.setId(resultSet.getInt("id"));
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

        return destinos;
    }
}
