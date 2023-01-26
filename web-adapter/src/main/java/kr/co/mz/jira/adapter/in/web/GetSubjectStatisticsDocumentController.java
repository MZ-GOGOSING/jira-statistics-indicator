package kr.co.mz.jira.adapter.in.web;

import static kr.co.mz.jira.adapter.in.web.config.mvc.CustomMediaType.APPLICATION_XLSX_SPREADSHEET_VALUE;
import static kr.co.mz.jira.support.converter.DefaultDateTimeConverter.convertDate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import kr.co.mz.jira.adapter.in.web.response.support.AttachmentResponseHeaderSupport;
import kr.co.mz.jira.application.port.in.GetSubjectDocumentQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "DOCUMENT", description = "통계 문서 다운로드 API")
@Validated
@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class GetSubjectStatisticsDocumentController implements AttachmentResponseHeaderSupport {

  private static final String RESPONSE_FILENAME_FORMAT = "subject(%s)-(v.%s).xlsx";

  private final GetSubjectDocumentQuery getSubjectDocumentQuery;

  @Operation(summary = "특정 SUBJECT 통계 문서 다운로드", description = "특정 SUBJECT 의 통계 문서를 다운로드 할 수 있습니다.")
  @ResponseBody
  @GetMapping(value = "/subject/{uuid}/download", produces = {APPLICATION_XLSX_SPREADSHEET_VALUE})
  public ResponseEntity<byte[]> getSubjectDocument(
      @Parameter(description = "SUBJECT UUID")
      final @PathVariable @NotBlank String uuid
  ) {
    final var inResponse = getSubjectDocumentQuery.loadByUuid(uuid);
    final var filename = String.format(RESPONSE_FILENAME_FORMAT, uuid, convertDate(LocalDate.now()));

    return ResponseEntity
        .ok()
        .headers(this.proceedResponseHeaders(filename))
        .body(inResponse);
  }
}
