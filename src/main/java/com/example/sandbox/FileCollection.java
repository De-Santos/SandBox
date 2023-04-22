package com.example.sandbox;

import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.util.*;

@Data
@Builder
public class FileCollection {
    private List<Photo> bannerPhotos;
    private Photo descriptionPhoto;

    public List<Photo> getAll() {
        List<Photo> allPhotos = new ArrayList<>(bannerPhotos);
        if (Objects.nonNull(descriptionPhoto)) allPhotos.add(descriptionPhoto);
        return allPhotos;
    }

    @SneakyThrows
    public Photo getPhotoByHash(UUID hash) {
        if (descriptionPhoto.getHash().equals(hash)) return descriptionPhoto;
        return bannerPhotos.stream()
                .filter(photo -> Objects.equals(photo.getHash(), hash))
                .findFirst()
                .orElseThrow(() -> new FileNotFoundException("File not found by id: " + hash));
    }

    public Optional<Photo> getPhotoByOrder(Integer position) {
        return bannerPhotos.stream()
                .filter(photo -> Objects.equals(photo.getOrder(), position))
                .findFirst();
    }

    @SneakyThrows
    public Photo remove(UUID hash) {
        if (descriptionPhoto.getHash().equals(hash)) {
            Photo temp = descriptionPhoto;
            descriptionPhoto = null;
            return temp;
        }
        Photo photo = bannerPhotos.stream()
                .filter(it -> it.getHash().equals(hash))
                .findFirst()
                .orElseThrow(() -> new FileNotFoundException("File not found by id: " + hash));
        bannerPhotos.remove(photo);
        return photo;
    }

    public static void maidn(String[] args) {
        Photo photo = Photo.builder()
                .hash(UUID.randomUUID())
                .build();
        UUID random = UUID.randomUUID();
        List<Photo> list = new ArrayList<>();
        list.add(Photo.builder()
                .hash(random)
                .build());
        FileCollection fileCollection = new FileCollection(list, photo);
        System.out.println(fileCollection.remove(UUID.randomUUID()));
        System.out.println(fileCollection);
    }

    public static void main(String[] args) {
        List<Photo> list = null;
        FileCollection fileCollection = FileCollection.builder()
                .bannerPhotos(list)
                .build();
        System.out.println(Objects.nonNull(list));
    }
}
