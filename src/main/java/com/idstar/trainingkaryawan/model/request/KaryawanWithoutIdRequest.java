package com.idstar.trainingkaryawan.model.request;

import com.idstar.trainingkaryawan.validation.ValidDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KaryawanWithoutIdRequest {

    @NotBlank
    @Size(max = 255)
    private String nama;

    @NotBlank
    @ValidDate
    private String dob;

    @NotBlank
    @Size(max = 255)
    private String status;

    @NotBlank
    @Size(max = 255)
    private String alamat;

    private DetailKaryawanWithoutIdRequest detailKaryawan;
}
