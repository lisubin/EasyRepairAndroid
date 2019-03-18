package com.repair.lsb.easyrepair.constat;

public class AppConfig {

  //  public final static String BASE_URL_PATH = "http://172.16.104.52:8080";
  // public final static String BASE_URL_PATH = "http://192.168.253.1:8080";
   public final static String BASE_URL_PATH = "http://192.168.43.120:8080";
    public final static String INSERT_USER = BASE_URL_PATH.concat("/user/addUser");
    public final static String SEARCH_USER = BASE_URL_PATH.concat("/user/searchUser");
    public final static String GET = BASE_URL_PATH.concat("/user/upload");
    public final static String GET_MERCHANT = BASE_URL_PATH.concat("/merchant/getAll");
    public final static String GET_DIALOGUE= BASE_URL_PATH.concat("/chat/getContent");
    public final static String GET_CONTENTS= BASE_URL_PATH.concat("/chat/getDialogues");

}
