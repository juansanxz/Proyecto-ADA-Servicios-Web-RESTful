package edu.escuelaing.ieti.exception;

import edu.escuelaing.ieti.error.ErrorCodeEnum;
import edu.escuelaing.ieti.error.InternalServerErrorException;
import org.springframework.http.HttpStatus;


public class InvalidCredentialsException extends InternalServerErrorException
{

    public InvalidCredentialsException()
    {
        super( new ServerErrorResponseDto( "Invalid username or password", ErrorCodeEnum.INVALID_USER_CREDENTIALS,
                HttpStatus.UNAUTHORIZED ), HttpStatus.UNAUTHORIZED );
    }
}