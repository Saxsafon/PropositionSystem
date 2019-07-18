package webapp;


import com.google.gson.*;
import enteties.Product;
import enteties.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public ModelAndView userId () {
        System.out.println("----userId");
        User user = new User();
        return new ModelAndView("user","userId",user);
    }

    @RequestMapping(value = "/request",method = RequestMethod.POST)
    public ModelAndView makeRequestAndPrediction(User user) throws IOException {
        System.out.println("----makeRequest");

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httppost = new HttpPost("http://localhost:5000/index");
        // user_id

        List<NameValuePair> params = new ArrayList<NameValuePair>(1);
        params.add(new BasicNameValuePair("user_id", user.getId()));
//        System.out.print("Params: ");
//        System.out.println(params);

        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Execute and get the response.
        HttpResponse response = httpClient.execute(httppost);
        // System.out.print("response: ");
        // System.out.println(response);
        HttpEntity entity = response.getEntity();
        String responseText = EntityUtils.toString(entity, "UTF-8");

        // System.out.println("responseText: ");
        // System.out.println(responseText);

        String jsonArrayString = responseText;
        JsonParser jsonParser = new JsonParser();

        JsonArray arrayFromString = jsonParser.parse(jsonArrayString).getAsJsonArray();
//         System.out.println(arrayFromString.toString());

//        System.out.println(arrayFromString.get(0));
//        System.out.println(arrayFromString.get(0).getClass());
//        System.out.println("---------------------------------");

        for (JsonElement i : arrayFromString){
//            System.out.println(i);
            JsonObject jobj = new Gson().fromJson(i, JsonObject.class);
//            System.out.println(jobj.get("name"));
//            System.out.println(jobj.get("description"));
//            System.out.println(jobj.get("picture"));
//            System.out.println("------------------------------------");

            String name = String.valueOf(jobj.get("name"));
            String description = String.valueOf(jobj.get("description"));
            String picture = String.valueOf(jobj.get("picture"));
            String price = String.valueOf(jobj.get("price"));

            user.addProduct(new Product(name,description,picture,price));
        }

        return new ModelAndView("prediction","user",user);
    }

}
