package com.example.crazytest.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.crazytest.entity.UserEntity;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * @author
 * @name Menghui
 * @date 2025/3/8 20:58
 * @DESRIPTION
 */

public class JWTUtil {

  private static final long EXPIRATION = 30 * 24 * 60 * 60L;

  private static final String SECRET = "AA5A90EFDD0EACD2BB47989EC034AD1A";

  public static String crateToken(UserEntity user) {

    Date expDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
    Map<String, Object> map = new HashMap<>();
    map.put("alg", "HS256");
    map.put("typ", "JWT");

    return
        JWT.create().withHeader(map).withClaim("id", user.getId())
            .withClaim("account", user.getAccount()).withClaim("name", user.getName())
            .withClaim("tenantId", user.getTenantId()).withClaim("roleId", user.getRoleId())
            .withExpiresAt(expDate).withIssuedAt(new Date()).sign(Algorithm.HMAC256(SECRET));

  }


  /**
   * 解密token
   *
   * @param token
   * @return
   */
  public static DecodedJWT getDecodeToken(String token) {
    return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
  }


  /**
   * 根据token获取用户信息
   *
   * @param token
   * @return
   */
  public static UserEntity getUserByToken(String token) {
    if (StringUtils.isNotEmpty(token)) {
      Map<String, Claim> claimMap = getDecodeToken(token).getClaims();
      return UserEntity.builder().id(claimMap.get("id").asLong())
          .account(claimMap.get("account").asString())
          .name(claimMap.get("name").asString())
          .tenantId(claimMap.get("tenantId").asString())
          .roleId(claimMap.get("roleId").asLong())
          .build();
    }
    return UserEntity.builder().build();
  }
}
