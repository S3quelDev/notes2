package com.example.notes2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

@Entity
@Table (name = "notes", schema = "notes", catalog = "postgres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "note")
    private String note;

    @Column (name = "number")
    private int number;

    public static void update(Notes note, Session session) {
        session.update(note);
        System.out.println("Object updated");
    }
    public static void remove(Notes note, Session session) {
        session.remove(note);
    }

    public static void save(Notes note, Session session) {
        session.save(note);
        session.getTransaction().commit();
    }

    public static String printAll(Session session) {
        String s = "";
        List<Notes> list = CreateFactory.loadAllData(Notes.class, session);

        for (Notes note : list) s += note.getId() + " " + note.getNote() + " " + note.getNumber();

        return s;
    }

    public static Notes createNote(String note, int number) {
        Session session = CreateFactory.getFactory().openSession();
        session.getTransaction().begin();

        Notes note_obj = new Notes();
        note_obj.setNote(note);
        note_obj.setNumber(number);
        session.save(note_obj);
        session.getTransaction().commit();

        return note_obj;
    }
}
