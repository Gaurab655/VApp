package VApp.VApp.service;

import VApp.VApp.dto.requestDto.ServiceChargeDto;
import VApp.VApp.dto.responseDto.UserResponseDto;
import VApp.VApp.entity.ServiceCharge;
import VApp.VApp.entity.User;
import VApp.VApp.exception.BankException;
import VApp.VApp.repository.ServiceChargeRepo;
import VApp.VApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ServiceChargeRepo serviceChargeRepo;


    public ResponseEntity<ServiceChargeDto> insertServiceCharge(ServiceChargeDto serviceChargeDto) throws BankException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User admin = userRepository.findByEmail(email).orElseThrow(() -> new BankException("No admin found", HttpStatus.NOT_FOUND));
        if (admin != null) {
            ServiceCharge serviceCharge = this.modelMapper.map(serviceChargeDto, ServiceCharge.class);
            serviceChargeRepo.save(serviceCharge);
            return new ResponseEntity<>(serviceChargeDto, HttpStatus.CREATED);
        }
        return null;
    }

    public List<UserResponseDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> UserResponseDto.fromEntity(user, modelMapper)).toList();
    }
}
