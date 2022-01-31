import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {
    private String requestPath;
    private String login;
    private String password;

    private Map<String, String> parameter = new HashMap<>();
    private List<NameValuePair> params;

    public Map<String, String> getQueryParams(String url) {
        try {
            params = URLEncodedUtils.parse(new URI(url), "UTF-8");
            for (NameValuePair param : params) {
                login = param.getName();
                password = param.getValue();
                if (login != null && password != null) {
                    parameter.put(login, password);
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return parameter;
    }

    public String getQueryParam(String url) {
        int i = url.indexOf("?");
        if (i == -1) {
            return url;
        }
        requestPath = url.substring(0, i);
        return requestPath;
    }
}
