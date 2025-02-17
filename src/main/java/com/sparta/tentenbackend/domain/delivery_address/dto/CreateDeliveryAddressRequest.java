package com.sparta.tentenbackend.domain.delivery_address.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateDeliveryAddressRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String address;
    private String detailAddress;
    @NotBlank
    private String townCode;
}
