package com.awakenguys.kmitl.ladkrabangcountry;

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
    public @ResponseBody void addUser(@RequestParam(value = "fb_id",required = true) String fb_id,
                                      @RequestParam(value = "name",required = true) String name,
                                      @RequestParam(value = "level", required = false, defaultValue = "1") int level){
        User user = new User(fb_id,name,level);
        userRepo.save(user);
    }

    @RequestMapping(value = "/deleteuser",method= RequestMethod.GET)
    public @ResponseBody void deleteAllUser(){
        userRepo.deleteAll();
    }
}
