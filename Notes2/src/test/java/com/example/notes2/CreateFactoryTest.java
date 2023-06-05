package com.example.notes2;


import org.checkerframework.checker.units.qual.C;
import org.hibernate.AssertionFailure;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateFactoryTest {

    @Test
    public void testFactoryCreation () {
        boolean finished = false;
        try {
            Session session = CreateFactory.getFactory().openSession();
            if (session.isConnected()) {
                finished = true;
            }
            assertTrue(finished);
        } catch (AssertionFailure e) {
            System.out.println("Assertion failed");
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCanGetDataFromDB () {
        try {
            Session session = CreateFactory.getFactory().openSession();
            boolean finished = false;

            String s = "";
            s = Notes.printAll(session);

            if (!s.equals("")) {
                finished = true;
            }

            assertTrue(finished);
        } catch (AssertionFailure e) {
            System.out.println("Assertion failed");
            System.out.println(e.getMessage());
        }


    }
}