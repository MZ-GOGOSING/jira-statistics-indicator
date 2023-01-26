package kr.co.mz.jira.jpa.querydsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public interface QueryDslThetaJoinOrderBySupport {

  /*
  final var orderSpecifierList = this.getOrderSpecifierList(pageable.getSort());

  jpqlQuery.limit(pageable.getPageSize());
  jpqlQuery.offset(pageable.getOffset());

  orderSpecifierList.forEach(jpqlQuery::orderBy);

  final var content = jpqlQuery.fetch();

  return new PageImpl<>(content, pageable, totalCount);

  @Override
  public Map<String, ComparableExpressionBase<?>> getSortingStrategyMap() {
    return Map.ofEntries(
        entry( // 1. 조직 (상위조직포함).
            Q_DEPARTMENT_ENTITY.departmentFullName.getMetadata().getName(),
            Q_DEPARTMENT_ENTITY.departmentFullName),
        entry( // 2. 원소속.
            Q_DEPARTMENT_ENTITY.departmentName.getMetadata().getName(),
            Q_DEPARTMENT_ENTITY.departmentName),
        entry( // 3. 고용형태.
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.toWorkTypeCode.getMetadata().getName(),
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.toWorkTypeCode),
        entry( // 4. 직군.
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.toJobGroupCode.getMetadata().getName(),
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.toJobGroupCode),
        entry( // 5. 직무.
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.toJobCode.getMetadata().getName(),
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.toJobCode),
        entry( // 6. 변경후 기말인원.
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.planHistoryToPlanCount.getMetadata().getName(),
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.planHistoryToPlanCount),
        entry( // 7. 변경전 기말인원.
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.planHistoryFromPlanCount.getMetadata().getName(),
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.planHistoryFromPlanCount),
        entry( // 8. 요청구분.
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.planHistoryTypeCode.getMetadata().getName(),
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.planHistoryTypeCode),
        entry( // 9. 문서번호.
            Q_REQUEST_MAIN_ENTITY.requestSeq.getMetadata().getName(),
            Q_REQUEST_MAIN_ENTITY.requestSeq),
        entry( // 10. 결제상태.
            Q_REQUEST_MAIN_ENTITY.requestMainStatusCode.getMetadata().getName(),
            Q_REQUEST_MAIN_ENTITY.requestMainStatusCode),
        entry( // 11. 승인일자.
            Q_REQUEST_MAIN_ENTITY.requestApprovalDate.getMetadata().getName(),
            Q_REQUEST_MAIN_ENTITY.requestApprovalDate),
        entry( // 12. 기준일자.
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.planHistoryDay.getMetadata().getName(),
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.planHistoryDay),
        entry( // 13. 변경일시.
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.updateDate.getMetadata().getName(),
            Q_PERSONNEL_PLAN_HISTORY_ENTITY.updateDate)
    );
  }

  @Override
  public List<? extends OrderSpecifier<?>> getDefaultOrderSpecifierList() {
    return List.of(Q_DEPARTMENT_ENTITY.departmentOrder.asc());
  }
  */

  Map<String, ComparableExpressionBase<?>> getSortingStrategyMap();

  List<? extends OrderSpecifier<?>> getDefaultOrderSpecifierList();

  default List<? extends OrderSpecifier<?>> getOrderSpecifierList(final Sort sort) {
    final var orderSpecifierList = sort
        .get()
        .map(this::getOrderSpecifier)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    if (CollectionUtils.isEmpty(orderSpecifierList)) {
      return getDefaultOrderSpecifierList();
    }

    return orderSpecifierList;
  }

  private OrderSpecifier<?> getOrderSpecifier(final Order order) {
    final var property = order.getProperty();
    final var direction = order.getDirection();

    final var metaProperty = getSortingStrategyMap().get(property);

    if (Objects.isNull(metaProperty)) {
      return null;
    }

    return Direction.ASC == direction
        ? metaProperty.asc()
        : metaProperty.desc();
  }
}
