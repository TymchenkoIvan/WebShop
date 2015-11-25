package com.epam.preprod.tymchenko.util.querygenerator.select;

import com.epam.preprod.tymchenko.service.bean.SortFormBean;

public abstract class Generator {

    protected Generator next;

    public Generator setNext(Generator generator) {
        next = generator;
        return generator;
    }
    
    protected abstract StringBuilder append(SortFormBean bean,  StringBuilder string);
}
