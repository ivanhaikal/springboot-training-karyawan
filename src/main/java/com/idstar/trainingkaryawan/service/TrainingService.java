package com.idstar.trainingkaryawan.service;

import com.idstar.trainingkaryawan.model.request.TrainingOnlyIdRequest;
import com.idstar.trainingkaryawan.model.request.TrainingRequest;
import com.idstar.trainingkaryawan.model.request.TrainingWithoutIdRequest;
import com.idstar.trainingkaryawan.model.response.GeneralResponse;
import com.idstar.trainingkaryawan.model.response.TrainingResponse;
import org.springframework.data.domain.Page;

public interface TrainingService {

    GeneralResponse<TrainingResponse> save(TrainingWithoutIdRequest request);

    GeneralResponse<TrainingResponse> update(TrainingRequest request);

    GeneralResponse<Page<TrainingResponse>> list(Integer page, Integer size);

    GeneralResponse<TrainingResponse> get(Long id);

    GeneralResponse<String> delete(TrainingOnlyIdRequest request);
}
