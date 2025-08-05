package com.vishal.saloon_service.payload.dto;

import java.util.UUID;

public record UserDto(UUID userId, String fullName, String email) {

}
