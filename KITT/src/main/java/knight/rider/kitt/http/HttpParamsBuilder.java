package knight.rider.kitt.http;

import org.json.JSONObject;

import java.util.WeakHashMap;

public class HttpParamsBuilder {

    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();

    // 私有
    private HttpParamsBuilder() {

    }

    /**
     * 获取构造器对象
     */
    public static HttpParamsBuilder newBuilder() {
        return new HttpParamsBuilder();
    }

    /**
     * 复制指定的Map集合到构造器
     *
     * @param params mappings to be stored in this map
     */
    public final HttpParamsBuilder addParams(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    /**
     * 复制指定的键值对到构造器
     *
     * @param key   key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     */
    public final HttpParamsBuilder addParams(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    /**
     * 构造器提取出Map集合
     *
     * @return map object.
     */
    public final WeakHashMap<String, Object> buildMap() {
        return PARAMS;
    }

    /**
     * 构造器提取出Json字符串
     *
     * @return json String.
     */
    public final String buildJson() {
        JSONObject json = new JSONObject(PARAMS);
        return json.toString();
    }

}
