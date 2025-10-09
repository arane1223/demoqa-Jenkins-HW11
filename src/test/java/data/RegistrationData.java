package data;

import static utils.RandomUtils.*;
import static utils.RandomUtils.getRandomAddress;
import static utils.RandomUtils.getRandomBirthDay;
import static utils.RandomUtils.getRandomBirthMonth;
import static utils.RandomUtils.getRandomBirthYear;
import static utils.RandomUtils.getRandomCity;
import static utils.RandomUtils.getRandomGender;
import static utils.RandomUtils.getRandomHobbies;
import static utils.RandomUtils.getRandomNumber;
import static utils.RandomUtils.getRandomPicture;
import static utils.RandomUtils.getRandomSecondAddress;
import static utils.RandomUtils.getRandomState;
import static utils.RandomUtils.getSubjects;

public class RegistrationData {
    public static String
            firstName = getRandomFirstName(),
            lastName = getRandomLastName(),
            userEmail = getRandomEmail(),
            gender = getRandomGender(),
            userNumber = getRandomNumber(),
            birthDay = getRandomBirthDay(getRandomBirthMonth()),
            birthMonth = getRandomBirthMonth(),
            birthYear = getRandomBirthYear(),
            subjects = getSubjects(),
            hobbies = getRandomHobbies(),
            picture = getRandomPicture(),
            address = getRandomAddress(),
            secondAddress = getRandomSecondAddress(),
            state = getRandomState(),
            city = getRandomCity(state);
}
