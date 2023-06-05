package com.example.notes2;


import org.hibernate.Session;

import java.util.List;



public class Main {

    public static void main(String[] args) {
        Session session = CreateFactory.getFactory().openSession();
        session.getTransaction().begin();

        Notes note1 = new Notes();
        note1.setNote("New note 1");
        note1.setNumber(0);

        Notes note2 = new Notes();
        note2.setNote("New note 2");
        note2.setNumber(2);

        Notes.save(note1, session);
        Notes.save(note2, session);


        note1.setNote("Updated note.");
        Notes.update(note1, session);

        Notes note3 = new Notes();
        note3.setNote("New note 3");
        note3.setNumber(3);
        Notes.save(note3, session);

        Notes.printAll(session);
        Notes.remove(note1, session);
        Notes.printAll(session);


        session.close();

    }
}
