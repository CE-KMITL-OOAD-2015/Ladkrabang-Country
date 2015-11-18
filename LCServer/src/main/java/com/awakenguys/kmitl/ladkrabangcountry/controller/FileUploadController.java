package com.awakenguys.kmitl.ladkrabangcountry.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awakenguys.kmitl.ladkrabangcountry.UserRepo;
import com.awakenguys.kmitl.ladkrabangcountry.model.FileUpload;
import com.awakenguys.kmitl.ladkrabangcountry.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;
import java.io.File;


public class FileUploadController {
    @Autowired
    UserRepo userRepo;
    private final AtomicLong counter = new AtomicLong();
    @RequestMapping(value = { "/uploadimage" }, method = RequestMethod.POST)
    public @ResponseBody String uploadImage(@RequestParam(value = "img_path",required = true) MultipartFile file,
                                            @RequestParam(value = "authorId",required = true) String userId,
                                            HttpServletRequest request, HttpServletResponse response) {

        User author  = userRepo.findOne(userId);
        String fileName = author.getId()+"_"+counter.incrementAndGet();
        Path path = null;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(fileName)));
                path = Paths.get("/opt/stuff/uploads", fileName);
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                return "failed";
            }
        } else {
            return "failed";
        }
        return path.toString();
    }

}
