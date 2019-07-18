package webapp;

import com.google.gson.*;
import enteties.Product;
import enteties.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Controller
public class WebController {


    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public ModelAndView userName () {
        User user = new User();
        return new ModelAndView("user","name",user);
    }

    @RequestMapping(value = "/request",method = RequestMethod.POST)
    public ModelAndView makeRequestAndPrediction(User user) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httppost = new HttpPost("http://localhost:5000/index");

        List<NameValuePair> params = new ArrayList<NameValuePair>(1);
        params.add(new BasicNameValuePair("user_name", user.getName()));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Execute and get the response.
        HttpResponse response = httpClient.execute(httppost);
        HttpEntity entity = response.getEntity();

        String jsonArrayString = EntityUtils.toString(entity, "UTF-8");
        JsonParser jsonParser = new JsonParser();
        JsonArray arrayFromString = jsonParser.parse(jsonArrayString).getAsJsonArray();

        for (JsonElement i : arrayFromString){
            JsonObject jobj = new Gson().fromJson(i, JsonObject.class);

            String product = String.valueOf(jobj.get("product"));
            String description = String.valueOf(jobj.get("description"));
            String price = String.valueOf(jobj.get("price"));

            user.addProduct(new Product(product,description,price));
        }
        return new ModelAndView("prediction","user",user);
    }

}
