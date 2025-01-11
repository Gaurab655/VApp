package com.vapp.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import com.vapp.enums.ServiceChargeTypeEnum;

@Getter
@Setter
public class ServiceChargeRequestDto {

    @NotNull(message = "Insert min-Amount")
    private double minAmount;

    @NotNull(message = "Insert max-Amount")
    private Double maxAmount;

    @NotNull(message = "Insert amount")
    private double charge;

    @NotNull
    private ServiceChargeTypeEnum type;
}
