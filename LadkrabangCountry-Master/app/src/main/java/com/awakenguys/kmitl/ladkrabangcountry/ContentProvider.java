package com.awakenguys.kmitl.ladkrabangcountry;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;
import com.awakenguys.kmitl.ladkrabangcountry.model.Review;
import com.awakenguys.kmitl.ladkrabangcountry.model.User;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.MediaType;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;



public class ContentProvider {

    private final String url = "http://203.151.92.199:8888/";
    private Traverson traverson;
    //private Traverson.TraversalBuilder traversalBuilder;

    public List<Place> getAllPlaces() throws URISyntaxException{
        traverson = new Traverson(new URI(url+"places?sort=name,asc"), MediaTypes.HAL_JSON);
        int all = traverson.follow("$._links.self.href").toObject("$.page.totalElements");
        int size = traverson.follow("$._links.self.href").toObject("$.page.size");
        int pages = traverson.follow("$._links.self.href").toObject("$.page.totalPages");
        List<Place> places = new ArrayList<Place>();
        Place place = new Place();
        for(int i = 0,j = 0;i<all && j<size;i++){
            Traverson.TraversalBuilder traversalBuilder = traverson
                    .follow("$._embedded.places[" + j + "]._links.self.href");
            place = traversalBuilder.toObject(Place.class);
            places.add(place);
            if(j==size){
                traverson.follow("next");
                j=0;
            }
        }
        return places;
    }

    public List<Place> getPlacesByNameLike(String str)  {
        List places = new ArrayList<Place>();
        try {
            traverson = new Traverson(new URI(url + "places/search/findByNameLikeIgnoreCaseOrderByNameAsc?name=" + str.replace(" ","+")), MediaTypes.HAL_JSON);
            Place place = new Place();
            int i = 0;
            while(true){
                Traverson.TraversalBuilder traversalBuilder = traverson
                        .follow("$._embedded.places[" + i + "]._links.self.href");
                place = traversalBuilder.toObject(Place.class);
                if(place.getName()==null) break;
                places.add(place);
                i++;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            return places;
        }
    }



    public List<Place> getPlacesByCategory(String str) throws URISyntaxException {
        List places = new ArrayList<Place>();
        try {
            traverson = new Traverson(new URI(url + "places/search/findByCategoryLikeOrderByNameAsc?category=" + str.replace(" ","+")), MediaTypes.HAL_JSON);
            Place place = new Place();
            int i = 0;
            while(true){
                Traverson.TraversalBuilder traversalBuilder = traverson
                        .follow("$._embedded.places[" + i + "]._links.self.href");
                place = traversalBuilder.toObject(Place.class);
                if(place.getName()==null) break;
                places.add(place);
                i++;
            }
        } catch (URISyntaxException e) {
            throw e;
        } finally {
            return places;
        }
    }

    public List<String> getPlacesNameByCategory(String str) throws URISyntaxException{
        List names = new ArrayList<String>();
        if(str.equals("ทั้งหมด")){
            traverson = new Traverson(new URI(url+"places?sort=name,asc"), MediaTypes.HAL_JSON);
            int size = traverson.follow("$._links.self.href").toObject("$.page.totalElements");
            List<Place> places = new ArrayList<Place>();
            String name = null;
            for(int i = 0;i<size;i++){
                Traverson.TraversalBuilder traversalBuilder = traverson
                        .follow("$._embedded.places[" + i + "]._links.self.href");
                name = traversalBuilder.toObject("$.name");
                names.add(name);
            }
            return names;
        }
        else {
            try {
                traverson = new Traverson(new URI(url + "places/search/findByCategoryLikeOrderByNameAsc?category=" + str.replace(" ","+")), MediaTypes.HAL_JSON);
                String name;
                int i = 0;
                while (true) {
                    Traverson.TraversalBuilder traversalBuilder = traverson
                            .follow("$._embedded.places[" + i + "]._links.self.href");
                    name = traversalBuilder.toObject("$.name");
                    if (name == null) break;
                    names.add(name);
                    i++;
                }
            } catch (Exception e) {
                throw e;
            } finally {
                return names;
            }
        }
    }

    //use in PlaceList (get just one String of place name)
    public String getPlaceNameByCategory(String str,int index) throws URISyntaxException{
        String name = null;
        try {
            if(str.equals("ทั้งหมด")){
                traverson = new Traverson(new URI(url+"places?sort=name,asc"), MediaTypes.HAL_JSON);
                Traverson.TraversalBuilder traversalBuilder = traverson
                            .follow("$._embedded.places[" + index + "]._links.self.href");
                name = traversalBuilder.toObject("$.name");
            }
            else {
                traverson = new Traverson(new URI(url + "places/search/findByCategoryLikeOrderByNameAsc?category=" + str), MediaTypes.HAL_JSON);
                Traverson.TraversalBuilder traversalBuilder = traverson
                        .follow("$._embedded.places[" + index + "]._links.self.href");
                name = traversalBuilder.toObject("$.name");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            return name;
        }
    }

    public List<String> getPlacesNameByNameLike(String str) throws URISyntaxException{
        List<String> names = new ArrayList<String>();
        try {
            traverson = new Traverson(new URI(url + "places/search/findByNameLikeIgnoreCaseOrderByNameAsc?name=" + str.replace(" ","+")), MediaTypes.HAL_JSON);
            String name;
            int i = 0;
            while (true) {
                Traverson.TraversalBuilder traversalBuilder = traverson
                        .follow("$._embedded.places[" + i + "]._links.self.href");
                name = traversalBuilder.toObject("$.name");
                if (name == null) break;
                names.add(name);
                i++;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            return names;
        }
    }


    //use in SearchPage
    public String getPlaceNameByNameLike(String str,int index) throws URISyntaxException{
        String name = null;
        try {
            traverson = new Traverson(new URI(url + "places/search/findByNameLikeIgnoreCaseOrderByNameAsc?name=" + str.replace(" ","+")), MediaTypes.HAL_JSON);
            Traverson.TraversalBuilder traversalBuilder = traverson
                    .follow("$._embedded.places[" + index + "]._links.self.href");
            name = traversalBuilder.toObject("$.name");
            return name;

        } catch (Exception e) {
            throw e;
        } finally {
            return name;
        }
    }

    public User getUserByFbId(String fbId) {
        User user = null;
        try {
            traverson = new Traverson(new URI(url + "users/search/findUserByFbId?fbId=" + fbId),
                    MediaTypes.HAL_JSON);
            user = traverson.follow("$._embedded.users[0]._links.self.href").toObject(User.class);
        } catch (Exception e) {
            throw e;
        } finally {
            return user;
        }
    }


    public Review getReviewByIndex(int index){

        int all = traverson.follow("$._links.self.href").toObject("$.page.totalElements");
        int size = traverson.follow("$._links.self.href").toObject("$.page.size");
        int page = index/size;
        index = index % size;
        try {
            traverson = new Traverson(new URI(url+"reviews?page="+page+"&sort=name,asc"), MediaTypes.HAL_JSON);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Review review = new Review();

        Traverson.TraversalBuilder traversalBuilder = traverson
                .follow("$._embedded.places[" + index + "]._links.self.href");
        review = traversalBuilder.toObject(Review.class);

        return review;
    }

    public List<String> getNearbyPlacesByLat(double lat, double lng){
        List<String> latAxis = new ArrayList<String>();
        String name = null;
        try {
            int i = 0;
            while(true){
                traverson = new Traverson(new URI(url + "places/search/findByLatBetween?from="
                        +(lat-0.0008)+"&to=" + (lat+0.0008)),MediaTypes.HAL_JSON);
                Traverson.TraversalBuilder traversalBuilder = traverson
                        .follow("$._embedded.places[" + i + "]._links.self.href");
                name = traversalBuilder.toObject("$.name");
                //if(place.getName()==null) break;
                latAxis.add(name);
                i++;

            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {

            return latAxis;
        }
    }

    public List<String> getNearbyPlacesByLng(double lat, double lng){
        List<String> lngAxis = new ArrayList<String>();
        String name = null;
        try {
            int i = 0;
            while(true) {
                traverson = new Traverson(new URI(url + "places/search/findByLngBetween?from="
                        + (lng - 0.0008) + "&to=" + (lng + 0.0008)), MediaTypes.HAL_JSON);
                Traverson.TraversalBuilder traversalBuilder = traverson
                        .follow("$._embedded.places[" + i + "]._links.self.href");
                name = traversalBuilder.toObject("$.name");
                //if(place.getName()==null) break;
                lngAxis.add(name);
                i++;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {

            return lngAxis;
        }
    }


    /*public List<String> getNearbyPlaces(double lat, double lng){
        List<String> latAxis = new ArrayList<String>();
        List<String> lngAxis = new ArrayList<String>();
        String name = null;
        try {
            int i = 0;
            while(true) {
                traverson = new Traverson(new URI(url + "places/search/findByLatBetween?from="
                        + (lat - 0.0008) + "&to=" + (lat + 0.0008)), MediaTypes.HAL_JSON);
                Traverson.TraversalBuilder traversalBuilder = traverson
                        .follow("$._embedded.places[" + i + "]._links.self.href");
                name = traversalBuilder.toObject("$.name");
                if(name()==null) break;
                lngAxis.add(name);
                i++;
            }
                name = null;

                i = 0;
            while (true) {
                traverson = new Traverson(new URI(url + "places/search/findByLngBetween?from="
                        + (lng - 0.0008) + "&to=" + (lng + 0.0008)), MediaTypes.HAL_JSON);
                Traverson.TraversalBuilder traversalBuilder = traverson
                        .follow("$._embedded.places[" + i + "]._links.self.href");
                name = traversalBuilder.toObject("$.name");
                //if(place.getName()==null) break;
                lngAxis.add(name);
                i++;

            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            latAxis.retainAll(lngAxis);
            return latAxis;
        }
    }*/

}
