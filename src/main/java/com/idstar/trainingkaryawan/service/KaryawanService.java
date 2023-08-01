package com.idstar.trainingkaryawan.service;

import com.idstar.trainingkaryawan.model.request.KaryawanOnlyIdRequest;
import com.idstar.trainingkaryawan.model.request.KaryawanWithoutIdRequest;
import com.idstar.trainingkaryawan.model.request.KaryawanRequest;
import com.idstar.trainingkaryawan.model.response.GeneralResponse;
import com.idstar.trainingkaryawan.model.response.KaryawanResponse;
import org.springframework.data.domain.Page;

public interface KaryawanService {

    GeneralResponse<KaryawanResponse> save(KaryawanWithoutIdRequest request);

    GeneralResponse<KaryawanResponse> update(KaryawanRequest request);

    GeneralResponse<Page<KaryawanResponse>> list(Integer page, Integer size);

    GeneralResponse<KaryawanResponse> get(Long id);

    GeneralResponse<String> delete(KaryawanOnlyIdRequest request);
}
