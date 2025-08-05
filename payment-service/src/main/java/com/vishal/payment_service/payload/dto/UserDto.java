package com.vishal.payment_service.payload.dto;

import java.util.UUID;

public record UserDto(UUID userId, String fullName, String email) {

}
