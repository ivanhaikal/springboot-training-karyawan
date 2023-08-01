package com.idstar.trainingkaryawan.service.impl;

import com.idstar.trainingkaryawan.entity.DetailKaryawan;
import com.idstar.trainingkaryawan.entity.Karyawan;
import com.idstar.trainingkaryawan.model.request.KaryawanOnlyIdRequest;
import com.idstar.trainingkaryawan.model.request.KaryawanWithoutIdRequest;
import com.idstar.trainingkaryawan.model.request.KaryawanRequest;
import com.idstar.trainingkaryawan.model.response.DetailKaryawanResponse;
import com.idstar.trainingkaryawan.model.response.GeneralResponse;
import com.idstar.trainingkaryawan.model.response.KaryawanResponse;
import com.idstar.trainingkaryawan.repository.DetailKaryawanRepository;
import com.idstar.trainingkaryawan.repository.KaryawanRepository;
import com.idstar.trainingkaryawan.service.KaryawanService;
import com.idstar.trainingkaryawan.service.ValidationService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
public class KaryawanServiceImpl implements KaryawanService {

    @Autowired
    private DetailKaryawanRepository detailKaryawanRepository;

    @Autowired
    private KaryawanRepository karyawanRepository;

    @Autowired
    private ValidationService validationService;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Transactional
    @SneakyThrows
    @Override
    public GeneralResponse<KaryawanResponse> save(KaryawanWithoutIdRequest request) {
        validationService.validate(request);
        validationService.validate(request.getDetailKaryawan());

        DetailKaryawan detailKaryawan = new DetailKaryawan();
        detailKaryawan.setNik(request.getDetailKaryawan().getNik());
        detailKaryawan.setNpwp(request.getDetailKaryawan().getNpwp());
        detailKaryawanRepository.save(detailKaryawan);

        Karyawan karyawan = new Karyawan();
        karyawan.setNama(request.getNama());
        karyawan.setDob(dateFormat.parse(request.getDob()));
        karyawan.setStatus(request.getStatus());
        karyawan.setAlamat(request.getAlamat());
        karyawan.setDetailKaryawan(detailKaryawan);
        karyawanRepository.save(karyawan);

        return new GeneralResponse<>(HttpStatus.OK.value(), karyawanResponse(karyawan), HttpStatus.OK.getReasonPhrase());
    }

    @Transactional
    @SneakyThrows
    @Override
    public GeneralResponse<KaryawanResponse> update(KaryawanRequest request) {
        validationService.validate(request);
        validationService.validate(request.getDetailKaryawan());

        Karyawan karyawan = karyawanRepository.findById(request.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID karyawan not found")
        );

        DetailKaryawan detailKaryawan = detailKaryawanRepository.findById(request.getDetailKaryawan().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID detail karyawan not found")
        );

        detailKaryawan.setNik(request.getDetailKaryawan().getNik());
        detailKaryawan.setNpwp(request.getDetailKaryawan().getNpwp());
        detailKaryawanRepository.save(detailKaryawan);

        karyawan.setNama(request.getNama());
        karyawan.setDob(dateFormat.parse(request.getDob()));
        karyawan.setStatus(request.getStatus());
        karyawan.setAlamat(request.getAlamat());
        karyawan.setDetailKaryawan(detailKaryawan);
        karyawanRepository.save(karyawan);

        return new GeneralResponse<>(HttpStatus.OK.value(), karyawanResponse(karyawan), HttpStatus.OK.getReasonPhrase());
    }

    @Transactional(readOnly = true)
    @Override
    public GeneralResponse<Page<KaryawanResponse>> list(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Karyawan> karyawans = karyawanRepository.findAll(pageable);
        return new GeneralResponse<>(HttpStatus.OK.value(),
                karyawans.map(this::karyawanResponse),
                HttpStatus.OK.getReasonPhrase());
    }

    @Transactional(readOnly = true)
    @Override
    public GeneralResponse<KaryawanResponse> get(Long id) {
        Karyawan karyawan = karyawanRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID karyawan not found")
        );

        return new GeneralResponse<>(HttpStatus.OK.value(), karyawanResponse(karyawan), HttpStatus.OK.getReasonPhrase());
    }

    @Transactional
    @Override
    public GeneralResponse<String> delete(KaryawanOnlyIdRequest request) {
        Karyawan karyawan = karyawanRepository.findById(request.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID karyawan not found")
        );

        if (Objects.nonNull(karyawan.getDetailKaryawan())) {
            DetailKaryawan detailKaryawan = karyawan.getDetailKaryawan();
            detailKaryawan.setDeletedDate(new Date());
            detailKaryawanRepository.save(detailKaryawan);
        }

        karyawan.setDeletedDate(new Date());
        karyawanRepository.save(karyawan);

        return new GeneralResponse<>(HttpStatus.OK.value(), "Success delete", HttpStatus.OK.getReasonPhrase());
    }

    private KaryawanResponse karyawanResponse(Karyawan karyawan) {
        return new KaryawanResponse(
                karyawan.getCreatedDate(), karyawan.getUpdatedDate(), karyawan.getDeletedDate(),
                karyawan.getId(), karyawan.getNama(), karyawan.getDob(), karyawan.getStatus(),
                karyawan.getAlamat(), new DetailKaryawanResponse(
                karyawan.getDetailKaryawan().getCreatedDate(), karyawan.getDetailKaryawan().getUpdatedDate(),
                karyawan.getDetailKaryawan().getDeletedDate(), karyawan.getDetailKaryawan().getId(),
                karyawan.getDetailKaryawan().getNik(), karyawan.getDetailKaryawan().getNpwp()
        ));
    }
}
