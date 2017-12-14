package com.acme.bank.loan.registration.web.helper;

import java.util.UUID;

import com.acme.bank.loan.registration.web.exception.BadRequestException;

public final class WebHelper {

    private WebHelper() {
    }

    public static UUID parseUUID(String uuid) {
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage(), e);
        }
    }
}
