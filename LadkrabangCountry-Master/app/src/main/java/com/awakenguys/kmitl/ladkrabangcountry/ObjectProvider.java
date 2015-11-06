package com.awakenguys.kmitl.ladkrabangcountry;

import com.awakenguys.kmitl.ladkrabangcountry.model.Place;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;



public class ObjectProvider  {

    private final String url = "http://203.151.92.199:8888/";
    private Traverson traverson;


    public List<Place> getAllPlaces() throws URISyntaxException{
        traverson = new Traverson(new URI(url+"places?sort=name,asc"), MediaTypes.HAL_JSON);
        int size = traverson.follow("$._links.self.href").toObject("$.page.totalElements");
        List<Place> places = new ArrayList<Place>();
        Place place = new Place();
        for(int i = 0;i<size;i++){
            Traverson.TraversalBuilder traversalBuilder = traverson
                    .follow("$._embedded.places[" + i + "]._links.self.href");
            place = traversalBuilder.toObject(Place.class);
            places.add(place);
        }
        return places;
    }

    public List<Place> getPlacesByNameLike(String str) throws URISyntaxException {
        List places = new ArrayList<Place>();
        try {
            traverson = new Traverson(new URI(url + "places/search/findByNameLikeOrderByNameAsc?name=" + str), MediaTypes.HAL_JSON);
            Place place = new Place();
            int i = 0;
            while(true){
                Traverson.TraversalBuilder traversalBuilder = traverson
                        .follow("$._embedded.places[" + i + "]._links.self.href");
                place = traversalBuilder.toObject(Place.class);
                if(place.getId()==null) break;
                places.add(place);
                i++;
            }
        } catch (URISyntaxException e) {
            throw e;
        } finally {
            return places;
        }
    }

    public List<Place> getPlacesByCategory(String str) throws URISyntaxException {
        List places = new ArrayList<Place>();
        try {
            traverson = new Traverson(new URI(url + "places/search/findByCategoryOrderByNameAsc?category=" + str), MediaTypes.HAL_JSON);
            Place place = new Place();
            int i = 0;
            while(true){
                Traverson.TraversalBuilder traversalBuilder = traverson
                        .follow("$._embedded.places[" + i + "]._links.self.href");
                place = traversalBuilder.toObject(Place.class);
                if(place.getId()==null) break;
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
                traverson = new Traverson(new URI(url + "places/search/findByCategoryOrderByNameAsc?category=" + str), MediaTypes.HAL_JSON);
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


}
