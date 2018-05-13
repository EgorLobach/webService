package app.servlet;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

public class OauthVKServlet extends HttpServlet {

    private static final String URI = "https://oauth.vk.com/" +
            "access_token?client_id=6477509" +
            "&client_secret=Wl5ifIKeYwL0eD0yPmJn" +
            "&redirect_uri=http://localhost:8888/vk" +
            "&code=~";
    private static final String GET_PROFILE_INFO =
            "https://api.vk.com/method/users.get?user_id=#&fields=first_name,last_name,bdate&access_token=~&v=5.74";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        HttpClient client = new HttpClient();
        GetMethod getAccessTokenMethod = new GetMethod(URI.replace("~",code));
        try {
            client.executeMethod(getAccessTokenMethod);
            InputStreamReader reader = new InputStreamReader(getAccessTokenMethod.getResponseBodyAsStream());
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject userResponse = new JSONObject(tokener);
            String user_id = String.valueOf(userResponse.get("user_id"));
            reader.close();
            String temp = GET_PROFILE_INFO.replace("#", user_id);
            String accessToken = userResponse.getString("access_token");
            temp = temp.replace("~",accessToken);
            GetMethod getInfoMethod = new GetMethod(temp);
            client.executeMethod(getInfoMethod);
            reader = new InputStreamReader(getInfoMethod.getResponseBodyAsStream(), "UTF-8");
            tokener = new JSONTokener(reader);
            JSONObject userInfoResponse = new JSONObject(tokener).getJSONArray("response").getJSONObject(0);
            String firstName = userInfoResponse.getString("first_name");
            req.getSession().setAttribute("firstName", firstName);
            String secondName = userInfoResponse.getString("last_name");
            req.getSession().setAttribute("secondName", secondName);
            String bdate = userInfoResponse.getString("bdate");
            req.getSession().setAttribute("bdate", bdate);
            resp.sendRedirect(req.getContextPath() + "/home");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
