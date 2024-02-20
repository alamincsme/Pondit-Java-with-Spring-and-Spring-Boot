package contact_management_system;

import java.io.*;
import java.util.*;

class Contact implements Comparable<Contact> {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and setters

    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}

class ContactManager {
    private TreeSet<Contact> contacts = new TreeSet<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void viewAllContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public Contact searchContactByName(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void updateContact(Contact oldContact, Contact newContact) {
        contacts.remove(oldContact);
        contacts.add(newContact);
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    public void exportContactsToFile(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(contacts);
        }
    }

    public void importContactsFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            TreeSet<Contact> importedContacts = (TreeSet<Contact>) ois.readObject();
            contacts.addAll(importedContacts);
        }
    }
}

public class ContactManagementSystem {
    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Contact\n2. View All Contacts\n3. Search Contact\n" +
                    "4. Update Contact\n5. Delete Contact\n6. Export Contacts\n7. Import Contacts\n8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    // Add Contact
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNumber, email);
                    contactManager.addContact(newContact);
                    break;
                case 2:
                    // View All Contacts
                    contactManager.viewAllContacts();
                    break;
                case 3:
                    // Search Contact
                    System.out.print("Enter Name to search: ");
                    String searchName = scanner.nextLine();
                    Contact foundContact = contactManager.searchContactByName(searchName);
                    if (foundContact != null) {
                        System.out.println("Contact found: " + foundContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 4:
                    // Update Contact
                    System.out.print("Enter Name to update: ");
                    String updateName = scanner.nextLine();
                    Contact existingContact = contactManager.searchContactByName(updateName);
                    if (existingContact != null) {
                        System.out.print("Enter New Phone Number: ");
                        String newPhoneNumber = scanner.nextLine();
                        System.out.print("Enter New Email: ");
                        String newEmail = scanner.nextLine();
                        Contact updatedContact = new Contact(updateName, newPhoneNumber, newEmail);
                        contactManager.updateContact(existingContact, updatedContact);
                        System.out.println("Contact updated successfully.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 5:
                    // Delete Contact
                    System.out.print("Enter Name to delete: ");
                    String deleteName = scanner.nextLine();
                    Contact contactToDelete = contactManager.searchContactByName(deleteName);
                    if (contactToDelete != null) {
                        contactManager.deleteContact(contactToDelete);
                        System.out.println("Contact deleted successfully.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 6:
                    // Export Contacts
                    System.out.print("Enter file name for export: ");
                    String exportFileName = scanner.nextLine();
                    try {
                        contactManager.exportContactsToFile(exportFileName);
                        System.out.println("Contacts exported successfully.");
                    } catch (IOException e) {
                        System.out.println("Error exporting contacts: " + e.getMessage());
                    }
                    break;
                case 7:
                    // Import Contacts
                    System.out.print("Enter file name for import: ");
                    String importFileName = scanner.nextLine();
                    try {
                        contactManager.importContactsFromFile(importFileName);
                        System.out.println("Contacts imported successfully.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error importing contacts: " + e.getMessage());
                    }
                    break;
                case 8:
                    // Exit
                    System.out.println("Exiting Contact Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }
    }
}
