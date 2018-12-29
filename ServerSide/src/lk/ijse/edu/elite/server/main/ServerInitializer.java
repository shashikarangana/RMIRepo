package lk.ijse.edu.elite.server.main;

import lk.ijse.edu.elite.server.factoryImpl.ServiceFactoryImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerInitializer {
    public static void main(String[] args) {

        try {
            System.setProperty("java.rmi.server.hostname","localhost");
            Registry registry = LocateRegistry.createRegistry(3030);

            registry.bind("Elite", ServiceFactoryImpl.getInstance());

            System.out.println("Server Started...!");

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            session.beginTransaction();
//
//            session.getTransaction().commit();
//        }
    }
}
