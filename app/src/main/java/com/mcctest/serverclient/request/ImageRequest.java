package com.mcctest.serverclient.request;

import java.io.Serializable;

/**
 * Created by sanjoy on 8/29/18.
 */

public class ImageRequest implements Serializable {
    String AppId;
    String MenuId;

    public ImageRequest(String appId, String menuId) {
        AppId = appId;
        MenuId = menuId;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
