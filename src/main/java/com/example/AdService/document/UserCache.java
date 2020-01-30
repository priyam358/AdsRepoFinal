package com.example.AdService.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserCache implements Serializable {

    private String userId;
    private List<Ad> ads;
}
