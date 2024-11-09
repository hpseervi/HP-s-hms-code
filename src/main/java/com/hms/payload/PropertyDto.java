package com.hms.payload;

import lombok.*;

@Getter
@Setter
public class PropertyDto {
    private String name;

    private Integer no_of_guests;

    private Integer no_of_rooms;

    private Integer no_of_beds;

    private long country_id;

    private long state_id;

    private long city_id;
}
