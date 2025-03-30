package com.example.crazytest.convert;

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
public class GetInputParamConvert {

  public Map<String, String> jsonMapConvert(JSONObject json) {
    return json.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey,
            entry -> Optional.ofNullable(entry.getValue().toString()).orElse("")));
  }


}
