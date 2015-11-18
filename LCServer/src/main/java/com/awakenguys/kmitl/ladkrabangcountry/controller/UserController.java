package com.awakenguys.kmitl.ladkrabangcountry.controller;

import com.awakenguys.kmitl.ladkrabangcountry.UserRepo;
import com.awakenguys.kmitl.ladkrabangcountry.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    UserRepo userRepo;
    @RequestMapping(value = "/adduser",method= RequestMethod.GET)
    public @ResponseBody User addUser(@RequestParam(value = "fbId",required = true) String fbId,
                                      @RequestParam(value = "name",required = true) String name,
                                      @RequestParam(value = "level", required = false, defaultValue = "1") int level){
        User user = new User(fbId,name,level);
        userRepo.save(user);
        return user;
    }

    @RequestMapping(value = "/deleteallusers",method= RequestMethod.GET)
    public @ResponseBody String deleteAllUser(){
        userRepo.deleteAll();
        return "all users were deleted.";
    }

    @RequestMapping(value = "/ban",method= RequestMethod.GET)
    public @ResponseBody String deleteUser(@RequestParam(value = "id",required = true) String id){
        User user = userRepo.findOne(id);
        userRepo.save(new User(user.getFbId(),user.getName(),user.getLevel(),true));
        userRepo.delete(id);
        return "user id "+id+" was banned.";
    }
}
