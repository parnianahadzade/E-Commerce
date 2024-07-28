package com.mftplus.ecommerce.api.controller.user;

import com.mftplus.ecommerce.repository.AddressRepository;
import com.mftplus.ecommerce.service.impl.UserServiceImpl;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${apiPrefix}/user")
public class UserController {

    private final AddressRepository addressRepository;

    private final UserServiceImpl userService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    public UserController(AddressRepository addressRepository, UserServiceImpl userService, SimpMessagingTemplate simpMessagingTemplate) {
        this.addressRepository = addressRepository;
        this.userService = userService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

//    @GetMapping("/{userId}/address")
//    public ResponseEntity<List<Address>> getAddress
//            (@AuthenticationPrincipal User user, @PathVariable Long userId){
//        if (!userService.userHasPermissionToUser(user, userId)){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//
//        return ResponseEntity.ok(addressRepository.findByUser_Id(userId));
//    }

    //add new address
//    @PutMapping("/{userId}/address")
//    public ResponseEntity<Address> putAddress(
//            @AuthenticationPrincipal User user, @PathVariable Long userId,
//            @RequestBody Address address){
//        if (!userService.userHasPermissionToUser(user, userId)){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//
//        address.setId(null);
//        User refUser = new User();
//        refUser.setId(userId);
//        address.setUser(refUser);
//
//        Address savedAddress = addressRepository.save(address);
//        simpMessagingTemplate.convertAndSend("/topic/user/" + userId + "/address",
//                new DataChange<>(DataChange.ChangeType.INSERT, address));
//
//        return ResponseEntity.ok(savedAddress);
//
//    }

    //edit address
//    @PatchMapping("/{userId}/address/{addressId}")
//    public ResponseEntity<Address> patchAddress(
//            @AuthenticationPrincipal User user, @PathVariable Long userId,
//            @PathVariable Long addressId, @RequestBody Address address){
//        if (!userService.userHasPermissionToUser(user, userId)){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//
//        if (address.getId().equals(addressId)){
//            Optional<Address> optionalAddress = addressRepository.findById(addressId);
//
//            if (optionalAddress.isPresent()){
//                User originalUser = optionalAddress.get().getUser();
//
//                if (originalUser.getId().equals(userId)){
//                    address.setUser(originalUser);
//
//                    Address savedAddress = addressRepository.save(address);
//                    simpMessagingTemplate.convertAndSend("/topic/user/" + userId + "/address",
//                            new DataChange<>(DataChange.ChangeType.UPDATE, address));
//
//                    return ResponseEntity.ok(savedAddress);
//                }
//            }
//        }
//
//        return ResponseEntity.badRequest().build();
//    }

}
