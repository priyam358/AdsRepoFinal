package com.example.AdService.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class RecieveTagDTO {
    @NonNull
    private List<String> tags;
    @NonNull
    private String userId;
}
