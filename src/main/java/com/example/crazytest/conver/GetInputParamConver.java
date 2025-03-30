package com.example.crazytest.conver;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/30 14:22
 * @DESRIPTION
 */

@Service
public class GetInputParamConver {

  public Map<String, String> jsonMapConver(JSONObject json) {
    return json.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey,
            entry -> Optional.ofNullable(entry.getValue().toString()).orElse("")));
  }


}
