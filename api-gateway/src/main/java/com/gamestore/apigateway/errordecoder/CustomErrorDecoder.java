/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.apigateway.errordecoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gamestore.apigateway.exception.BadRequestException;
import com.gamestore.apigateway.exception.ConflictException;
import com.gamestore.apigateway.exception.ExceptionBody;
import com.gamestore.apigateway.exception.NotFoundException;
import com.google.common.io.CharStreams;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author qbuser
 */
@Component
public class CustomErrorDecoder implements ErrorDecoder {

    private static final org.slf4j.Logger LOGGER
            = LoggerFactory.getLogger(CustomErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            String responseBody = CharStreams.toString(response.body().asReader());
            ExceptionBody exceptionBody = objectMapper.readValue(responseBody, ExceptionBody.class);
            String message = exceptionBody.getMessage();
            switch (response.status()) {
                case 404:
                    return new NotFoundException(message);
                case 409:
                    return new ConflictException(message);
                case 400:
                    return new BadRequestException(message);
                default:
                    return new ResponseStatusException(HttpStatus.resolve(response.status()), responseBody.replace("\"", ""));
            }
        } catch (IOException ex) {
            LOGGER.warn("Error while decoding error response from service: {}", ex);
            return new IOException("CANNOT_DESERIALIZE_RESPONSE");
        }

    }

}
