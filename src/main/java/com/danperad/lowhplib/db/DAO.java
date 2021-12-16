package com.danperad.lowhplib.db;

import com.danperad.lowhplib.PlayerLow;
import org.hibernate.Session;

public class DAO {
    public static PlayerLow getPlayer(String UUID){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        PlayerLow player = session.get(PlayerLow.class, UUID);
        session.close();
        return player;
    }

    public static void insertPlayer(PlayerLow player){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(player);
        session.getTransaction().commit();
        session.close();
    }

    public static void updatePlayer(PlayerLow player){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(player);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleatePlayer(PlayerLow player){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(player);
        session.getTransaction().commit();
        session.close();
    }

    public static void init(){
        HibernateSessionFactoryUtil.getSessionFactory().openSession().close();
    }
}
