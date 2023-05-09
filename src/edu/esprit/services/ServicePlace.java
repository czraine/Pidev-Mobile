/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import edu.esprit.entities.PlaceToVisit;
import edu.esprit.utils.Statics;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nasr
 */
public class ServicePlace {

    //singleton 
    public static ServicePlace instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServicePlace getInstance() {
        if (instance == null) {
            instance = new ServicePlace();
        }
        return instance;
    }

    public ServicePlace() {
        req = new ConnectionRequest();

    }

    
    public void ajoutPlace(PlaceToVisit Place) {

        String url = Statics.BASE_URL1 + "/visit/new?name=" + Place.getPlace_name()+ "&city=" + Place.getCityname()+ "&type=" + Place.getPlace_Type()+ "&desc=" + Place.getPlace_Description()+ "&address=" + Place.getPlace_Address()+ "&price=" + Place.getTickets_Price()+ "&price=" + Place.getTickets_Price()+ "&img=" + Place.getPlace_img()+ "&img2=" + Place.getPlace_img2()+ "&img3=" + Place.getPlace_img3(); // aa sorry n3adi getId lyheya mech ta3 user ta3 Place

        req.setUrl(url);
                req.setHttpMethod("POST");

        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

    }
    //affichage
    public ArrayList<PlaceToVisit> affichagePlaces() {
        ArrayList<PlaceToVisit> result = new ArrayList<>();

        String url = Statics.BASE_URL1 + "/visit/show";
        req.setUrl(url);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapPlaces = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapPlaces.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        PlaceToVisit re = new PlaceToVisit();

                        //dima id fi codename one float 5outhouha
                        int id = (int)Float.parseFloat(obj.get("placeId").toString());
                        System.out.println(id);
                        String pname = obj.get("placeName").toString();
                        System.out.println( " place name is" + pname);
                        String cname = obj.get("cityname").toString();

                        String ptype = obj.get("placeType").toString();
                        String pdesc = obj.get("placeDescription").toString();
                        String padress = obj.get("placeAddress").toString();
                        double tprice = Double.parseDouble(obj.get("ticketsPrice").toString());
                        String pimg = obj.get("placeImg").toString();
                        String pimg2 = obj.get("placeImg2").toString();
                        String pimg3 = obj.get("placeImg3").toString();

                        re.setId( id);
                        re.setPlace_name(pname);
                        re.setCityname(cname);
                        re.setPlace_Type(ptype);
                        re.setPlace_Description(pdesc);
                        re.setPlace_Address(padress);
                        re.setTickets_Price(tprice);
                        re.setPlace_img(pimg);
                        re.setPlace_img2(pimg2);
                        re.setPlace_img3(pimg3);

                        //insert data into ArrayList result
                        result.add(re);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;

    }

    /*    public PlaceToVisit DetailRecalamation(int id, PlaceToVisit Place) {

        String url = Statics.BASE_URL1 + "/detailPlace?" + id;
        req.setUrl(url);

        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {

            JSONParser jsonp = new JSONParser();
            try {

                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));

                Place.setObjet(obj.get("obj").toString());
                Place.setDescription(obj.get("description").toString());
                Place.setEtat(Integer.parseInt(obj.get("etat").toString()));

            } catch (IOException ex) {
                System.out.println("error related to sql :( " + ex.getMessage());
            }

            System.out.println("data === " + str);

        }));

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return Place;

    }
*/
    //Delete 
    public boolean deletePlace(int id) {
        String url = Statics.BASE_URL1 + "/visit/delete?id=" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

        
    public boolean modifierPlace(PlaceToVisit Place) {
        String url = Statics.BASE_URL1 + "/visit/edit/"+ Place.getPlace_Id() +"?name=" + Place.getPlace_name()+ "&city=" + Place.getCityname()+ "&type=" + Place.getPlace_Type()+ "&desc=" + Place.getPlace_Description()+ "&address=" + Place.getPlace_Address()+ "&price=" + Place.getTickets_Price()+ "&price=" + Place.getTickets_Price()+ "&img=" + Place.getPlace_img()+ "&img2=" + Place.getPlace_img2()+ "&img3=" + Place.getPlace_img3(); // aa sorry n3adi getId lyheya mech ta3 user ta3 Place
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    

     
}
