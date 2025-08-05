package com.vishal.service_offring_service.payload.dto;

import java.util.UUID;

public record UserDto(UUID userId, String fullName, String email) {

}
