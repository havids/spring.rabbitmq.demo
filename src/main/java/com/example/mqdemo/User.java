package com.example.mqdemo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class User implements Serializable {

    private Integer userId;
    private String userName;
    private String address;

}
