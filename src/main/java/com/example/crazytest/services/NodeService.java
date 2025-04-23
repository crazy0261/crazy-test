package com.example.crazytest.services;

import com.example.crazytest.config.ExecutionProcessContext;
import com.example.crazytest.entity.ExecutionResult;
import com.example.crazytest.entity.Node;
import java.io.IOException;
import java.sql.SQLException;

public interface NodeService {

  ExecutionResult execute(Node node, ExecutionProcessContext context) throws SQLException, IOException;
}
