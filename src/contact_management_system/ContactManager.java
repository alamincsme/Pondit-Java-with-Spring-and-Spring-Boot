package contact_management_system;

import java.util.TreeSet;

public class ContactManager {

    private TreeSet<Contact> contacts = new TreeSet<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void viewAllContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public Contact searchContactByName(String searchName) {

        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(searchName)) {
                return  contact;
            }
        }
        return null;
    }
}
