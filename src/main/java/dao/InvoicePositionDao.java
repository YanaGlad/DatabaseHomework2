package dao;

import entities.Invoice;
import entities.InvoicePosition;
import entities.Organization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoicePositionDao implements DAO<InvoicePosition> {
    final Connection connection;

    public InvoicePositionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public InvoicePosition get(int id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT id, price, nomenclature_name, count FROM public.\"InvoicePositions\" WHERE id = " + id)) {
                while (rs.next()) {
                    return new InvoicePosition(rs.getInt("id"), rs.getLong("price"), rs.getString("nomenclature_name"), rs.getInt("count"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new IllegalStateException("InvoicePosition matching invoice id " + id + " not found");
    }

    @Override
    public List<InvoicePosition> getAll() {
        final List<InvoicePosition> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT id, price, nomenclature_name, count FROM public.\"InvoicePositions\"")) {
                while (rs.next()) {
                    result.add(new InvoicePosition(rs.getInt("id"), rs.getLong("price"), rs.getString("nomenclature_name"), rs.getInt("count")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public void save(InvoicePosition entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.\"InvoicePositions\" (id, price, nomenclature_name, count) VALUES(?,?,?,?)")) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setLong(2, entity.getPrice());
            preparedStatement.setString(3, entity.getNomenclature_name());
            preparedStatement.setLong(4, entity.getCount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(InvoicePosition entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.\"InvoicePositions\" SET price = ?, nomenclature_name = ?, count = ? WHERE id = ?")) {
            int cnt = 1;
            preparedStatement.setLong(cnt++, entity.getPrice());
            preparedStatement.setInt(cnt++, entity.getCount());
            preparedStatement.setString(cnt++, entity.getNomenclature_name());
            preparedStatement.setInt(cnt++, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(InvoicePosition entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.\"InvoicePositions\" WHERE id = ?")) {
            preparedStatement.setInt(1, entity.getId());
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("InvoicePosition matching invoice id " + entity.getId() + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
