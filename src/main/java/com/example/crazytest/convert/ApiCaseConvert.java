package com.example.crazytest.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/31 15:37
 * @DESRIPTION
 */

@Service
public class ApiCaseConvert {

  public List<Long> apiCaseIdTypeConvert(String apiCaseIds) throws JsonProcessingException {

    if (StringUtils.isEmpty(apiCaseIds)) {
      return new ArrayList<>();
    }

    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(apiCaseIds, new TypeReference<List<Long>>() {
    });
  }

}
