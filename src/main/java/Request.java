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

    public Request(String path) {
        this.requestPath = getQueryParam(path);
        this.parameters = getQueryParams(path);
    }

    public String getRequestPath() {
        return requestPath;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    private Map<String, String> getQueryParams(String url) {
        try {
            parameters = new HashMap<>();
            List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), "UTF-8");
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

    private String getQueryParam(String url) {
        int i = url.indexOf("?");
        if (i == -1) {
            return url;
        }
        requestPath = url.substring(0, i);
        return requestPath;
    }
}
