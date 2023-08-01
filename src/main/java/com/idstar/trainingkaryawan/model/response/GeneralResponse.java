package com.idstar.trainingkaryawan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse<T> {

    private Integer code;

    private T data;

    private String status;
}
