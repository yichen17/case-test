package com.yichen.casetest.config.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Qiuxinchao
 * @date 2023/4/24 9:13
 * @describe
 */
@Configuration
public class JacksonConfiguration {

    /**
     * 指定 LocalDateTime 序列化格式
     * https://blog.csdn.net/qq_45740349/article/details/120805668
     * @param dateFormat
     * @return
     */
    @Bean
    @ConditionalOnProperty("spring.jackson.date-format")
    Jackson2ObjectMapperBuilderCustomizer customizeLocalDateTimeFormat(@Value("${spring.jackson.date-format}") String dateFormat){
        return jacksonObjectMapperBuilder -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
            jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
            jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
            // bigDecimal 精度设置
            jacksonObjectMapperBuilder.serializerByType(BigDecimal.class, new BigDecimalSerialize());
        };
    }

    public static class BigDecimalSerialize extends NumberSerializers.Base<Object> {

        public BigDecimalSerialize() {
            super(BigDecimal.class, JsonParser.NumberType.BIG_DECIMAL, "bigDecimal");
        }

        @Override
        public void serialize(Object value, JsonGenerator gen,
                              SerializerProvider provider) throws IOException {
            gen.writeString(((BigDecimal) value).setScale(2, RoundingMode.HALF_UP).toString());
        }
    }

}
