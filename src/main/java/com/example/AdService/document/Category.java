package com.example.AdService.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;


@Document(collection = "categories")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    private String categoryId;

    @NotNull
    private String categoryName;

    @NotNull
    private List<String> tags;
}
