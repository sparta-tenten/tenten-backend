package com.sparta.tentenbackend.domain.delivery_address.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.Getter;

@Getter
public class UpdateDeliveryAddressRequest {

    @NotBlank
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    private String detailAddress;
    @NotBlank
    private String townCode;
}
