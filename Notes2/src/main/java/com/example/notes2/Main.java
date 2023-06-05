package com.example.notes2;


import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;



public class Main {

    public static void main(String[] args) {
        Session session = CreateFactory.getFactory().openSession();
        session.getTransaction().begin();

        List<Notes> list_obj = new ArrayList<Notes>();

        list_obj.add(Notes.createNote("Note 1", 23));

        list_obj.add(Notes.createNote("Note 2", 32));


        list_obj.get(0).setNote("Updated note.");
        Notes.update(list_obj.get(0), session);

        list_obj.add(Notes.createNote("Note 3", 245));

        Notes.printAll(session);
        Notes.remove(list_obj.get(0), session);
        Notes.printAll(session);


        session.close();

    }
}
