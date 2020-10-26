import dao.InvoiceDao;
import dao.InvoicePositionDao;
import dao.NomenclatureDao;
import dao.OrganizationDao;
import entities.Invoice;
import entities.InvoicePosition;
import entities.Nomenclature;
import entities.Organization;
import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class Testing {

    @Test //Проверка работы organization dao. Проверка работы методов save и get
    public void testOrganizationDao() {
        final Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/Finance", "postgres", "money10")
                .locations("db")
                .load();
        flyway.clean();
        flyway.migrate();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Finance", "postgres", "money10")) {
            System.out.println("Connection Ok.");
            final OrganizationDao organizationDAO = new OrganizationDao(connection);

            organizationDAO.save(new Organization("Company1", 2234, 12000));
            organizationDAO.save(new Organization("Company2", 2342, 70000));
            String expected = "Company1";
            String actual = organizationDAO.get(2234).getName();

            Assert.assertEquals(expected, actual);

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFirstTenOrganizationsWithTopCount() {

        final Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/Finance", "postgres", "money10")
                .locations("db")
                .load();
        flyway.clean();
        flyway.migrate();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Finance", "postgres", "money10")) {
            System.out.println("Connection Ok.");

            final OrganizationDao organizationDAO = new OrganizationDao(connection);
            organizationDAO.save(new Organization("Company11", 34, 15000));
            organizationDAO.save(new Organization("Company12", 234, 200000));


            final InvoiceDao invoiceDao = new InvoiceDao(connection);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            calendar.setTimeInMillis(0);
            calendar.set(2019, Calendar.NOVEMBER, 1, 1, 0, 0);
            long date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(11, new Date(date), "Company11"));
            calendar.set(2018, Calendar.SEPTEMBER, 12, 1, 0, 0);
            date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(12, new Date(date), "Company12"));

            final NomenclatureDao nomenclatureDao = new NomenclatureDao(connection);
            nomenclatureDao.save(new Nomenclature("nomenclature3", 9545));
            nomenclatureDao.save(new Nomenclature("nomenclature11", 2904));

            final InvoicePositionDao invoicePositionDao = new InvoicePositionDao(connection);
            invoicePositionDao.save(new InvoicePosition(11, 5000, "nomenclature11", 43));
            invoicePositionDao.save(new InvoicePosition(12, 5000, "nomenclature3", 1));

            List<Organization> expected;
            Organization[] a = {new Organization("Company11", 34, 15000), new Organization("Company12", 234, 200000)};
            expected = Arrays.asList(a.clone());
            List<Organization> firstTenOrganizations = Main.getFirstTenOrganizationsWithTopCount(invoicePositionDao, invoiceDao, organizationDAO);

            System.out.println("первые 10 поставщиков по количеству поставленного товара: ");
            for (Organization org : firstTenOrganizations) {
                System.out.println("Name: " + org.getName() + " inn: " + org.getInn() + " payment account: " + org.getPayment_account());
            }

            Assert.assertEquals(expected.get(0).getName() + expected.get(1).getName(), firstTenOrganizations.get(0).getName() + firstTenOrganizations.get(1).getName());

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    @Test
    public void testCountAveragePrice() {
        final Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/Finance", "postgres", "money10")
                .locations("db")
                .load();
        flyway.clean();
        flyway.migrate();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Finance", "postgres", "money10")) {
            System.out.println("Connection Ok.");

            final OrganizationDao organizationDAO = new OrganizationDao(connection);
            organizationDAO.save(new Organization("Company11", 34, 15000));
            organizationDAO.save(new Organization("Company12", 234, 200000));


            final InvoiceDao invoiceDao = new InvoiceDao(connection);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            calendar.setTimeInMillis(0);
            calendar.set(2020, Calendar.SEPTEMBER, 1, 1, 0, 0);
            long date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(11, new Date(date), "Company11"));
            calendar.set(2020, Calendar.OCTOBER, 12, 1, 0, 0);
            date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(12, new Date(date), "Company12"));

            final NomenclatureDao nomenclatureDao = new NomenclatureDao(connection);
            nomenclatureDao.save(new Nomenclature("nomenclature3", 9545));
            nomenclatureDao.save(new Nomenclature("nomenclature11", 2904));

            final InvoicePositionDao invoicePositionDao = new InvoicePositionDao(connection);
            invoicePositionDao.save(new InvoicePosition(11, 5000, "nomenclature11", 43));
            invoicePositionDao.save(new InvoicePosition(12, 5000, "nomenclature3", 1));

            calendar.set(2020, Calendar.SEPTEMBER, 1, 1, 0, 0);
            date = calendar.getTimeInMillis();
            Date date1 = new Date(date);
            calendar.set(2020, Calendar.OCTOBER, 31, 1, 0, 0);
            date = calendar.getTimeInMillis();
            Date date2 = new Date(date);


            Assert.assertEquals(5000,(int)Main.countAveragePrice(invoiceDao, invoicePositionDao, date1, date2));


        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }


}
