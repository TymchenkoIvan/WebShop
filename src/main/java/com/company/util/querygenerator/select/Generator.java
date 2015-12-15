package com.company.util.querygenerator.select;

import com.company.service.bean.SortFormBean;

public abstract class Generator {

    protected Generator next;

    public Generator setNext(Generator generator) {
        next = generator;
        return generator;
    }
    
    protected abstract StringBuilder append(SortFormBean bean,  StringBuilder string);
}
