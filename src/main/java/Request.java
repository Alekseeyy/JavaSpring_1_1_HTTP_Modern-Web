import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {
    private Map<String, String> parameters;
    private String path;
    private String url;

    public Request(String url) {
        this.url = url;
        this.path = queryPath();
        this.parameters = queryParams();
    }

    public Map<String, String> getQueryParams() {
        return parameters;
    }

    public String getQueryPath() {
        return path;
    }

    public List<String> getQueryParam(String name) {
        List<String> parameterValue = new ArrayList<>();
        if (parameters.containsKey(name)) {
            parameterValue.add(parameters.get(name));
        } else {
            parameterValue.add("Такого параметра нету!!!!!!");
        }
        return parameterValue;
    }

    private Map<String, String> queryParams() {
        try {
            parameters = new HashMap<>();
            List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), "UTF-8");
            for (NameValuePair param : params) {
                String key = param.getName();
                String value = param.getValue();
                if (key != null && value != null) {
                    parameters.put(key, value);
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return parameters;
    }

    private String queryPath() {
        int i = url.indexOf("?");
        if (i == -1) {
            return url;
        }
        path = url.substring(0, i);
        return path;
    }
}
