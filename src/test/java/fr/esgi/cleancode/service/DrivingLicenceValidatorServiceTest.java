package fr.esgi.cleancode.service;

import fr.esgi.cleancode.exception.InvalidDriverSocialSecurityNumberException;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceValidatorServiceTest {

    private final DrivingLicenceValidatorService service = new DrivingLicenceValidatorService();

    @Test
    void should_validate() {
        final var actual = "123456789012345";
        final var drivingLicence = DrivingLicence.builder().driverSocialSecurityNumber(actual).build();
        assertThat(service.isValidSocialSecurityNumber(drivingLicence)).isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"lorem ipsum","azertyuiop","3","978AZ67ZRC"})
    void should_not_validate(String invalidSSNumber) {
        final var drivingLicence = DrivingLicence.builder().driverSocialSecurityNumber(invalidSSNumber).build();
        assertThatExceptionOfType(InvalidDriverSocialSecurityNumberException.class).isThrownBy(() -> service.isValidSocialSecurityNumber(drivingLicence));
    }
}
