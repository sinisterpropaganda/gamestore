/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author qbuser
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerError extends RuntimeException {

    public ServerError() {
    }

    public ServerError(String string) {
        super(string);
    }

    public ServerError(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ServerError(Throwable thrwbl) {
        super(thrwbl);
    }

    public ServerError(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }

}
