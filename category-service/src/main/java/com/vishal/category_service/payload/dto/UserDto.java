package com.vishal.category_service.payload.dto;

import java.util.UUID;

public record UserDto(UUID id, String fullName, String email) {

}
