package glucobot.glucobotapi.service;

import glucobot.glucobotapi.data.model.User;
import glucobot.glucobotapi.data.repository.UserRepository;
import glucobot.glucobotapi.service.exception.PatientNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getPatients() {
        return userRepository.findAllPatients();
    }

    public User getPatientById(long patientId) throws PatientNotFoundException {
        Optional<User> userOptional = userRepository.findPatientById(patientId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new PatientNotFoundException();
        }
    }
}
