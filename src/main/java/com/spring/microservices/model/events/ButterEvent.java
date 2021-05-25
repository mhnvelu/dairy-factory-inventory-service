package com.spring.microservices.model.events;

import com.spring.microservices.model.v2.ButterDtoV2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ButterEvent implements Serializable {
    private ButterDtoV2 butterDtoV2;
}
