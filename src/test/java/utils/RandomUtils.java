package utils;

import com.github.javafaker.Faker;

public class RandomUtils {

    public static Faker faker = new Faker();

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public static String getNumber() {
        return faker.number().digits(10);
    }

    public static String getBirthMonth() {
        String[] month = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return faker.options().option(month);
    }

    public static String getBirthDay(String month) {
        return switch (month) {
            case "January", "March", "May", "July", "August", "October", "December" ->
                    String.valueOf(faker.number().numberBetween(1, 31));
            case "April", "June", "September", "November" -> String.valueOf(faker.number().numberBetween(1, 30));
            case "February" -> String.valueOf(faker.number().numberBetween(1, 28));
            default -> throw new IllegalArgumentException(month);
        };
    }

    public static String getBirthYear() {
        return String.valueOf(faker.number().numberBetween(1900, 2100));
    }

    public static String getSubjects() {
        return faker.options().option("Maths", "Accounting", "Arts", "Social Studies", "Physics", "Chemistry",
                "Computer Science", "Commerce", "Economics", "Civics", "English", "Hindi", "Biology", "History");
    }

    public static String getHobbies() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    public static String getPicture() {
        return faker.options().option(
                "cyberpunk_1.jpeg",
                "cyberpunk_2.jpeg",
                "cyberpunk_3.jpeg",
                "cyberpunk_4.jpeg");
    }

    public static String getAddress() {
        return faker.address().streetAddress();
    }

    public static String getState() {
        String[] state = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return faker.options().option(state);
    }

    public static String getCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> throw new IllegalArgumentException(state);
        };
    }
}