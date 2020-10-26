package dao;

import entities.Invoice;
import entities.InvoicePosition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDao implements DAO<Invoice> {
    final Connection connection;

    public InvoiceDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Invoice get(int id) {
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT id, organization_date, organization_name FROM public.\"Invoice\" WHERE id = " + id)) {
                while (rs.next()) {
                    return new Invoice(rs.getInt("id"), rs.getDate("organization_date"), rs.getString("organization_name"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new IllegalStateException("Invoice with id " + id + " not found");
    }

    @Override
    public List<Invoice> getAll() {
        final List<Invoice> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT id, organization_date, organization_name FROM public.\"Invoice\"")) {
                while (rs.next()) {
                    result.add(new Invoice(rs.getInt("id"), rs.getDate("organization_date"), rs.getString("organization_name")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public void save(Invoice entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.\"Invoice\"(id, organization_date, organization_name) VALUES(?,?,?)")) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setDate(2, entity.getOrganization_date());
            preparedStatement.setString(3, entity.getOrganization_name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Invoice entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.\"Invoice\" SET organization_date = ?, organization_name = ? WHERE id = ?")) {
            int cnt = 1;
            preparedStatement.setDate(cnt++, entity.getOrganization_date());
            preparedStatement.setString(cnt++, entity.getOrganization_name());
            preparedStatement.setInt(cnt++, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Invoice entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.\"Organization\" WHERE id = ?")) {
            preparedStatement.setInt(1, entity.getId());
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException(" Invoice with id " + entity.getId() + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
