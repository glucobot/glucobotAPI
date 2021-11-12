package glucobot.glucobotapi.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "patient not found")
public class PatientNotFoundException extends Exception {
}