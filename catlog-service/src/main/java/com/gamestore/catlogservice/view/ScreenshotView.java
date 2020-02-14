/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.view;

import com.gamestore.catlogservice.entity.Screenshot;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author qbuser
 */
public class ScreenshotView {

    List<DocumentView> documentViews;

    public ScreenshotView(List<Screenshot> screenshots) {
        List<DocumentView> collect = screenshots.stream()
                .collect(Collectors.mapping(fn -> new DocumentView(fn.getDocument()),
                        Collectors.toList()));
        this.documentViews = collect;
    }

    public List<DocumentView> getDocumentViews() {
        return documentViews;
    }

    public void setDocumentViews(List<DocumentView> documentViews) {
        this.documentViews = documentViews;
    }
}
