package com.example.notes2;


import org.hibernate.Session;

import java.util.List;



public class Main {

    public void update(Notes note, Session session) {
        session.update(note);
        session.beginTransaction().commit();
        System.out.println("Object updated");
    }
    public void remove(Notes note, Session session) {
        session.remove(note);
    }

    public void save(Notes note, Session session) {
        session.save(note);
        session.getTransaction().commit();
    }
    public static void main(String[] args) {
        Session session = CreateFactory.getFactory().openSession();
        session.getTransaction().begin();

        Notes note1 = new Notes();
        note1.setNote("New note 1");
        note1.setNumber(0);

        Notes note2 = new Notes();
        note2.setNote("New note 2");
        note2.setNumber(2);

        session.save(note1);
        session.save(note2);


        note1.setNote("Updated note.");
        session.update(note1);
        session.getTransaction().commit();

        Notes note3 = new Notes();
        note3.setNote("New note 3");
        note3.setNumber(3);

        List<Notes> list = CreateFactory.loadAllData(Notes.class, session);

        for (Notes note : list) {
            System.out.println(note.getId() + " " + note.getNote() +  " " + note.getNumber());
        }


        session = CreateFactory.getFactory().openSession();
        session.remove(note1);

        list = CreateFactory.loadAllData(Notes.class, session);
        for (Notes note : list) {
            System.out.println(note.getId() + " " + note.getNote() +  " " + note.getNumber());
        }


        session.close();

    }
}
