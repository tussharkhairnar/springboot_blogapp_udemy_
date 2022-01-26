package com.services.blogrestapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long Id;

    @Column(
            name = "title",
            nullable = false
    )
    private String title;

    @Column(
            name = "description"
    )
    private String description;

    @Column(
            name = "content"
    )
    private String content;

}
