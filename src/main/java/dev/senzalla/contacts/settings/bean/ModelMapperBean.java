package dev.senzalla.contacts.settings.bean;

import org.modelmapper.ModelMapper;

public class ModelMapperBean {
    protected static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
    }
}
