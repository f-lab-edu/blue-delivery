package com.bluedelivery.common.event;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    private Map<String, Object> header;
    private String payload;
}
