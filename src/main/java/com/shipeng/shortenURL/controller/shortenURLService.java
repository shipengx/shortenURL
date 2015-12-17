package com.shipeng.shortenURL.controller;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shipeng.shortenURL.util.*;


@Path("/url")
public class shortenURLService {

    private HashMap<Long,String> map = new HashMap<Long,String>();

    @GET
        @Path("/getshorturl/{input}")
        @Produces(MediaType.TEXT_PLAIN)
        public String getShortenURL(@PathParam("input") String longURL) {

            //1. get a randome long id for this url.
            long id = Helper.randomIdGenerate();
            System.out.println("the random generated id is: " + id);

            //2. save id, and url into the map
            map.put(id,longURL);

            //3. get short url from the long id
            String shortURL = Helper.idToShortURL(id);
            System.out.println("the short generated url is: " + shortURL);

            return  "the short url is: " + shortURL;
        }

    @GET
        @Path("/getlongurl/{input}")
        @Produces(MediaType.TEXT_PLAIN)
        public String getOriginalURL(@PathParam("input") String shortURL) {

            //1. convert the shortURL to id
            long id = Helper.shortURLToId(shortURL);
            System.out.println("id is: " + id);

            //2. get original url through long id from map
            String longURL = map.get(id);
            System.out.println("long url is: " + longURL);

            return "the long url is: " + longURL;
        }

}//end class 





