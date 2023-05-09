/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import edu.esprit.entities.Reviews;
import edu.esprit.utils.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nasr
 */
public class ServiceReviews {
    //singleton 

    public static ServiceReviews instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceReviews getInstance() {
        if (instance == null) {
            instance = new ServiceReviews();
        }
        return instance;
    }

    public ServiceReviews() {
        req = new ConnectionRequest();

    }

    public void ajoutReviews(Reviews reviews) {

        String url = Statics.BASE_URL1 + "/reviews/visit/new?Place=" + reviews.getPlace_id() + "&IdUser=" + reviews.getId_User() + "&name=" + reviews.getPlace_Name() + "&rating=" + reviews.getRating() + "&ReviewTxt=" + reviews.getReview_txt();
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

    }

    //affichage
    public ArrayList<Reviews> affichageReviewss() {
        ArrayList<Reviews> result = new ArrayList<>();

        String url = Statics.BASE_URL1 + "/reviews/show";
        req.setUrl(url);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapReviewss = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapReviewss.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Reviews re = new Reviews();

                        //dima id fi codename one float 5outhouha
                        int id = (int) Float.parseFloat(obj.get("id").toString());
                        System.out.println(id);
                        String pname = obj.get("placeName").toString();
                        Map<String, Object> userList = (Map<String, Object>) obj.get("idUser");
                        Map<String, Object> placeList = (Map<String, Object>) obj.get("place");
                            int placeid =(int) Double.parseDouble(placeList.get("placeId").toString());
                            System.out.println(pname);
                            Double cname = Double.parseDouble(obj.get("rating").toString());
                            int userid = (int) Double.parseDouble(userList.get("idUser").toString());

                                String ptype = obj.get("reviewTxt").toString();
                                String pdesc = obj.get("reviewDate").toString();

                                re.setReview_id(placeid);
                                re.setPlace_id(id);
                                re.setId_User(userid);
                                re.setPlace_Name(pname);
                                re.setRating(cname);
                                re.setReview_txt(ptype);
                                re.setReview_date(pdesc);

                                //insert data into ArrayList result
                                result.add(re);

                            
                    }
                    }catch (Exception ex) {

                    ex.printStackTrace();
                }

                }
            }

            );

            NetworkManager.getInstance ()
            .addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

            return result ;

        }
        /*    public ReviewsToVisit DetailRecalamation(int id, ReviewsToVisit Reviews) {

        String url = Statics.BASE_URL1 + "/detailReviews?" + id;
        req.setUrl(url);

        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {

            JSONParser jsonp = new JSONParser();
            try {

                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));

                Reviews.setObjet(obj.get("obj").toString());
                Reviews.setDescription(obj.get("description").toString());
                Reviews.setEtat(Integer.parseInt(obj.get("etat").toString()));

            } catch (IOException ex) {
                System.out.println("error related to sql :( " + ex.getMessage());
            }

            System.out.println("data === " + str);

        }));

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return Reviews;

    }
         */
        //Delete 


    public boolean deleteReviews(int id) {
        String url = Statics.BASE_URL1 + "/reviews/delete?id=" + id;

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

    public boolean modifierReviews(Reviews reviews) {
        String url = Statics.BASE_URL1 + "/reviews/edit/" + reviews.getReview_id() + "?Place=" + reviews.getPlace_id() + "&IdUser=" + reviews.getId_User() + "&name=" + reviews.getPlace_Name() + "&rating=" + reviews.getRating() + "&ReviewTxt=" + reviews.getReview_txt();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

    }

}
