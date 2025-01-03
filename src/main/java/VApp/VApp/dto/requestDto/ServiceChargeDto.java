package VApp.VApp.dto.requestDto;

import VApp.VApp.enums.ServiceChargeType;
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
    @Column(name = "min_amount", nullable = false)
    private double minAmount;

    @Column(name = "max_amount", nullable = false)
    private double maxAmount;
    @NotNull
    private double discount;
    @NotNull
    private ServiceChargeType type;
}
