package com.idstar.trainingkaryawan.controller;

import com.idstar.trainingkaryawan.model.request.RekeningOnlyIdRequest;
import com.idstar.trainingkaryawan.model.request.RekeningRequest;
import com.idstar.trainingkaryawan.model.request.RekeningWithoutIdRequest;
import com.idstar.trainingkaryawan.model.response.GeneralResponse;
import com.idstar.trainingkaryawan.model.response.RekeningResponse;
import com.idstar.trainingkaryawan.service.RekeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/idstar/rekening")
public class RekeningController {

    @Autowired
    private RekeningService rekeningService;

    @PostMapping("/save")
    public ResponseEntity<GeneralResponse<RekeningResponse>> save(@RequestBody RekeningWithoutIdRequest request) {
        GeneralResponse<RekeningResponse> response = rekeningService.save(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<GeneralResponse<RekeningResponse>> update(@RequestBody RekeningRequest request) {
        GeneralResponse<RekeningResponse> response = rekeningService.update(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<GeneralResponse<Page<RekeningResponse>>> list(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) {
        GeneralResponse<Page<RekeningResponse>> response = rekeningService.list(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<GeneralResponse<RekeningResponse>> get(@PathVariable("id") Long id) {
        GeneralResponse<RekeningResponse> response = rekeningService.get(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GeneralResponse<String>> delete(@RequestBody RekeningOnlyIdRequest request) {
        GeneralResponse<String> response = rekeningService.delete(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
