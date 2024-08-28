package com.appointmensystem.appointmentsystem.services;

import com.appointmensystem.appointmentsystem.dto.PoliclinicCreateDto;
import com.appointmensystem.appointmentsystem.entities.Policlinic;
import com.appointmensystem.appointmentsystem.repository.PoliclinicRepository;
import com.appointmensystem.appointmentsystem.utilities.Result;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PoliclinicService {

    private final PoliclinicRepository policlinicRepository;
    private final ModelMapper modelMapper;

    //ADD
    public Result add(PoliclinicCreateDto args) {
        Policlinic policlinic = new Policlinic();

        modelMapper.map(args, policlinic);
        policlinicRepository.save(policlinic);

        return new Result(true, "Policy added successfully");
    }
}
