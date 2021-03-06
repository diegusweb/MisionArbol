package com.diegusweb.dev.misionarbol.helper;

import com.diegusweb.dev.misionarbol.models.PointsTree;
import com.diegusweb.dev.misionarbol.models.Tree;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by HP on 31/01/2017.
 */

public class InfoConstants {
    public static final String BASE_URL = "http://10.0.0.5:8075/2017/laravel/misionarbol/public/api/";

    public static final String BASE_URL_IMG = "http://10.0.0.5:8075/2017/laravel/misionarbol/public/";

    public static final String BASE_URL_IMG_POINT = "http://10.0.0.5:8075/2017/laravel/misionarbol";

    //public static final String BASE_URL = "http://10.0.0.11:8075/2017/laravel/";

    public static final String BASE_URL_TEST = "https://api.github.com";

    public static final String PATH_VERSION = "/2.0";

    public static int SELECT_OPTION = 1;

    public static String API_TOKEN = "";

    public static String USER_EMAIL = "";

    public static String USER_NAME = "";

    public static int USER_ID = 12;

    public static double latDes = 0;
    public static double lonDes = 0;

    public  static final int zoomMap = 19;

    public static String CITY = "";
    public static String COUNTRY = "";

    public static int TYPE_SELECT = 0;

    public static String TYPE_NAME = null;

    public static List<Tree> ALL_TREE_LIBRARY = null;
    public static Tree ONE_TREE_LIBRARY = null;

    public static PointsTree ID_TREE_DETAIL = null;


}
