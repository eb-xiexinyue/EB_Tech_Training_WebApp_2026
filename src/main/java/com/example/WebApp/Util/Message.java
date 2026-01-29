package com.example.WebApp.Util;

import java.util.HashMap;
import java.util.Map;

public class Message {

    private static final Map<String, String> messageMap = new HashMap<>();

    static {
        messageMap.put("BE001", "入力されましたメールアドレスは既に使用されています！");
        messageMap.put("BE002", "入力されました部署名は既に使用されています！");
        messageMap.put("BE003", "入力されました案件名は既に使用されています！");
    }

    public static String get(String code) {
        return messageMap.get(code);
    }
}
