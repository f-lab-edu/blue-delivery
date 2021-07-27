package com.bluedelivery.application.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EditCategoryParam {
    private final Long id;
    private final String name;
}
