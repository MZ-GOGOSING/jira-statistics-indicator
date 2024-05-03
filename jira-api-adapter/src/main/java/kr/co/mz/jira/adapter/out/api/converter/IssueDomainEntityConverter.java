package kr.co.mz.jira.adapter.out.api.converter;


import com.atlassian.jira.rest.client.api.domain.*;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.support.converter.BiConverter;
import kr.co.mz.jira.support.converter.LocalDateTimeConverter;
import kr.co.mz.jira.support.converter.StreamConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.codehaus.jackson.*;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jettison.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class IssueDomainEntityConverter
    implements BiConverter<Issue, List<Worklog>, IssueDomainEntity> {

    private static final IssueTimeTrackingDomainEntityConverter ISSUE_TIME_TRACKING_DOMAIN_ENTITY_CONVERTER =
            new IssueTimeTrackingDomainEntityConverter();

    private static final IssueWorklogDomainEntityConverter ISSUE_WORKLOG_DOMAIN_ENTITY_CONVERTER =
            new IssueWorklogDomainEntityConverter();

    private static final IssueChangelogGroupDomainEntityConverter ISSUE_CHANGELOG_GROUP_DOMAIN_ENTITY_CONVERTER =
            new IssueChangelogGroupDomainEntityConverter();

    @Override
    public IssueDomainEntity convert(
        final Issue issue,
        final List<Worklog> worklogs
    ) {
        boolean isSubTask = false;
        String parentTask = "";
        JsonFactory jsonFactory = new MappingJsonFactory();
        JsonParser parser;
        ObjectCodec obj;
        JsonNode node;

        if(issue.getIssueType().isSubtask()){
            try {
                isSubTask = true;
                //{"id":"820998","key":"ITO-199","self":"https:\/\/jira.woowa.in\/rest\/api\/2\/issue\/820998","fields":{"summary":"[어드민]인터뷰전형_심사결과 PDF 다운로드 기능 추가","status":{"self":"https:\/\/jira.woowa.in\/rest\/api\/2\/status\/10305","description":"테스트 진행 중","iconUrl":"https:\/\/jira.woowa.in\/images\/icons\/statuses\/generic.png","name":"In Test","id":"10305","statusCategory":{"self":"https:\/\/jira.woowa.in\/rest\/api\/2\/statuscategory\/4","id":4,"key":"indeterminate","colorName":"yellow","name":"In Progress"}},"priority":{"self":"https:\/\/jira.woowa.in\/rest\/api\/2\/priority\/3","iconUrl":"https:\/\/jira.woowa.in\/images\/icons\/priorities\/medium.svg","name":"Medium","id":"3"},"issuetype":{"self":"https:\/\/jira.woowa.in\/rest\/api\/2\/issuetype\/10013","id":"10013","description":"아직 개발되지 않은 새로운 기능이 요구될 때 사용한다.","iconUrl":"https:\/\/jira.woowa.in\/secure\/viewavatar?size=xsmall&avatarId=10505&avatarType=issuetype","name":"New Feature","subtask":false,"avatarId":10505}}}
                IssueField issueParentField = issue.getField("parent"); // Epic Link
                parser = jsonFactory.createJsonParser(issueParentField.getValue().toString());
                obj = parser.getCodec();
                node = obj.readTree(parser);
                parentTask = node.get("key").getTextValue();
            } catch (JsonParseException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // IssueField{id=customfield_10006, name=Epic Link, type=null, value=ITO-1}
        IssueField issueField = issue.getField("customfield_10006"); // Epic Link
        String epicKey = ObjectUtils.defaultIfNull(issueField.getValue(), "").toString();

        // Sprint
        IssueField sprintField = issue.getField("customfield_10004"); // Sprint
        String sprintName = "";
        try {
            final var test = (JSONArray) ObjectUtils.defaultIfNull(sprintField.getValue(), "[]");
            sprintName = ObjectUtils.defaultIfNull(test.toString().substring(test.toString().indexOf(",name=")+6, test.toString().indexOf(",startDate=")), "");
        } catch (Exception e) { }
        // End of Sprint

        return IssueDomainEntity.fromOrigin(
                epicKey,
                issue.getKey(),
                issue.getSelf().toString(),
                Optional.ofNullable(issue.getWatchers())
                        .map(BasicWatchers::getSelf)
                        .map(URI::toString)
                        .orElse(null),
                issue.getLabels(),
                LocalDateTimeConverter.fromJoda(issue.getDueDate()),
                LocalDateTimeConverter.fromJoda(issue.getUpdateDate()),
                LocalDateTimeConverter.fromJoda(issue.getCreationDate()),
                Optional.ofNullable(issue.getAssignee())
                        .map(AddressableNamedEntity::getName)
                        .orElse(null),
                Optional.ofNullable(issue.getReporter())
                        .map(AddressableNamedEntity::getName)
                        .orElse(null),
                issue.getSummary(),
                issue.getIssueType().getName(),
                issue.getStatus().getName(),
                sprintName,
                parentTask,
                isSubTask,
                this.getComponentsName(IteratorUtils.toList(issue.getComponents().iterator())),
                this.convertObjectFromLocalDateTime(issue.getField("customfield_10025").getValue()),
                Optional.ofNullable(issue.getTimeTracking())
                        .map(ISSUE_TIME_TRACKING_DOMAIN_ENTITY_CONVERTER::convert)
                        .orElse(null),
                StreamConverter
                        .fromIterable(this.getWorklogs(issue, worklogs))
                        .map(ISSUE_WORKLOG_DOMAIN_ENTITY_CONVERTER::convert)
                        .collect(Collectors.toList()),
                StreamConverter
                        .fromIterable(issue.getChangelog())
                        .map(ISSUE_CHANGELOG_GROUP_DOMAIN_ENTITY_CONVERTER::convert)
                        .collect(Collectors.toList())
        );
    }

    private List<Worklog> getWorklogs(final Issue issue, final List<Worklog> worklogs) {
        if (CollectionUtils.isNotEmpty(worklogs)) {
            return worklogs;
        }
        return StreamConverter
            .fromIterable(issue.getWorklogs())
            .collect(Collectors.toList());
    }

    /**
     * 첫번째 고정
     * TODO 최초 요구사항은 첫번째 고정, 미래에는 달라질수가 있다.
     */
    private String getComponentsName(final List<BasicComponent> components) {
        if (CollectionUtils.isEmpty(components)) {
            // 구성 요소 없을 시
            return "";
        }
        return components.get(0).getName();
    }
    private LocalDateTime convertObjectFromLocalDateTime(Object obj) {
        return Objects.isNull(obj) ? null : LocalDate.parse(obj.toString()).atStartOfDay();
    }
}
