
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import edu.esprit.entities.PlaceToVisit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nasr
 */
public class test {
                        private ConnectionRequest req;

    public void getTes(String tesam){

    ArrayList<PlaceToVisit> result = new ArrayList<>();

        String url = "http://api.weatherapi.com/v1/current.json?key=d0cfd0217d5443ffb97171538232502&q="+tesam;
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

        System.out.println(result);    }
    
    }

