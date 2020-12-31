package com.zbcn.zbcnjosejwt.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.zbcn.common.response.ResponseResult;
import com.zbcn.zbcnjosejwt.entity.dto.PayloadDto;
import com.zbcn.zbcnjosejwt.service.JwtTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 *  JWT令牌管理Controller
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/30 10:16
 */
@Api( "JWT令牌管理")
@RestController
@RequestMapping("/token")
public class JwtTokenController {

    @Autowired
    private JwtTokenService jwtTokenService;

    @ApiOperation("使用对称加密（HMAC）算法生成token")
    @GetMapping(value = "/hmac/generate")
    public ResponseResult generateTokenByHMAC() {
        try {
            PayloadDto payloadDto = jwtTokenService.getDefaultPayloadDto();
            String token = jwtTokenService.generateTokenByHMAC(JSONUtil.toJsonStr(payloadDto), SecureUtil.md5("test"));
            return ResponseResult.success(token);
        } catch (JOSEException e) {
            return ResponseResult.fail("生成密钥失败", e);
        }
    }

    @ApiOperation("使用对称加密（HMAC）算法验证token")
    @GetMapping(value = "/hmac/verify")
    public ResponseResult verifyTokenByHMAC(String token) {
        try {
            PayloadDto payloadDto  = jwtTokenService.verifyTokenByHMAC(token, SecureUtil.md5("test"));
            return ResponseResult.success(payloadDto);
        } catch (ParseException | JOSEException e) {
            return ResponseResult.fail("验证密钥失败", e);
        }
    }

    @ApiOperation("获取非对称加密（RSA）算法公钥")
    @GetMapping(value = "/rsa/publicKey")
    public ResponseResult getRSAPublicKey(){
        RSAKey defaultRSAKey = jwtTokenService.getDefaultRSAKey();
        JWKSet jwkSet = new JWKSet(defaultRSAKey);
        return ResponseResult.success(jwkSet.toJSONObject());
    }

    @ApiOperation("使用非对称加密（RSA）算法生成token")
    @GetMapping(value = "/rsa/generate")
    public ResponseResult generateTokenByRSA(){
        PayloadDto payloadDto = jwtTokenService.getDefaultPayloadDto();
        try {
            String token = jwtTokenService.generateTokenByRSA(JSONUtil.toJsonStr(payloadDto), jwtTokenService.getDefaultRSAKey());
            return ResponseResult.success(token);
        } catch (JOSEException e) {
            return ResponseResult.fail("生成RSA token 失败。",e);
        }
    }

    @ApiOperation("使用非对称加密（RSA）算法验证token")
    @GetMapping(value = "/rsa/verify")
    public ResponseResult verifyTokenByRSA(String token){
        try {
            PayloadDto payloadDto = jwtTokenService.verifyTokenByRSA(token, jwtTokenService.getDefaultRSAKey());
            return ResponseResult.success(payloadDto);
        } catch (ParseException | JOSEException  e) {
            return ResponseResult.fail("RSA 验证token 失败。", e);
        }
    }
}
