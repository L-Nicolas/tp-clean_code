package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;

import java.util.regex.Pattern;

public class DrivingLicenceValidatorService {
    public boolean isValidSocialSecurityNumber(DrivingLicence drivingLicence) throws InvalidDriverSocialSecurityNumberException {
        var actual = drivingLicence.getDriverSocialSecurityNumber();
        if(actual == null) {
            throw  new InvalidDriverSocialSecurityNumberException("Null");
        } else if (actual.length() != 15) {
            throw  new InvalidDriverSocialSecurityNumberException("Only 15 digts are accepted");
        } else if (!Pattern.matches("\\d*", actual)) {
            throw  new InvalidDriverSocialSecurityNumberException("The security number can only contains digits");
        }
        return true;
    }
}
