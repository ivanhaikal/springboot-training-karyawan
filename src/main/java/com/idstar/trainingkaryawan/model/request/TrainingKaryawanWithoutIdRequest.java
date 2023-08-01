package com.idstar.trainingkaryawan.model.request;

import com.idstar.trainingkaryawan.validation.ValidDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingKaryawanWithoutIdRequest {

    private KaryawanOnlyIdRequest karyawan;

    private TrainingOnlyIdRequest training;

    @NotBlank
    @ValidDate
    private String tanggal;
}
