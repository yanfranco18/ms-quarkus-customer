package com.bancario.customerservice.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider // Quarkus registra este componente automáticamente
@Slf4j
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        log.error("Unhandled exception occurred: {}", exception.getMessage(), exception);

        // Puedes personalizar la respuesta según el tipo de excepción
        if (exception instanceof IllegalArgumentException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error de validación: " + exception.getMessage())
                    .build();
        }

        // Respuesta por defecto para cualquier otra excepción
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("An unexpected error occurred. Please try again later.")
                .build();
    }
}
