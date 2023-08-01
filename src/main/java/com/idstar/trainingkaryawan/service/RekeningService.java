package com.idstar.trainingkaryawan.service;

import com.idstar.trainingkaryawan.model.request.RekeningOnlyIdRequest;
import com.idstar.trainingkaryawan.model.request.RekeningRequest;
import com.idstar.trainingkaryawan.model.request.RekeningWithoutIdRequest;
import com.idstar.trainingkaryawan.model.response.GeneralResponse;
import com.idstar.trainingkaryawan.model.response.RekeningResponse;
import org.springframework.data.domain.Page;

public interface RekeningService {

    GeneralResponse<RekeningResponse> save(RekeningWithoutIdRequest request);

    GeneralResponse<RekeningResponse> update(RekeningRequest request);

    GeneralResponse<Page<RekeningResponse>> list(Integer page, Integer size);

    GeneralResponse<RekeningResponse> get(Long id);

    GeneralResponse<String> delete(RekeningOnlyIdRequest request);
}
