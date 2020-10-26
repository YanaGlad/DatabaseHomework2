import dao.InvoiceDao;
import dao.InvoicePositionDao;
import dao.NomenclatureDao;
import dao.OrganizationDao;
import entities.Invoice;
import entities.InvoicePosition;
import entities.Nomenclature;
import entities.Organization;
import org.flywaydb.core.Flyway;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public final class Main {

    public static void main(String[] args) throws SQLException {
        final Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/Finance", "postgres", "money10")
                .locations("db")
                .load();
        flyway.clean();
        flyway.migrate();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Finance", "postgres", "money10")) {
            System.out.println("Connection Ok.");

            //Примеры работы CRUD
            final OrganizationDao organizationDAO = new OrganizationDao(connection);

            organizationDAO.save(new Organization("Company1", 2234, 12000));
            organizationDAO.save(new Organization("Company2", 2342, 70000));
            organizationDAO.save(new Organization("Company3", 3, 50000));
            organizationDAO.save(new Organization("Company4", 4, 50000));
            organizationDAO.save(new Organization("Company5", 532, 50000));
            organizationDAO.save(new Organization("Company6", 21, 30000));
            organizationDAO.save(new Organization("Company7", 423, 50000));
            organizationDAO.save(new Organization("Company8", 123, 44522));
            organizationDAO.save(new Organization("Company9", 757, 50000));
            organizationDAO.save(new Organization("Company10", 456, 19000));
            organizationDAO.save(new Organization("Company11", 34, 15000));
            organizationDAO.save(new Organization("Company12", 234, 200000));


            for (Organization organization : organizationDAO.getAll()) {
                System.out.println("name : " + organization.getName() + " inn: " + organization.getInn() + " payment account: " + organization.getPayment_account());
            }


            final InvoiceDao invoiceDao = new InvoiceDao(connection);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            calendar.setTimeInMillis(0);

            calendar.set(2020, Calendar.SEPTEMBER, 10, 1, 0, 0);
            long date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(1, new Date(date), "Company3"));

            calendar.set(2020, Calendar.SEPTEMBER, 20, 1, 0, 0);
            date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(2, new Date(date), "Company1"));

            calendar.set(2020, Calendar.SEPTEMBER, 12, 1, 0, 0);
            date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(3, new Date(date), "Company2"));

            calendar.set(2020, Calendar.JANUARY, 2, 1, 0, 0);
            date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(4, new Date(date), "Company4"));

            calendar.set(2020, Calendar.JUNE, 27, 1, 0, 0);
            date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(5, new Date(date), "Company5"));

            calendar.set(2020, Calendar.MARCH, 14, 1, 0, 0);
            date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(6, new Date(date), "Company5"));

            calendar.set(2020, Calendar.APRIL, 10, 1, 0, 0);
            date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(7, new Date(date), "Company7"));

            calendar.set(2020, Calendar.SEPTEMBER, 5, 1, 0, 0);
            date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(8, new Date(date), "Company8"));

            calendar.set(2020, Calendar.JULY, 22, 1, 0, 0);
            date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(9, new Date(date), "Company9"));

            calendar.set(2019, Calendar.DECEMBER, 20, 1, 0, 0);
            date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(10, new Date(date), "Company10"));

            calendar.set(2019, Calendar.NOVEMBER, 1, 1, 0, 0);
            date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(11, new Date(date), "Company11"));

            calendar.set(2018, Calendar.SEPTEMBER, 12, 1, 0, 0);
            date = calendar.getTimeInMillis();
            invoiceDao.save(new Invoice(12, new Date(date), "Company12"));

            for (Invoice invoice : invoiceDao.getAll()) {
                System.out.println("id : " + invoice.getId() + " date: " + invoice.getOrganization_date() + " company: " + invoice.getOrganization_name());
            }

            final NomenclatureDao nomenclatureDao = new NomenclatureDao(connection);

            nomenclatureDao.save(new Nomenclature("nomenclature1", 3245));
            nomenclatureDao.save(new Nomenclature("nomenclature2", 5463));
            nomenclatureDao.save(new Nomenclature("nomenclature3", 9545));
            nomenclatureDao.save(new Nomenclature("nomenclature4", 1231));
            nomenclatureDao.save(new Nomenclature("nomenclature5", 3243));
            nomenclatureDao.save(new Nomenclature("nomenclature6", 1239));
            nomenclatureDao.save(new Nomenclature("nomenclature7", 2221));
            nomenclatureDao.save(new Nomenclature("nomenclature8", 2411));
            nomenclatureDao.save(new Nomenclature("nomenclature9", 1208));
            nomenclatureDao.save(new Nomenclature("nomenclature10", 1234));
            nomenclatureDao.save(new Nomenclature("nomenclature11", 2904));
            nomenclatureDao.save(new Nomenclature("nomenclature12", 2303));

            for (Nomenclature nomenclature : nomenclatureDao.getAll()) {
                System.out.println("name : " + nomenclature.getName() + " inside code: " + nomenclature.getInside_code());
            }

            final InvoicePositionDao invoicePositionDao = new InvoicePositionDao(connection);

            invoicePositionDao.save(new InvoicePosition(4, 8000, "nomenclature5", 2));
            invoicePositionDao.save(new InvoicePosition(2, 8000, "nomenclature2", 30));
            invoicePositionDao.save(new InvoicePosition(1, 1000, "nomenclature3", 23));
            invoicePositionDao.save(new InvoicePosition(3, 5000, "nomenclature3", 12));
            invoicePositionDao.save(new InvoicePosition(5, 5000, "nomenclature2", 6));
            invoicePositionDao.save(new InvoicePosition(6, 5000, "nomenclature6", 7));
            invoicePositionDao.save(new InvoicePosition(7, 5000, "nomenclature7", 11));
            invoicePositionDao.save(new InvoicePosition(8, 5000, "nomenclature2", 6));
            invoicePositionDao.save(new InvoicePosition(9, 5000, "nomenclature9", 31));
            invoicePositionDao.save(new InvoicePosition(10, 5000, "nomenclature3", 23));
            invoicePositionDao.save(new InvoicePosition(11, 5000, "nomenclature11", 43));
            invoicePositionDao.save(new InvoicePosition(12, 5000, "nomenclature3", 1));

            for (InvoicePosition invoicePosition : invoicePositionDao.getAll()) {
                System.out.println("id: " + invoicePosition.getId() + " price: " + invoicePosition.getPrice() + " nomenclature name: " + invoicePosition.getNomenclature_name() + " count: " + invoicePosition.getCount());
            }



            List<Organization> firstTenOrganizations = getFirstTenOrganizationsWithTopCount(invoicePositionDao, invoiceDao, organizationDAO);

            System.out.println("первые 10 поставщиков по количеству поставленного товара: ");
            for (Organization org : firstTenOrganizations) {
                System.out.println("Name: " + org.getName() + " inn: " + org.getInn() + " payment account: " + org.getPayment_account());
            }

            List<Organization> chosenOrgs = chooseOrganizations(20, "nomenclature3", invoicePositionDao, invoiceDao, organizationDAO);

            System.out.println("поставщики с суммой поставленного товара выше указанного количества: ");
            for (Organization org : chosenOrgs) {
                System.out.println("Name: " + org.getName() + " inn: " + org.getInn() + " payment account: " + org.getPayment_account());
            }
            
            calendar.set(2020, Calendar.SEPTEMBER, 1, 1, 0, 0);
            date = calendar.getTimeInMillis();
            Date date1 = new Date(date);
            calendar.set(2020, Calendar.OCTOBER, 31, 1, 0, 0);
            date = calendar.getTimeInMillis();
            Date date2 = new Date(date);

            countTotal(date1, date2, invoiceDao, invoicePositionDao);

            System.out.println("Средняя цена полученного товара за период");
            System.out.println(countAveragePrice(invoiceDao, invoicePositionDao, date1, date2));
            System.out.println();

            printOrganizationProducts(invoiceDao, invoicePositionDao, organizationDAO);


        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    //Вывести список товаров, поставленных организациями за период.
    // Если организация товары не поставляла, то она все равно должна быть отражена в списке.
    public static void printOrganizationProducts(InvoiceDao invoiceDao, InvoicePositionDao invoicePositionDao, OrganizationDao organizationDAO) {
        List<Invoice> invoice = invoiceDao.getAll();
        List<Organization> organizations = organizationDAO.getAll();

        for (Organization organization : organizations) {
            System.out.println("Компания: " + organization.getName());
            for (Invoice value : invoice) {
                if (value.getOrganization_name().equals(organization.getName())) {
                    System.out.println("Товар: " + invoicePositionDao.get(value.getId()).getNomenclature_name());
                }
            }
            System.out.println();
        }

    }

    //Рассчитать среднюю цену полученного товара за период
    public static double countAveragePrice(InvoiceDao invoiceDao, InvoicePositionDao invoicePositionDao, Date date1, Date date2) {
        double sum = 0, totalCount = 0;
        List<InvoicePosition> invoices = invoicePositionDao.getAll();
        for (InvoicePosition invoice : invoices) {
            if (invoiceDao.get(invoice.getId()).getOrganization_date().before(date2) && invoiceDao.get(invoice.getId()).getOrganization_date().after(date1)) {
                sum += invoice.getPrice(); //сумма полученного товара
                totalCount++; //С товара
            }
        }
        return sum / totalCount;
    }

    //За каждый день в разрезе номенклатур рассчитать количество
    // и сумму полученного товара в указанном периоде, посчитать итоги за период
    public static void countTotal(Date date1, Date date2, InvoiceDao invoiceDao, InvoicePositionDao
            invoicePositionDao) {
        int sum = 0, totalCount = 0;
        List<Invoice> invoices = invoiceDao.getAll();
        for (Invoice invoice : invoices) {
            if (invoice.getOrganization_date().before(date2) && invoice.getOrganization_date().after(date1)) {
                sum += invoicePositionDao.get(invoice.getId()).getPrice(); //сумма полученного товара
                totalCount += invoicePositionDao.get(invoice.getId()).getCount(); //С товара
            }
        }
        System.out.println("Сумма полученного товара: " + sum);
        System.out.println("Количество товара: " + totalCount);

    }

    public static Organization findOrganisationByInvoicePositionAll(List<InvoicePosition> allPos,
                                                                    InvoiceDao invoiceDao,
                                                                    OrganizationDao organizationDAO) {
        for (InvoicePosition pos : allPos) {
            Invoice currentInvoice = invoiceDao.get(pos.getId());
            for (int i = 0; i < organizationDAO.getAll().size(); i++) {
                if (organizationDAO.getAll().get(i).getName().equals(currentInvoice.getOrganization_name())) {
                    Organization result = organizationDAO.getAll().get(i);
                    allPos.remove(pos);
                    return result;
                }
            }
        }
        return null;
    }


    public static Organization findOrganisationByInvoicePositionOnly(InvoicePosition invoicePosition,
                                                                     InvoiceDao invoiceDao,
                                                                     OrganizationDao organizationDAO) {
        Invoice inv = invoiceDao.get(invoicePosition.getId());
        List<Organization> organizations = organizationDAO.getAll();
        for (Organization organization : organizations) {
            if (inv.getOrganization_name().equals(organization.getName()))
                return organization;
        }
        return null;
    }

    //Выбрать первые 10 поставщиков по количеству поставленного товара
    public static List<Organization> getFirstTenOrganizationsWithTopCount(InvoicePositionDao invoicePositionDao,
                                                                          InvoiceDao invoiceDao,
                                                                          OrganizationDao organizationDAO) {
        List<Organization> result = new ArrayList<>();
        List<InvoicePosition> allPos = invoicePositionDao.getAll();
        
        Collections.sort(allPos, InvoicePosition::compareTo);

        int total = invoicePositionDao.getAll().size();
        if(total>10)
            total = 10;

        System.out.println("total = " + total);
        int c = 1;
        while (c <= total) {
            result.add(findOrganisationByInvoicePositionAll(allPos, invoiceDao, organizationDAO));
            c++;
        }
        return result;
    }

    //Выбрать поставщиков с суммой поставленного товара выше указанного количества
    public static List<Organization> chooseOrganizations(int count, String name, InvoicePositionDao
            invoicePositionDao,
                                                         InvoiceDao invoiceDao, OrganizationDao organizationDAO) {
        List<Organization> result = new ArrayList<>();
        List<InvoicePosition> allPos = invoicePositionDao.getAll();
        for (InvoicePosition pos : allPos) {
            if (pos.getNomenclature_name().equals(name) && pos.getCount() > count) {
                result.add(findOrganisationByInvoicePositionOnly(pos, invoiceDao, organizationDAO));
            }

        }

        return result;
    }
}
