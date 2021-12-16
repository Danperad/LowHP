package com.danperad.lowhplib.db;


import com.danperad.lowhplib.PlayerLow;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().
                        addAnnotatedClass(PlayerLow.class).
                        setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC").
                        setProperty("hibernate.connection.url", "jdbc:sqlite:test.sqlite").
                        setProperty("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect").
                        setProperty("hibernate.show_sql", "false").
                        setProperty("hibernate.hdm2ddl.auto", "create").configure();

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
