package vapp.dto.requestDto;

import vapp.enums.ServiceChargeTypeEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ServiceChargeDto {
    @Column(name = "min_amount")
    @NotNull(message = "Insert min-Amount")
    private double minAmount;

    @Column(name = "max_amount")
    @NotNull(message = "Insert max-Amount")
    private Double maxAmount;

    @NotNull(message = "Insert amount")
    private double charge;

    @NotNull
    private ServiceChargeTypeEnum type;
}
