package com.idstar.trainingkaryawan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailKaryawanResponse {

    private Date createdDate;

    private Date updatedDate;

    private Date deletedDate;

    private Long id;

    private String nik;

    private String npwp;
}
