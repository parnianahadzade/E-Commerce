package com.mftplus.ecommerce.api.controller.user;

import com.mftplus.ecommerce.model.entity.Address;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.repository.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AddressRepository addressRepository;

    public UserController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping("/{userId}/address")
    public ResponseEntity<List<Address>> getAddress
            (@AuthenticationPrincipal User user, @PathVariable Long userId){
        if (!userHasPermission(user, userId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(addressRepository.findByUser_Id(userId));
    }

    //add new address
    @PutMapping("/{userId}/address")
    public ResponseEntity<Address> putAddress(
            @AuthenticationPrincipal User user, @PathVariable Long userId,
            @RequestBody Address address){
        if (!userHasPermission(user, userId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        address.setId(null);
        User refUser = new User();
        refUser.setId(userId);
        address.setUser(refUser);

        return ResponseEntity.ok(addressRepository.save(address));

    }

    //edit address
    @PatchMapping("/{userId}/address/{addressId}")
    public ResponseEntity<Address> patchAddress(
            @AuthenticationPrincipal User user, @PathVariable Long userId,
            @PathVariable Long addressId, @RequestBody Address address){
        if (!userHasPermission(user, userId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (address.getId().equals(addressId)){
            Optional<Address> optionalAddress = addressRepository.findById(addressId);

            if (optionalAddress.isPresent()){
                User originalUser = optionalAddress.get().getUser();

                if (originalUser.getId().equals(userId)){
                    address.setUser(originalUser);

                    return ResponseEntity.ok(addressRepository.save(address));
                }
            }
        }

        return ResponseEntity.badRequest().build();
    }

    private boolean userHasPermission(User user, Long id){
        return user.getId().equals(id);
    }
}
