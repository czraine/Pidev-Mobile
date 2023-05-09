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
import com.codename1.io.rest.Rest;
import com.codename1.ui.events.ActionListener;
import edu.esprit.entities.Produit;
import edu.esprit.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hedi
 */
public class ServiceProduit {

    public static ServiceProduit instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }

    public ServiceProduit() {
        req = new ConnectionRequest();
    }

    public void ajoutProduit(Produit p) {

        String url = Statics.BASE_URL + "/produit/new?nameProd=" + p.getNameProd() + "&prodDescription=" + p.getProdDescription()
                + "&typeProd=" + p.getTypeProd() + "&quantite=" + p.getQuantite() + "&priceProd=" + p.getPriceProd()
                + "&imageProd=" + p.getImageProd() + "&status=" + p.getStatus();
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data ajout == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Produit> affichageProduits() {
        ArrayList<Produit> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/produit/all";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapCourss = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapCourss.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        
                        float id = Float.parseFloat(obj.get("idProduit").toString());
                        String nom = obj.get("nameProd").toString();
                        String desc = obj.get("prodDescription").toString();
                        String type = obj.get("typeProd").toString();
                        float prix = Float.parseFloat(obj.get("priceProd").toString());
                        float qte = Float.parseFloat(obj.get("quantite").toString());
                        String img = obj.get("imageProd").toString();
                        String status = obj.get("status").toString();

                        Produit p = new Produit();
                        p.setIdProduit((int) id);
                        p.setNameProd(nom);
                        p.setProdDescription(desc);
                        p.setTypeProd(type);
                        p.setPriceProd(prix);
                        p.setQuantite((int)qte);
                        p.setImageProd(img);
                        p.setStatus(status);

                        result.add(p);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;

    }

    public Produit DetailProduit(int id, Produit p) {

        String url = Statics.BASE_URL + "/produit/" + id;
        req.setUrl(url);
        
        req.addResponseListener(((evt) -> {

            JSONParser jsonp = new JSONParser();
            try {

                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                        String nom = obj.get("nameProd").toString();
                        String desc = obj.get("prodDescription").toString();
                        String type = obj.get("typeProd").toString();
                        float prix = Float.parseFloat(obj.get("priceProd").toString());
                        float qte = Float.parseFloat(obj.get("quantite").toString());
                        String img = obj.get("imageProd").toString();
                        String status = obj.get("status").toString();
                        
                        p.setIdProduit(id);
                        p.setNameProd(nom);
                        p.setProdDescription(desc);
                        p.setTypeProd(type);
                        p.setPriceProd(prix);
                        p.setQuantite((int)qte);
                        p.setImageProd(img);
                        p.setStatus(status);

            } catch (IOException ex) {
                System.out.println("error related to sql :( " + ex.getMessage());
            }
        }));

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return p;

    }

    //Delete 
    public static boolean deleteProduit(int id) {
        Rest.delete(Statics.BASE_URL + "/produit/delete/" + id).jsonContent().acceptJson().getAsJsonMap().getResponseData();
        return true;
    }

    //Update 
    public boolean modifierProduit(Produit p) {
        String url = Statics.BASE_URL + "/produit/update/"+p.getIdProduit()+"?nameProd=" + p.getNameProd() + "&prodDescription=" + p.getProdDescription()
                + "&typeProd=" + p.getTypeProd() + "&quantite=" + p.getQuantite() + "&priceProd=" + p.getPriceProd()
                + "&imageProd=" + p.getImageProd() + "&status=" + p.getStatus();
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
