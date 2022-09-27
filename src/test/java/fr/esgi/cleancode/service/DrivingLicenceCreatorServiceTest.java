package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.model.DrivingLicence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DrivingLicenceCreatorServiceTest {
    @InjectMocks
    private DrivingLicenceCreateService service;

    @Mock
    private InMemoryDatabase database;

    @Mock
    private DrivingLicenceIdGenerationService serviceIdGenerator;

    @Mock
    private DrivingLicenceValidatorService serviceValidator;

    @Test
    void should_create_licence() {
        final var id = UUID.randomUUID();
        final var socialSecurityNumber = "18273792889N";
        final var drivingLicence = DrivingLicence.builder().driverSocialSecurityNumber(socialSecurityNumber).id(id).build();

        when(serviceIdGenerator.generateNewDrivingLicenceId()).thenReturn(id);
        when(serviceValidator.isValidSocialSecurityNumber(drivingLicence)).thenReturn(true);
        when(database.save(id, drivingLicence)).thenReturn(drivingLicence);

        final var actual = service.save(drivingLicence);

        assertThat(actual).isEqualTo(drivingLicence);
        verify(database).save(id, drivingLicence);
        verifyNoMoreInteractions(database);
        verifyNoMoreInteractions(serviceValidator);
    }
}
