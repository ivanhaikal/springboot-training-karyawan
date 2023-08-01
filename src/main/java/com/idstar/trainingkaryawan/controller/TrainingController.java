package com.idstar.trainingkaryawan.controller;

import com.idstar.trainingkaryawan.model.request.TrainingOnlyIdRequest;
import com.idstar.trainingkaryawan.model.request.TrainingRequest;
import com.idstar.trainingkaryawan.model.request.TrainingWithoutIdRequest;
import com.idstar.trainingkaryawan.model.response.GeneralResponse;
import com.idstar.trainingkaryawan.model.response.TrainingResponse;
import com.idstar.trainingkaryawan.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/idstar/training")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;

    @PostMapping("/save")
    public ResponseEntity<GeneralResponse<TrainingResponse>> save(@RequestBody TrainingWithoutIdRequest request) {
        GeneralResponse<TrainingResponse> response = trainingService.save(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<GeneralResponse<TrainingResponse>> update(@RequestBody TrainingRequest request) {
        GeneralResponse<TrainingResponse> response = trainingService.update(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<GeneralResponse<Page<TrainingResponse>>> list(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        GeneralResponse<Page<TrainingResponse>> response = trainingService.list(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<TrainingResponse>> get(@PathVariable("id") Long id) {
        GeneralResponse<TrainingResponse> response = trainingService.get(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GeneralResponse<String>> delete(@RequestBody TrainingOnlyIdRequest request) {
        GeneralResponse<String> response = trainingService.delete(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
