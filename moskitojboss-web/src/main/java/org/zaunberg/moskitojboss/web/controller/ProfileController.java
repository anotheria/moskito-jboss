package org.zaunberg.moskitojboss.web.controller;


import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;

/**
 *
 */
@WebController
public class ProfileController extends UserController {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log;
    
    private boolean editable;

    // -------------- Initialization ------------------------------------------------------
    @PostConstruct
    public void init() {
        editable = false;
    }

    // -------------- Actions -------------------------------------------------------------
    public void enableEditMode() {
        setEditable(true);
        log.info("Profile edit mode has been started.");
    }

    public void disableEditMode() {
        setEditable(false);
        log.info("Profile changes were discarded.");
    }

    // -------------- Getter / Setter -----------------------------------------------------
    public boolean getEditable() {
        return this.editable;
    }

    public void setEditable(final boolean editable) {
        this.editable = editable;
    }
}
