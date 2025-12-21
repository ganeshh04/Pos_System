package com.gatmane.controller;

import com.gatmane.Service.StoreService;
import com.gatmane.Service.UserService;
import com.gatmane.domain.StoreStatus;
import com.gatmane.exceptions.UserException;
import com.gatmane.mapper.StoreMapper;
import com.gatmane.model.Store;
import com.gatmane.model.User;
import com.gatmane.payload.dto.StoreDto;
import com.gatmane.payload.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/stores")
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<StoreDto>createStore(@RequestBody StoreDto storeDto,
                                               @RequestHeader ("Authorization")String jwt) throws UserException {
        User user=userService.getUserFromJwtToken(jwt);
        return  ResponseEntity.ok(storeService.createStore(storeDto,user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto>getStoreById(@PathVariable Long id,
                                               @RequestHeader ("Authorization")String jwt) throws Exception {
        User user=userService.getUserFromJwtToken(jwt);
        return  ResponseEntity.ok(storeService.getStoreById(id));
    }

    @GetMapping
    public ResponseEntity<List<StoreDto>>getAllStore(
                                               @RequestHeader ("Authorization")String jwt) throws Exception {
        User user=userService.getUserFromJwtToken(jwt);
        return  ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/admin")
    public ResponseEntity<StoreDto>getStoreByAdmin(
            @RequestHeader ("Authorization")String jwt) throws Exception {
        User user=userService.getUserFromJwtToken(jwt);
        return  ResponseEntity.ok(StoreMapper.toDTO(storeService.getStoreByAdmin()));
    }

    @GetMapping("/employee")
    public ResponseEntity<StoreDto>getStoreByEmployee(
            @RequestHeader ("Authorization")String jwt) throws Exception {
        return  ResponseEntity.ok(storeService.getStoreByEmployee());
    }
    @PutMapping("/{id}")
    public ResponseEntity<StoreDto>updateStore(@PathVariable Long id,
                                               @RequestBody StoreDto storeDto) throws Exception {
        return ResponseEntity.ok(storeService.updateStore(id,storeDto));
    }

    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDto>moderateStore(@PathVariable Long id,
                                               @RequestParam StoreStatus status
                                               ) throws Exception {
        return ResponseEntity.ok(storeService.moderateStore(id,status));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse>deleteStore(@PathVariable Long id) throws UserException {
        storeService.deleteStore(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("store deletedSuccessfully");
        return ResponseEntity.ok(apiResponse);

    }
}
