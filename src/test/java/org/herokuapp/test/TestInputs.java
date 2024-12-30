package org.herokuapp.test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestInputs {
        public List<String[]> contactsList = new ArrayList<>();
        public List<List<String>> contactDetail = new ArrayList<>();
        public List<String> findingValues = Arrays.asList("Adam Davis", "Joe Smith");
        public List<String> deleteValues = Arrays.asList("Kishore Brown");
        public enum ContactField {
            FIRST_NAME,
            LAST_NAME,
            DOB,
            EMAIL,
            PHONE_NUMBER,
            ADDRESS_ONE,
            ADDRESS_TWO,
            CITY,
            STATE,
            POSTAL_CODE,
            COUNTRY
        }
    
        public TestInputs() {
        contactsList.add(new String[]{"John", "Doe", "1990-01-01", "john.doe@example.com", "1234567890", "123 Main St", "Apt 4B", "New York", "NY", "10001", "USA"});
        contactsList.add(new String[]{"Joe", "Smith", "1985-02-02", "jane.smith@example.com", "9876543210", "456 Maple Ave", "Suite 10", "Los Angeles", "CA", "90001", "USA"});
        contactsList.add(new String[]{"Henry", "Johnson", "1992-03-03", "michael.j@example.com", "1112223333", "789 Oak St", "Floor 2", "Chicago", "IL", "60601", "USA"});
        contactsList.add(new String[]{"Adam", "Davis", "1988-04-04", "emily.d@example.com", "2223334444", "987 Elm St", "Room 101", "Houston", "TX", "77001", "USA"});
        contactsList.add(new String[]{"Kishore", "Brown", "1993-05-05", "william.b@example.com", "3334445555", "654 Pine St", "Suite 203", "Phoenix", "AZ", "85001", "USA"});
        contactsList.add(new String[]{"Olivia", "Martinez", "1991-06-06", "olivia.m@example.com", "4445556666", "321 Cedar St", "Apt 2A", "San Diego", "CA", "92101", "USA"});
        contactsList.add(new String[]{"James", "Garcia", "1994-07-07", "james.g@example.com", "5556667777", "876 Spruce St", "Unit 6B", "Dallas", "TX", "75201", "USA"});
        contactsList.add(new String[]{"Sophia", "Miller", "1987-08-08", "sophia.m@example.com", "6667778888", "432 Birch St", "Penthouse", "San Francisco", "CA", "94101", "USA"});
        contactsList.add(new String[]{"Lucas", "Wilson", "1995-09-09", "lucas.w@example.com", "7778889999", "345 Poplar St", "Level 4", "Miami", "FL", "33101", "USA"});
        contactsList.add(new String[]{"Mia", "Anderson", "1990-10-10", "mia.a@example.com", "8889990000", "789 Redwood St", "Cabin 5", "Seattle", "WA", "98101", "USA"});
        
        
     // Add your test data
        contactDetail.add(Arrays.asList("Venkatesan", null, null, null, null, null, null, null, null, null, null));
        contactDetail.add(Arrays.asList("anjali", null, null, null, null, null, null, null, null, null, null));
        contactDetail.add(Arrays.asList("Naveen", null, null, null, null, null, null, null, null, null, null));
        
     }
     
   

    
}
