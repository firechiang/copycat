package com.firechiang.android.copycat_helloword;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/**
 * json解析测试（注意：Android原生的JSONObject工具比较简单，不是很推荐使用）
 */
public class JSONTest {

    @Test
    public void testJson2Object() throws JSONException {
        String jsonStr = "{\"name\":\"天天\",\"age\":33}";
        JSONObject json = new JSONObject(jsonStr);
        System.err.println(json);
    }

    @Test
    public void object2Json() {
        User user = new User();
        user.setName("毛毛");
        user.setAge(30);
    }

    private static class User {
        private String name;
        private Integer age;

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }
    }
}
