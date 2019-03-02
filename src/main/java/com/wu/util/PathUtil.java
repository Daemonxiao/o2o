package com.wu.util;

public class PathUtil {
    private static String seperator = System.getProperty("file.separator");
    //返回图片根路径
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "F:/java/IdeaProjects/o2oimage";
        } else {
            basePath = "/home/image";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }
    //返回子路径
    public static String getShopImagePath(long shopId){
        String imagePath = "/upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", seperator);
    }
}
