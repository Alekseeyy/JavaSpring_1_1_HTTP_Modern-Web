import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {

    public static Map<String, String> getQueryParams(String url) {
        final Map<String, String> parameter = new HashMap<>();
        List<NameValuePair> params;
        try {
            params = URLEncodedUtils.parse(new URI(url), "UTF-8");
            for (NameValuePair param : params) {
                if (param.getName() != null && param.getValue() != null) {
                    parameter.put(param.getName(), param.getValue());
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return parameter;
    }

    public static String getQueryParam(String url) {
        String result;
        int i = url.indexOf("?");
        if (i == -1) {
            return url;
        }
        result = url.substring(0, i);
        return result;
    }
}
