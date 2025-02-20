package loicMangele.Direct237_20.exceptions;

import loicMangele.Direct237_20.dto.ErrorsResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsResponseDTO handleBadrequest(BadRequestException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorsResponseDTO handleUnauthorized(UnauthorizedException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    public ErrorsResponseDTO handleForbidden(AuthorizationDeniedException ex) {
        return new ErrorsResponseDTO("Non hai i permessi per accedere", LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorsResponseDTO handleGeneric(Exception ex) {
        ex.printStackTrace();
        return new ErrorsResponseDTO("Problema lato server! Giuro che risolveremo presto!", LocalDateTime.now());
    }

    @ExceptionHandler(AdminNotFoundByEmailException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsResponseDTO handleAdminNotFoundByEmail(AdminNotFoundByEmailException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(AdminNotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsResponseDTO handleAdminNotFoundById(AdminNotFoundByIdException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(AdminNotFoundByNameException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsResponseDTO handleAdminNotFoundByName(AdminNotFoundByNameException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ClientNotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsResponseDTO handleClientNotFoundById(ClientNotFoundByIdException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ReservationNotFoundByClientException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsResponseDTO handleReservationNotFoundByClient(ReservationNotFoundByClientException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ReservationNotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsResponseDTO handleReservationNotFoundById(ReservationNotFoundByIdException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ReservationNotFoundByTrackingException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsResponseDTO handleReservationNotFoundByTracking(ReservationNotFoundByTrackingException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ReservationNotFoundByVoyageException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsResponseDTO handleReservationNotFoundByVoyage(ReservationNotFoundByVoyageException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(VoyageNotFoundByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsResponseDTO handleVoyageNotFoundById(VoyageNotFoundByIdException ex) {
        return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
    }


}
