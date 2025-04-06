package com.example.crazytest.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.dto.ProcessCaseDTO;
import com.example.crazytest.vo.ProcessCaseVO;

public interface ProcessCaseService {

  IPage<ProcessCaseVO> listPage(ProcessCaseDTO processCaseDTO);

}
