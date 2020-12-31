package com.zbcn.zbcnjosejwt.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.zbcn.zbcnjosejwt.entity.dto.PayloadDto;

import java.text.ParseException;

public interface JwtTokenService {
    /**
     * 对称加密生成 token
     * @param payloadStr
     * @param secret
     * @return
     */
    String generateTokenByHMAC(String payloadStr, String secret) throws JOSEException;

    /**
     * 校验token
     * @param token
     * @param secret
     * @return
     * @throws ParseException
     * @throws JOSEException
     */
    PayloadDto verifyTokenByHMAC(String token, String secret) throws ParseException, JOSEException;

    /**
     * 获取默认负载信息
     * @return
     */
    PayloadDto getDefaultPayloadDto();

    /**
     * 获取RSA秘钥
     * @return
     */
    RSAKey getDefaultRSAKey();

    /**
     * 非对称加密校验token
     * @param token
     * @param rsaKey
     * @return
     */
    PayloadDto verifyTokenByRSA(String token, RSAKey rsaKey) throws ParseException, JOSEException;

    /**
     * 生成 RSA token
     * @param payloadStr
     * @param rsaKey
     * @return
     */
    String generateTokenByRSA(String payloadStr, RSAKey rsaKey) throws JOSEException;
}
