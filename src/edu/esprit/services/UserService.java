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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import edu.esprit.entities.User;
import edu.esprit.utils.Statics;
import java.util.Map;

/**
 *
 * @author
 */
public class UserService {

    public static UserService instance = null;
    String json;
    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public UserService() {
        req = new ConnectionRequest();
    }

    public void login(String mail, String pwd) {
        String url = Statics.BASE_URL + "/login?email=" + mail + "&password=" + pwd;
        req = new ConnectionRequest(url, false);
        req.setUrl(url);
        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData());
            try {
                if (json.equals("Mail non existant")) {
                    Dialog.show("Echec d'authentification", "Ce mail n'est lié à aucun compte", "OK", null);
                } else if (json.equals("Mot de passe errone")) {
                    Dialog.show("Echec d'authentification", "Mot de passe éronné", "OK", null);
                } else {
                    Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    String fnameU = obj.get("userFirstname").toString();
                    String lnameU = obj.get("userLastname").toString();
                    String mailU = obj.get("userMail").toString();
                    float phoneU = Float.parseFloat(obj.get("userPhone").toString());
                    String nameU = obj.get("username").toString();
                    String roleU = obj.get("role").toString();
                    User current = new User(fnameU, lnameU, mailU, (int) phoneU, nameU, roleU);
                    SessionManager.setU(current);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void logout() {
        SessionManager.setU(null);
    }

    public boolean signupTouriste(User u) {
        String url = Statics.BASE_URL + "/signupTour?fname=" + u.getFname() + "&lname=" + u.getLname()
                + "&phone=" + u.getPhone() + "&uname=" + u.getUname() + "&pwd=" + u.getPwd() + "&mail=" + u.getMail()
                + "&nat=" + u.getNationality() + "&lang=" + u.getLang();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    public boolean signupGuide(User u) {
        String url = Statics.BASE_URL + "/signupGuide?fname=" + u.getFname() + "&lname=" + u.getLname()
                + "&phone=" + u.getPhone() + "&uname=" + u.getUname() + "&pwd=" + u.getPwd() + "&mail=" + u.getMail()
                + "&lang1=" + u.getLang1() + "&lang2=" + u.getLang2() + "&lang3=" + u.getLang3() + "&city" + u.getCity();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    public boolean updateProfile(User u) {
        String url = Statics.BASE_URL + "/updateProfile/" + u.getMail() + "?role=" + u.getRole() + "&fname=" + u.getFname() + "&lname=" + u.getLname()
                + "&phone=" + u.getPhone() + "&uname=" + u.getUname() + "&pwd=" + u.getPwd();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    public String getPasswordByEmail(String mail, Resources res) {
        String url = Statics.BASE_URL + "/reset?email=" + mail;
        req = new ConnectionRequest(url, false);
        req.setUrl(url);
        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();
            json = new String(req.getResponseData()) + "";
            try {
                System.out.println("data ==" + json);
                Map<String, Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return json;
    }

}
