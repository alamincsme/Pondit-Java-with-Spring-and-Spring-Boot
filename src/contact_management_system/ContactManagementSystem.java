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
                    }
            }
        }
    }
}
