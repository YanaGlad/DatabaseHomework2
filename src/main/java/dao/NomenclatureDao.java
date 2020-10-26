package dao;

import entities.Nomenclature;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NomenclatureDao implements DAO<Nomenclature> {
    final Connection connection;

    public NomenclatureDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Nomenclature get(int inside_code) {

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT name FROM public.\"Nomenclature\" WHERE inside_code = " + inside_code)) {
                while (rs.next()) {
                    return new Nomenclature(rs.getString("name"), rs.getInt("inside_code"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new IllegalStateException(" Nomenclature with inside_code " + inside_code + " not found");
    }

    @Override
    public List<Nomenclature> getAll() {
        final List<Nomenclature> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT name, inside_code FROM public.\"Nomenclature\"")) {
                while (rs.next()) {
                    result.add(new Nomenclature(rs.getString("name"), rs.getInt("inside_code")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public void save(Nomenclature entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.\"Nomenclature\"(name,inside_code) VALUES(?,?)")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getInside_code());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Nomenclature entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.\"Nomenclature\" SET name = ? WHERE inside_code = ?")) {
            int cnt = 1;
            preparedStatement.setString(cnt++, entity.getName());
            preparedStatement.setInt(cnt++, entity.getInside_code());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Nomenclature entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.\"Nomenclature\" WHERE inside_code = ?")) {
            preparedStatement.setInt(1, entity.getInside_code());
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException(" Nomenclature with inside_code " + entity.getInside_code() + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
