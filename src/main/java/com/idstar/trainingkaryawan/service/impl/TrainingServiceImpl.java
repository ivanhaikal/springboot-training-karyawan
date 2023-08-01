package com.idstar.trainingkaryawan.service.impl;

import com.idstar.trainingkaryawan.entity.Training;
import com.idstar.trainingkaryawan.model.request.TrainingOnlyIdRequest;
import com.idstar.trainingkaryawan.model.request.TrainingRequest;
import com.idstar.trainingkaryawan.model.request.TrainingWithoutIdRequest;
import com.idstar.trainingkaryawan.model.response.GeneralResponse;
import com.idstar.trainingkaryawan.model.response.TrainingResponse;
import com.idstar.trainingkaryawan.repository.TrainingRepository;
import com.idstar.trainingkaryawan.service.TrainingService;
import com.idstar.trainingkaryawan.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    @Override
    public GeneralResponse<TrainingResponse> save(TrainingWithoutIdRequest request) {
        validationService.validate(request);

        Training training = new Training();
        training.setTema(request.getTema());
        training.setPengajar(request.getPengajar());
        trainingRepository.save(training);

        return new GeneralResponse<>(HttpStatus.OK.value(), trainingResponse(training), HttpStatus.OK.getReasonPhrase());
    }

    @Transactional
    @Override
    public GeneralResponse<TrainingResponse> update(TrainingRequest request) {
        validationService.validate(request);

        Training training = trainingRepository.findById(request.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID training not found")
        );

        training.setTema(request.getTema());
        training.setPengajar(request.getPengajar());
        trainingRepository.save(training);

        return new GeneralResponse<>(HttpStatus.OK.value(), trainingResponse(training), HttpStatus.OK.getReasonPhrase());
    }

    @Transactional(readOnly = true)
    @Override
    public GeneralResponse<Page<TrainingResponse>> list(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Training> trainings = trainingRepository.findAll(pageable);
        return new GeneralResponse<>(HttpStatus.OK.value(),
                trainings.map(this::trainingResponse),
                HttpStatus.OK.getReasonPhrase());
    }

    @Transactional(readOnly = true)
    @Override
    public GeneralResponse<TrainingResponse> get(Long id) {
        Training training = trainingRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID training not found")
        );

        return new GeneralResponse<>(HttpStatus.OK.value(), trainingResponse(training), HttpStatus.OK.getReasonPhrase());
    }

    @Transactional
    @Override
    public GeneralResponse<String> delete(TrainingOnlyIdRequest request) {
        validationService.validate(request);

        Training training = trainingRepository.findById(request.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID training not found")
        );

        training.setDeletedDate(new Date());
        trainingRepository.save(training);

        return new GeneralResponse<>(HttpStatus.OK.value(), "Success delete", HttpStatus.OK.getReasonPhrase());
    }

    private TrainingResponse trainingResponse(Training training) {
        return new TrainingResponse(training.getCreatedDate(), training.getUpdatedDate(), training.getDeletedDate(),
                training.getId(), training.getTema(), training.getPengajar());
    }
}
