package com.idstar.trainingkaryawan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RekeningResponse {

    private Date createdDate;

    private Date updatedDate;

    private Date deletedDate;

    private Long id;

    private String nama;

    private String jenis;

    private String rekening;

    private KaryawanOnlyIdNamaResponse karyawan;
}
