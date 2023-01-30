package com.ws.taskmanager.mapper;

import org.modelmapper.ModelMapper;

public class ModelMapperService {

    private static ModelMapper mapper = new ModelMapper();

    public static <O, D>D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

}
