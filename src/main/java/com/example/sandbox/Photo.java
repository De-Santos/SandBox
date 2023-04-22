package com.example.sandbox;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
public class Photo {
    private UUID hash;
    private Integer order;
    private String mediaType;
    private String realPhotoName;
    private byte[] image;
}
