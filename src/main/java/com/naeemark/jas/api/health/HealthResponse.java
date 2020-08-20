package com.naeemark.jas.api.health;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2020-08-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HealthResponse {

    private String serviceName;
    private String status;
}
