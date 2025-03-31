package com.example.crazytest.convert;

import com.example.crazytest.services.ApiCaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/3/31 15:37
 * @DESRIPTION
 */

@Service
public class ApiCaseConvert {

  @Autowired
  ApiCaseService caseService;

  public List<Long> apiCaseIdTypeConvert(String apiCaseIds) throws JsonProcessingException {

    if (StringUtils.isEmpty(apiCaseIds)) {
      return new ArrayList<>();
    }

    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(apiCaseIds, new TypeReference<List<Long>>() {
    });
  }

  public String apiCaseCheckEnable(String apiCaseIds) throws JsonProcessingException {
    List<Long> apiCaseIdsList = apiCaseIdTypeConvert(apiCaseIds);
    return caseService.checkApiCaseEnable(apiCaseIdsList).toString();
  }

}
