package app.servlet;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

public class OauthYandexServlet extends HttpServlet {

    private static final String URI = "https://oauth.yandex.ru/token";
    private static final String CLIENT_ID = "0f93b88929f44b5192e981adde363711";
    private static final String CLIENT_SECRET = "faf25400922f45e4a205087c79636d02";
    private static final String AUTHORIZATION_CODE = "authorization_code";
    private static final String GET_PROFILE_INFO =
            "https://login.yandex.ru/info?format=json&oauth_token=";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String error = req.getParameter("error");
        System.out.println("auth");
        System.out.println(error);
        System.out.println(code);
        if(error == null && code !=null){
            HttpClient client = new HttpClient();
            PostMethod getTokenMethod = new PostMethod(URI);
            getTokenMethod.addParameter("code", code);
            getTokenMethod.addParameter("client_id", CLIENT_ID);
            getTokenMethod.addParameter("client_secret", CLIENT_SECRET);
            getTokenMethod.addParameter("grant_type", AUTHORIZATION_CODE);
            getTokenMethod.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            client.executeMethod(getTokenMethod);
            JSONObject authResponse = new JSONObject(new JSONTokener(new InputStreamReader(getTokenMethod.getResponseBodyAsStream())));
            String accessToken = authResponse.getString("access_token");
            GetMethod getInfoMethod = new GetMethod(GET_PROFILE_INFO+accessToken);
            new HttpClient().executeMethod(getInfoMethod);
            JSONTokener x = new JSONTokener(new InputStreamReader(getInfoMethod.getResponseBodyAsStream(), "UTF-8"));
            JSONObject userInfoJson = new JSONObject(x);
            String firstName = userInfoJson.getString("first_name");
            req.getSession().setAttribute("firstName", firstName);
            String secondName = userInfoJson.getString("last_name");
            req.getSession().setAttribute("secondName", secondName);
            String bdate = userInfoJson.getString("birthday");
            req.getSession().setAttribute("bdate", bdate);
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}
