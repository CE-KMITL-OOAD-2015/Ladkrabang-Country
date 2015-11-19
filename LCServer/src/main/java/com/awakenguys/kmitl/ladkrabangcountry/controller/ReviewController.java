package com.awakenguys.kmitl.ladkrabangcountry.controller;

import com.awakenguys.kmitl.ladkrabangcountry.ReviewRepo;
import com.awakenguys.kmitl.ladkrabangcountry.UserRepo;
import com.awakenguys.kmitl.ladkrabangcountry.model.Review;
import com.awakenguys.kmitl.ladkrabangcountry.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class ReviewController {
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    UserRepo userRepo;

    @RequestMapping(value = "/addreviewwithimg",method= RequestMethod.POST)
    public @ResponseBody Review addReviewWithImg(HttpServletRequest request,
                                                 @RequestParam(value = "topic",required = true) String topic,
                                                 @RequestParam(value = "content",required = true) String content,
                                                 @RequestParam(value = "img", required = false, defaultValue = "null") String img,
                                                 @RequestParam(value = "authorId", required = true) String authorId){
        Review review;
        User author = userRepo.findOne(authorId);
        if(img=="null") review = new Review(topic, content, authorId, author.getName());
        else review = new Review(topic, content, img, authorId, author.getName());
        reviewRepo.save(review);
        return review;
    }

    @RequestMapping(value = "/addreview",method= RequestMethod.GET)
    public @ResponseBody Review addReview(@RequestParam(value = "topic",required = true) String topic,
                                      @RequestParam(value = "content",required = true) String content,
                                      @RequestParam(value = "img", required = false, defaultValue = "null") String img,
                                      @RequestParam(value = "authorId", required = true) String authorId){
        Review review;
        User author = userRepo.findOne(authorId);
        if(img=="null") review = new Review(topic, content, authorId, author.getName());
        else review = new Review(topic, content, img, authorId, author.getName());
        reviewRepo.save(review);
        return review;
    }

    @RequestMapping(value = "ratereview", method=RequestMethod.GET)
    public @ResponseBody Review rateReview(@RequestParam(value =  "userId",required = true) String userId,
                                           @RequestParam(value = "reviewId", required = true) String reviewId,
                                           @RequestParam(value = "rating", required = true) int rating){
        User user = userRepo.findOne(userId);
        Review review = reviewRepo.findOne(reviewId);
        HashMap<String,Integer> ratings = review.getRatings();
        if(!ratings.containsKey(userId)) ratings.put(userId,rating);
        else ratings.replace(userId,rating);
        float sum = 0.0f;
        for (float f : ratings.values()) {
            sum += f;
        }
        review.setRating(sum/ratings.size());
        reviewRepo.save(review);
        return review;
    }

    @RequestMapping(value = "reviewratingbyid", method=RequestMethod.GET)
    public @ResponseBody int getRatingById(@RequestParam(value =  "userId",required = true) String userId,
                                           @RequestParam(value = "reviewId", required = true) String reviewId){
        User user = userRepo.findOne(userId);
        Review review = reviewRepo.findOne(reviewId);
        HashMap<String,Integer> ratings = review.getRatings();
        if(ratings.containsKey(userId)) return ratings.get(userId);
        else return 0;
    }
    @RequestMapping(value = "/deletereview",method= RequestMethod.GET)
    public @ResponseBody String deleteReview(@RequestParam(value = "id",required = true) String id) {
        reviewRepo.delete(id);
        return "success";
    }

    @RequestMapping(value = "/deleteallreviews",method= RequestMethod.GET)
    public @ResponseBody String deleteAllReview(){
        reviewRepo.deleteAll();
        return "all reviews were deleted.";
    }

}