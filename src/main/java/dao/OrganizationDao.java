package dao;

import entities.Organization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class OrganizationDao implements DAO<Organization> {
    final Connection connection;

    public OrganizationDao(Connection connection) {
        this.connection = connection;
    }

    @Override //Получить организацию по инн
    public Organization get(int inn) {

        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT name, inn, payment_account FROM public.\"Organization\" WHERE inn = " + inn)) {
                while (rs.next()) {
                    return new Organization(rs.getString("name"), rs.getInt("inn"), rs.getInt("payment_account"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new IllegalStateException(" Organization with inn " + inn + " not found");
    }

    @Override
    public List<Organization> getAll() {
        final List<Organization> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT name, inn, payment_account FROM public.\"Organization\"")) {
                while (rs.next()) {
                    result.add(new Organization(rs.getString("name"), rs.getInt("inn"), rs.getInt("payment_account")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public void save(Organization entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.\"Organization\"(name,inn,payment_account) VALUES(?,?,?)")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getInn());
            preparedStatement.setLong(3, entity.getPayment_account());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override //Обновить данные органищации с некоторым инн
    public void update(Organization entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE public.\"Organization\" SET name = ?, payment_account = ? WHERE inn = ?")) {
            int cnt = 1;
            preparedStatement.setString(cnt++, entity.getName());
            preparedStatement.setLong(cnt++, entity.getPayment_account());
            preparedStatement.setInt(cnt++, entity.getInn());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Organization entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.\"Organization\" WHERE inn = ?")) {
            preparedStatement.setInt(1, entity.getInn());
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException(" Organization with inn " + entity.getInn() + " not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
