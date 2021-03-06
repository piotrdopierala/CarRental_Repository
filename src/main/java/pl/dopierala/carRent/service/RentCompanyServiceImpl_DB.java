package pl.dopierala.carRent.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.dopierala.carRent.domain.*;

import java.util.List;

public class RentCompanyServiceImpl_DB implements RentCompanyService {

    private static SessionFactory sessionFactory;

    @Override
    public RentCompany createNewCompany(String name, String website, String address, String owner, String logo) {
        return null;
    }

    @Override
    public RentCompany getCompany() {
        return null;
    }

    @Override
    public void addDepartmentToCompany(String depAdress) {

    }

    @Override
    public boolean removeDepartment(String address) {
        return false;
    }

    @Override
    public List<Department> getDepartmentsList() {
        return null;
    }

    @Override
    public void addEmployeeToDepartment(Employee emp) {

    }

    @Override
    public void addClient(Client client) {

    }

    @Override
    public void addCarToDepartment(Car car, Department dep) {

        dep.getCarList().add(car);

        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.getTransaction();
        transaction.begin();
        currentSession.persist(car);
        transaction.commit();
    }

    @Override
    public int initializeRepository() {
        setUpDB();
        return 0; //success
    }

    @Override
    public int closeRepository() {
        closeDB();
        return 0;//success
    }

    private static void setUpDB() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
            throw  e;
        }
    }

    private static void closeDB(){
        sessionFactory.close();
    }
}
