package com.sales.management.Controller;

import com.sales.management.BaseResponse.APIResponse;
import com.sales.management.BaseResponse.baseresp;
import com.sales.management.DTO.UserDTO;
import com.sales.management.DTO.UserSignupRequestVO;
import com.sales.management.Model.User;
import com.sales.management.Service.UserInterface;
import com.sales.management.ServiceImpl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceimpl;

    @Autowired
    private UserInterface userInterface;


    @PostMapping(value="/add")
    public baseresp<User> Saveuser(@RequestBody UserDTO userDTO){
        baseresp<User> base=null;
        base = baseresp.<User>builder().Data(userServiceimpl.Saveuser(userDTO)).build();
        return base;
    }

    @GetMapping(value = "/login")
    @ApiOperation(value = "user login ")
    public baseresp<UserSignupRequestVO> logOfUser(@RequestBody UserSignupRequestVO userSignupRequestVO) {
        baseresp<UserSignupRequestVO> base=null;
        base = baseresp.<UserSignupRequestVO>builder().Data(userServiceimpl.logOfUser(userSignupRequestVO)).build();
        return base;
    }

     @GetMapping("/{offset}/{pageSize}/{name}")
     private APIResponse<User> getUserWithPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String name) {
     return userServiceimpl.GetUserWithPagination(offset, pageSize, name);
     }


    @PutMapping("/updater")
    public baseresp<Optional<User>> updateuser(@Valid @RequestBody UserDTO userDTO) {
        baseresp<Optional<User>> base=null;
        base=baseresp.<Optional<User>>builder().Data(userServiceimpl.updateUser(userDTO)).build();
        return base ;
    }

    @RolesAllowed(value="USER")
    @GetMapping("/{id}")
    public baseresp<Optional<User>> FindByUserId(@PathVariable int id)  {
        baseresp<Optional<User>> base=null;
        base =baseresp.<Optional<User>>builder().Data(userServiceimpl.FindByUserId(id)).build();
        return base;
    }

    @RolesAllowed(value="ADMIN")
    @PutMapping("/delete")
     public baseresp<Optional<User>> deletesoft(@RequestBody UserDTO userDTO) {
        baseresp<Optional<User>> base=null;
        base=baseresp.<Optional<User>>builder().Data(userServiceimpl.deletesoft(userDTO)).build();
        return base ;
    }






}
