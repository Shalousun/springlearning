package com.sunyu.kafka.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yu 2019/12/13.
 */
@Data
public class KafkaMsg {

    @NotBlank
    private String topic;

    private String key;

    @NotBlank
    private String value;
}
