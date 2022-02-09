import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {
    private String requestPath;
    private Map<String, String> parameters;
    private String path;

    public Request(String path) {
        this.path = path;
        this.requestPath = getQueryParam(path);
        this.parameters = getQueryParams();
    }

    public Map<String, String> getQueryParams() {
        try {
            parameters = new HashMap<>();
            List<NameValuePair> params = URLEncodedUtils.parse(new URI(path), "UTF-8");
            for (NameValuePair param : params) {
                String login = param.getName();
                String password = param.getValue();
                if (login != null && password != null) {
                    parameters.put(login, password);
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return parameters;
    }

    public String getQueryParam(String name) {
        int i = name.indexOf("?");
        if (i == -1) {
            return name;
        }
        requestPath = name.substring(0, i);
        return requestPath;
    }
}
