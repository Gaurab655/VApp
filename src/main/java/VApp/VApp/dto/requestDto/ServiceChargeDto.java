package VApp.VApp.dto.requestDto;

import VApp.VApp.enums.ServiceChargeType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
