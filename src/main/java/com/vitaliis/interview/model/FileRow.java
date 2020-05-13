package com.vitaliis.interview.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileRow {

    @Id
    String id;
    String name;
    String description;
    String time;
}
