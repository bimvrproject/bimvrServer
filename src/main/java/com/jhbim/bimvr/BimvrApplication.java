package com.jhbim.bimvr;

import com.fasterxml.jackson.core.*;
import com.jhbim.bimvr.utils.FileUploadUtils;
import com.jhbim.bimvr.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;


@SpringBootApplication(scanBasePackages = "com.jhbim.bimvr")
@MapperScan("com.jhbim.bimvr.dao.mapper")
public class BimvrApplication extends SpringBootServletInitializer {

    public static JsonParser settings = new JsonParser() {
        @Override
        public ObjectCodec getCodec() {
            return null;
        }

        @Override
        public void setCodec(ObjectCodec objectCodec) {

        }

        @Override
        public Version version() {
            return null;
        }

        @Override
        public void close() throws IOException {

        }

        @Override
        public boolean isClosed() {
            return false;
        }

        @Override
        public JsonStreamContext getParsingContext() {
            return null;
        }

        @Override
        public JsonLocation getTokenLocation() {
            return null;
        }

        @Override
        public JsonLocation getCurrentLocation() {
            return null;
        }

        @Override
        public JsonToken nextToken() throws IOException {
            return null;
        }

        @Override
        public JsonToken nextValue() throws IOException {
            return null;
        }

        @Override
        public JsonParser skipChildren() throws IOException {
            return null;
        }

        @Override
        public JsonToken getCurrentToken() {
            return null;
        }

        @Override
        public int getCurrentTokenId() {
            return 0;
        }

        @Override
        public boolean hasCurrentToken() {
            return false;
        }

        @Override
        public boolean hasTokenId(int i) {
            return false;
        }

        @Override
        public boolean hasToken(JsonToken jsonToken) {
            return false;
        }

        @Override
        public void clearCurrentToken() {

        }

        @Override
        public JsonToken getLastClearedToken() {
            return null;
        }

        @Override
        public void overrideCurrentName(String s) {

        }

        @Override
        public String getCurrentName() throws IOException {
            return null;
        }

        @Override
        public String getText() throws IOException {
            return null;
        }

        @Override
        public char[] getTextCharacters() throws IOException {
            return new char[0];
        }

        @Override
        public int getTextLength() throws IOException {
            return 0;
        }

        @Override
        public int getTextOffset() throws IOException {
            return 0;
        }

        @Override
        public boolean hasTextCharacters() {
            return false;
        }

        @Override
        public Number getNumberValue() throws IOException {
            return null;
        }

        @Override
        public NumberType getNumberType() throws IOException {
            return null;
        }

        @Override
        public int getIntValue() throws IOException {
            return 0;
        }

        @Override
        public long getLongValue() throws IOException {
            return 0;
        }

        @Override
        public BigInteger getBigIntegerValue() throws IOException {
            return null;
        }

        @Override
        public float getFloatValue() throws IOException {
            return 0;
        }

        @Override
        public double getDoubleValue() throws IOException {
            return 0;
        }

        @Override
        public BigDecimal getDecimalValue() throws IOException {
            return null;
        }

        @Override
        public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
            return new byte[0];
        }

        @Override
        public String getValueAsString(String s) throws IOException {
            return null;
        }
    };

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BimvrApplication.class);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 设置哪些接口可以跨域访问
        return new CorsFilter(source);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");   // 设置允许哪些来源地址访问
        corsConfiguration.addAllowedHeader("*");   // 设置允许哪些请求头
        corsConfiguration.addAllowedMethod("*");   // 设置允许的方法，Get / Post / Delete / Put 等
        return corsConfiguration;
    }

    public static void main(String[] args) {
        SpringApplication.run(BimvrApplication.class, args);
    }

    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }

    @Bean
    public FileUploadUtils fileUploadUtils(){
        return new FileUploadUtils();
    }
}
