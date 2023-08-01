package com.idstar.trainingkaryawan.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RekeningOnlyIdRequest {

    @NotNull
    private Long id;
}
