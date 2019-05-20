package webapp;


import com.google.gson.JsonObject;
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
    public ModelAndView userId (Model model) {
        System.out.println("----userId");
        User user = new User();
        // Мы передаем шаблону start мы передаем некую модель с именем name, который является пустым объектом класса person
        return new ModelAndView("user","userId",user);
    }

    @RequestMapping(value = "/request",method = RequestMethod.POST)
    public ModelAndView makeRequest(User user) throws IOException {
        System.out.println("----makeRequest");
//        JsonObject userIdRequest = new JsonObject();
//        userIdRequest.addProperty("user_id", user.getId());
//        System.out.print("userIdRequest: ");
//        System.out.println(userIdRequest.toString());
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httppost = new HttpPost("http://localhost:5000/index");
        // user_id

        List<NameValuePair> params = new ArrayList<NameValuePair>(1);
        params.add(new BasicNameValuePair("user_id", user.getId()));
        System.out.print("Params: ");
        System.out.println(params);

        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Execute and get the response.
        HttpResponse response = httpClient.execute(httppost);
        System.out.print("response: ");
        System.out.println(response);
        HttpEntity entity = response.getEntity();
        String responseText = EntityUtils.toString(entity, "UTF-8");
        System.out.println(responseText);
        user.setText(responseText);

        return new ModelAndView("prediction","predict",user);
    }

    @RequestMapping(value = "/prediction",method = RequestMethod.POST)
    public ModelAndView makePrediction(User user) {
        System.out.println("----makeRequest");
        System.out.println(user);

        return new ModelAndView("prediction","predict",user);
    }
}
