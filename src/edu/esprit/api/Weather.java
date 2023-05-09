package edu.esprit.api;

public class Weather {
/*
    private final String cityAPI = "http://api.weatherapi.com/v1/current.json?key=d0cfd0217d5443ffb97171538232502";

    private final String weatherAPI = "http://api.weatherapi.com/v1/current.json?key=d0cfd0217d5443ffb97171538232502&q=";
public JSONObject GetTwi(String woeid)  {
    try {
        URL url = new URL(weatherAPI + woeid);
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();
        
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            System.out.println(" we have information ");
            StringBuilder informationString = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            JSONParser parser = new JSONParser();
            JSONObject obj = new JSONObject();
            
            String find = "temp_c";
            String res;
            while ((res = reader.readLine()) != null) {
                informationString.append(res);
            }
            
            reader.close();
            JSONObject locationlist = new JSONObject();                
            locationlist = (JSONObject) parser.parse(String.valueOf(informationString));
            
            return locationlist;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    public String searchMe(String me) {

        String find = "/weather/";
        int i = me.indexOf(find);

        if (i > 0) {
            System.out.println(me.substring(i, me.length()));

            return me.substring(i, me.length());
        } else {
            System.out.println("string not found");
            return "string not found";
        }
    }
*/
}
