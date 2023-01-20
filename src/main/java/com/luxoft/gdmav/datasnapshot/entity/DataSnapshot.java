package com.luxoft.gdmav.datasnapshot.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DataSnapshot {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotBlank
    Long id ;
    String name, description;
    //@Temporal(TemporalType.TIMESTAMP)
    String updatedTimeStamp;


}
