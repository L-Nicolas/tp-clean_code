package fr.esgi.cleancode.service;

import fr.esgi.cleancode.database.InMemoryDatabase;
import fr.esgi.cleancode.exception.ResourceNotFoundException;
import fr.esgi.cleancode.model.DrivingLicence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@RequiredArgsConstructor
public class DrivingLicenceRemovePointsService {

    private final InMemoryDatabase database;

    public DrivingLicence removePoints(int points, UUID id) throws ResourceNotFoundException {
        var drivingLicenceDb = database.findById(id);

        return drivingLicenceDb.map( drivingLicence -> {
            drivingLicence.withAvailablePoints(drivingLicence.getAvailablePoints() - points);
            return drivingLicence;
        }).orElseThrow(() -> new ResourceNotFoundException("License introuvable"));
    }
}
