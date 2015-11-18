package com.awakenguys.kmitl.ladkrabangcountry.controller;

import com.awakenguys.kmitl.ladkrabangcountry.AnnounceRepo;
import com.awakenguys.kmitl.ladkrabangcountry.UserRepo;
import com.awakenguys.kmitl.ladkrabangcountry.model.Announce;
import com.awakenguys.kmitl.ladkrabangcountry.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AnnounceController {
    @Autowired
    AnnounceRepo announceRepo;
    @Autowired
    UserRepo userRepo;
    @RequestMapping(value = "/addannounce",method= RequestMethod.GET)
    public @ResponseBody Announce addAnnounce(@RequestParam(value = "topic",required = true) String topic,
                                      @RequestParam(value = "content",required = true) String content,
                                      @RequestParam(value = "img_path", required = false, defaultValue = "null") String img_path,
                                      @RequestParam(value =  "authorId", required = true) String authorId) {
        Announce announce;
        User author = userRepo.findOne(authorId);
        if(img_path=="null") announce = new Announce(topic, content, author.getName());
        else  announce = new Announce(topic, content, img_path, author.getName());
        announceRepo.save(announce);
        return announce;
    }

    @RequestMapping(value = "/deleteannounce",method= RequestMethod.GET)
    public @ResponseBody String deleteAnnounce(@RequestParam(value = "id",required = true) String id) {
        announceRepo.delete(id);
        return "success";
    }

    @RequestMapping(value = "/deleteallannounces",method= RequestMethod.GET)
    public @ResponseBody String deleteAllAnnounce(){
        announceRepo.deleteAll();
        return "all announces were deleted.";
    }

}