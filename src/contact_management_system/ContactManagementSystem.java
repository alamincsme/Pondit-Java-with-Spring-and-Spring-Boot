package contact_management_system;

import java.util.Scanner;

public class ContactManagementSystem {
    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Contact\n2. ViewAllContacts\n3. Search Contact\n" +
                    "4. Update Contact\n5. Delete Contact");

            System.out.println("Enter Your Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 :
                    System.out.println("Enter Your Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter Your PhoneNumber: ");
                    String phoneNo = scanner.nextLine();
                    System.out.println("Enter Your Email: ");
                    String email = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNo, email);
                    contactManager.addContact(newContact);
                    break;

                case 2:
                    contactManager.viewAllContacts();
                    break;

                case 3:
                    System.out.println("Enter name to search: ");
                    String searchName = scanner.nextLine();
                    Contact foundContact = contactManager.searchContactByName(searchName);
                    if (foundContact != null) {
                        System.out.println("Contact found : " + foundContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 4:
                    System.out.println("Enter name to update: ");
                    String updateName = scanner.nextLine();
                    Contact existContact = contactManager.searchContactByName(updateName);
                    if (existContact != null) {
                        System.out.println("Enter new Phone Number:");
                        String newPhoneNo = scanner.nextLine();
                        System.out.println("Enter new Email: ");
                        String newEmail = scanner.nextLine();
                        Contact updatedContact = new Contact(updateName, newPhoneNo, newEmail);
                        contactManager.updateContact(existContact, updatedContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 5:
                    System.out.println("Enter name to delete: ");
                    Contact deleteContact = contactManager.searchContactByName(scanner.nextLine());
                    if (deleteContact != null) {
                            contactManager.deleteContact(deleteContact);
                        System.out.println("Contact deleted successfully.");
                    } else {
                        System.out.println("contact not found.");
                    }
                    break;
                case 6:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!. Please Enter between 1 to 6.");

            }
        }
    }
}
