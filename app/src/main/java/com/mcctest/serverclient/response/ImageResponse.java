package com.mcctest.serverclient.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sanjoy on 8/29/18.
 */

public class ImageResponse implements Serializable {
    List<ImageDetail> contentfilelist;

    public List<ImageDetail> getContentfilelist() {
        return contentfilelist;
    }

    public void setContentfilelist(List<ImageDetail> contentfilelist) {
        this.contentfilelist = contentfilelist;
    }
}
