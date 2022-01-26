package com.services.blogrestapi.payload;

import lombok.Data;

@Data
public class PostDTO {
    private long Id;
    private String title;
    private String description;
    private String content;
}
