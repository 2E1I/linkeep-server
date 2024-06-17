package com.e2i1.linkeepserver.domain.links.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Getter
@Setter
@Document(indexName = "links")
@Setting(replicas = 0)
public class LinkDocument {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String collectionId;

    @Field(type = FieldType.Keyword)
    private String userId;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String url;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Long)
    private Long numOfViews;

}