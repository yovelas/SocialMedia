package io.github.yovelas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Photo {
    private int id;
    private String fileName;
    private String fileOriginName;
    private String thumbnail1920x1080;
    private String thumbnail1024x768;
    private String thumbnail800x600;
    private String thumbnail500x375;
    private String thumbnail400x300;
    private String fileSize;
    private String imageWidth;
    private String imageHeight;
    private String dateTime;
    private String artist;
    private String imageSize;
    private String userComment;
    private String exposureTime;
    private String fNumber;
    private String isoSpeedRatings;
    private String focalLength;
    private String meteringMode;
    private String exposureMode;
    private String exposureProgram;
    private String flash;
    private String make;
    private String model;
    private String longFocalLength;
    private String shortFocalLength;
    private String apertureValue;
    private String software;
}
