package com.idstar.trainingkaryawan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KaryawanOnlyIdNamaResponse {

    private Long id;

    private String nama;
}
