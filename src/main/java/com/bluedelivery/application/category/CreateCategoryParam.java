package com.bluedelivery.application.category;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
public class CreateCategoryParam {
    private final String name;
}
