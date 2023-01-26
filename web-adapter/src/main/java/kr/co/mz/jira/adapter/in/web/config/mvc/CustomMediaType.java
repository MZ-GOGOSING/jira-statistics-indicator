package kr.co.mz.jira.adapter.in.web.config.mvc;

import org.springframework.http.MediaType;

public class CustomMediaType extends MediaType {

  public static final MediaType APPLICATION_XLS_SPREADSHEET;

  public static final String APPLICATION_XLS_SPREADSHEET_VALUE = "application/vnd.ms-excel";

  public static final MediaType APPLICATION_XLSX_SPREADSHEET;

  public static final String APPLICATION_XLSX_SPREADSHEET_VALUE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

  static {
    APPLICATION_XLS_SPREADSHEET = new MediaType("application", "vnd.ms-excel");
    APPLICATION_XLSX_SPREADSHEET = new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet");
  }

  public CustomMediaType(String type) {
    super(type);
  }

  public CustomMediaType(String type, String subtype) {
    super(type, subtype);
  }
}
