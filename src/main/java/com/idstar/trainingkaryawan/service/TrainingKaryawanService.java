package com.idstar.trainingkaryawan.service;

import com.idstar.trainingkaryawan.model.request.TrainingKaryawanOnlyIdRequest;
import com.idstar.trainingkaryawan.model.request.TrainingKaryawanRequest;
import com.idstar.trainingkaryawan.model.request.TrainingKaryawanWithoutIdRequest;
import com.idstar.trainingkaryawan.model.response.GeneralResponse;
import com.idstar.trainingkaryawan.model.response.TrainingKaryawanResponse;
import org.springframework.data.domain.Page;

public interface TrainingKaryawanService {

    GeneralResponse<TrainingKaryawanResponse> save(TrainingKaryawanWithoutIdRequest request);

    GeneralResponse<TrainingKaryawanResponse> update(TrainingKaryawanRequest request);

    GeneralResponse<Page<TrainingKaryawanResponse>> list(Integer page, Integer size);

    GeneralResponse<TrainingKaryawanResponse> get(Long id);

    GeneralResponse<String> delete(TrainingKaryawanOnlyIdRequest request);
}
