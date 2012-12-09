package org.zaunberg.moskitojboss.web.controller;

import java.io.Serializable;

import org.zaunberg.moskitojboss.domain.enums.Category;

@WebController
public class CategoryController implements Serializable {

    private static final long serialVersionUID = 1L;

    public Category[] getCategoryFilterTypes() {
    	return Category.values();
    }

}
