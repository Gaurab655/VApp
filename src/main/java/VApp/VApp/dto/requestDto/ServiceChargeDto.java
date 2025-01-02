package VApp.VApp.dto.requestDto;

import VApp.VApp.enums.ServiceChargeType;
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
    @NotNull
    private String amountRange;
    @NotNull
    private double discount;
    @NotNull
    private ServiceChargeType type;
}
