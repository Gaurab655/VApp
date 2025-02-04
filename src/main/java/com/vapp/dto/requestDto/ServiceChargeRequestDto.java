package com.vapp.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import com.vapp.enums.ServiceChargeTypeEnum;

import java.math.BigDecimal;

@Getter
@Setter
public class ServiceChargeRequestDto {

    @NotNull(message = "Insert min-Amount")
    private BigDecimal minAmount;

    @NotNull(message = "Insert max-Amount")
    private BigDecimal maxAmount;

    @NotNull(message = "Insert amount")
    private BigDecimal charge;

    @NotNull
    private ServiceChargeTypeEnum type;
}
