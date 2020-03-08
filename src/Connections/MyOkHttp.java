/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author Μπορότης Βασίλειος
 * @author Ντουλάκης Ευστράτιος
 * @author Ντάφος Χρήστος
 */

public class MyOkHttp extends SwingWorker {

    private String key =  "75ubJt2gtjie_dte2Rb7";  // "j79mQ_zEuVUqFV1DihJT";
    private String url1 = "https://www.quandl.com/api/v3/datasets/WWDI/";
    private String url2 = "_NY_GDP_MKTP_CN.json?api_key=";
    private String url3 = "https://www.quandl.com/api/v3/datasets/BP/OIL_CONSUM_";
    private String url4 = ".json?api_key=";  
    private JsonOil JsonOil;
    private JsonGdp JsonGdp;
// GDP
// https://www.quandl.com/api/v3/datasets/WWDI/GRC_NY_GDP_MKTP_CN.json?api_key=ΤΟ_ΚΛΕΙΔΙ_ΕΔΩ
// OIL
// https://www.quandl.com/api/v3/datasets/BP/OIL_CONSUM_GRC.json?api_key=ΚΛΕΙΔΙ_ΕΔΩ 

// ind is indicator for Oil or GDP Data retreive
// for Oil data ind = "OIL", for GRD data ind = "GDP"
    
    public MyOkHttp (String cntr, String ind) {

            String urlToCall = "";//    FetchData fd = new FetchData(cntr, ind);
            OkHttpClient client = new OkHttpClient();
            
            if ("GDP".equals(ind)) {
                urlToCall = url1 + cntr + url2 + key;
            }
            if ("OIL".equals(ind)) {
                urlToCall = url3 + cntr + url4 + key;
            }
            
            Request request = new Request.Builder().url(urlToCall).build();
            
    try (
Response response = client.newCall(request).execute();
         ) {
if (response.isSuccessful() && response.body() != null) {
    Gson gson = new Gson();
    if ("OIL".equals(ind)) {
        this.JsonOil = gson.fromJson(response.body().string(), JsonOil.class);
    }
    if ("GDP".equals(ind)) {
        this.JsonGdp = gson.fromJson(response.body().string(), JsonGdp.class);
    }
} else {
    this.setProgress(4);
}
        } catch (IOException e) {
            e.printStackTrace();
                }
    }

    /**
     * @return the JsonOil
     */
    public JsonOil getJsonOil() {
        return JsonOil;
    }

    /**
     * @return the JsonGdp
     */
    public JsonGdp getJsonGdp() {
        return JsonGdp;
    }
    
    @Override
    protected Object doInBackground() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
