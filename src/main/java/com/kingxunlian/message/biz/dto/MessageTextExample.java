package com.kingxunlian.message.biz.dto;

import com.kingxunlian.message.dto.enums.MessageTypeEnum;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageTextExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageTextExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMessageIdIsNull() {
            addCriterion("message_id is null");
            return (Criteria) this;
        }

        public Criteria andMessageIdIsNotNull() {
            addCriterion("message_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessageIdEqualTo(Long value) {
            addCriterion("message_id =", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotEqualTo(Long value) {
            addCriterion("message_id <>", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThan(Long value) {
            addCriterion("message_id >", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("message_id >=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThan(Long value) {
            addCriterion("message_id <", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThanOrEqualTo(Long value) {
            addCriterion("message_id <=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdIn(List<Long> values) {
            addCriterion("message_id in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotIn(List<Long> values) {
            addCriterion("message_id not in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdBetween(Long value1, Long value2) {
            addCriterion("message_id between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotBetween(Long value1, Long value2) {
            addCriterion("message_id not between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageContentIsNull() {
            addCriterion("message_content is null");
            return (Criteria) this;
        }

        public Criteria andMessageContentIsNotNull() {
            addCriterion("message_content is not null");
            return (Criteria) this;
        }

        public Criteria andMessageContentEqualTo(String value) {
            addCriterion("message_content =", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotEqualTo(String value) {
            addCriterion("message_content <>", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentGreaterThan(String value) {
            addCriterion("message_content >", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentGreaterThanOrEqualTo(String value) {
            addCriterion("message_content >=", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLessThan(String value) {
            addCriterion("message_content <", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLessThanOrEqualTo(String value) {
            addCriterion("message_content <=", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLike(String value) {
            addCriterion("message_content like", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotLike(String value) {
            addCriterion("message_content not like", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentIn(List<String> values) {
            addCriterion("message_content in", values, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotIn(List<String> values) {
            addCriterion("message_content not in", values, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentBetween(String value1, String value2) {
            addCriterion("message_content between", value1, value2, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotBetween(String value1, String value2) {
            addCriterion("message_content not between", value1, value2, "messageContent");
            return (Criteria) this;
        }

        public Criteria andPostDateIsNull() {
            addCriterion("post_date is null");
            return (Criteria) this;
        }

        public Criteria andPostDateIsNotNull() {
            addCriterion("post_date is not null");
            return (Criteria) this;
        }

        public Criteria andPostDateEqualTo(Date value) {
            addCriterion("post_date =", value, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateNotEqualTo(Date value) {
            addCriterion("post_date <>", value, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateGreaterThan(Date value) {
            addCriterion("post_date >", value, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateGreaterThanOrEqualTo(Date value) {
            addCriterion("post_date >=", value, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateLessThan(Date value) {
            addCriterion("post_date <", value, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateLessThanOrEqualTo(Date value) {
            addCriterion("post_date <=", value, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateIn(List<Date> values) {
            addCriterion("post_date in", values, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateNotIn(List<Date> values) {
            addCriterion("post_date not in", values, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateBetween(Date value1, Date value2) {
            addCriterion("post_date between", value1, value2, "postDate");
            return (Criteria) this;
        }

        public Criteria andPostDateNotBetween(Date value1, Date value2) {
            addCriterion("post_date not between", value1, value2, "postDate");
            return (Criteria) this;
        }

        public Criteria andMessageTitleIsNull() {
            addCriterion("message_title is null");
            return (Criteria) this;
        }

        public Criteria andMessageTitleIsNotNull() {
            addCriterion("message_title is not null");
            return (Criteria) this;
        }

        public Criteria andMessageTitleEqualTo(String value) {
            addCriterion("message_title =", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotEqualTo(String value) {
            addCriterion("message_title <>", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleGreaterThan(String value) {
            addCriterion("message_title >", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleGreaterThanOrEqualTo(String value) {
            addCriterion("message_title >=", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLessThan(String value) {
            addCriterion("message_title <", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLessThanOrEqualTo(String value) {
            addCriterion("message_title <=", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLike(String value) {
            addCriterion("message_title like", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotLike(String value) {
            addCriterion("message_title not like", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleIn(List<String> values) {
            addCriterion("message_title in", values, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotIn(List<String> values) {
            addCriterion("message_title not in", values, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleBetween(String value1, String value2) {
            addCriterion("message_title between", value1, value2, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotBetween(String value1, String value2) {
            addCriterion("message_title not between", value1, value2, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNull() {
            addCriterion("creator_id is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNotNull() {
            addCriterion("creator_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdEqualTo(String value) {
            addCriterion("creator_id =", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotEqualTo(String value) {
            addCriterion("creator_id <>", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThan(String value) {
            addCriterion("creator_id >", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("creator_id >=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThan(String value) {
            addCriterion("creator_id <", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThanOrEqualTo(String value) {
            addCriterion("creator_id <=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLike(String value) {
            addCriterion("creator_id like", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotLike(String value) {
            addCriterion("creator_id not like", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIn(List<String> values) {
            addCriterion("creator_id in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotIn(List<String> values) {
            addCriterion("creator_id not in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdBetween(String value1, String value2) {
            addCriterion("creator_id between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotBetween(String value1, String value2) {
            addCriterion("creator_id not between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andMessageTypeIsNull() {
            addCriterion("message_type is null");
            return (Criteria) this;
        }

        public Criteria andMessageTypeIsNotNull() {
            addCriterion("message_type is not null");
            return (Criteria) this;
        }

        public Criteria andMessageTypeEqualTo(MessageTypeEnum value) {
            addCriterion("message_type =", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotEqualTo(MessageTypeEnum value) {
            addCriterion("message_type <>", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeGreaterThan(MessageTypeEnum value) {
            addCriterion("message_type >", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeGreaterThanOrEqualTo(MessageTypeEnum value) {
            addCriterion("message_type >=", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeLessThan(MessageTypeEnum value) {
            addCriterion("message_type <", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeLessThanOrEqualTo(MessageTypeEnum value) {
            addCriterion("message_type <=", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeIn(List<MessageTypeEnum> values) {
            addCriterion("message_type in", values, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotIn(List<MessageTypeEnum> values) {
            addCriterion("message_type not in", values, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeBetween(MessageTypeEnum value1, MessageTypeEnum value2) {
            addCriterion("message_type between", value1, value2, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotBetween(MessageTypeEnum value1, MessageTypeEnum value2) {
            addCriterion("message_type not between", value1, value2, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageSystemIsNull() {
            addCriterion("message_system is null");
            return (Criteria) this;
        }

        public Criteria andMessageSystemIsNotNull() {
            addCriterion("message_system is not null");
            return (Criteria) this;
        }

        public Criteria andMessageSystemEqualTo(String value) {
            addCriterion("message_system =", value, "messageSystem");
            return (Criteria) this;
        }

        public Criteria andMessageSystemNotEqualTo(String value) {
            addCriterion("message_system <>", value, "messageSystem");
            return (Criteria) this;
        }

        public Criteria andMessageSystemGreaterThan(String value) {
            addCriterion("message_system >", value, "messageSystem");
            return (Criteria) this;
        }

        public Criteria andMessageSystemGreaterThanOrEqualTo(String value) {
            addCriterion("message_system >=", value, "messageSystem");
            return (Criteria) this;
        }

        public Criteria andMessageSystemLessThan(String value) {
            addCriterion("message_system <", value, "messageSystem");
            return (Criteria) this;
        }

        public Criteria andMessageSystemLessThanOrEqualTo(String value) {
            addCriterion("message_system <=", value, "messageSystem");
            return (Criteria) this;
        }

        public Criteria andMessageSystemLike(String value) {
            addCriterion("message_system like", value, "messageSystem");
            return (Criteria) this;
        }

        public Criteria andMessageSystemNotLike(String value) {
            addCriterion("message_system not like", value, "messageSystem");
            return (Criteria) this;
        }

        public Criteria andMessageSystemIn(List<String> values) {
            addCriterion("message_system in", values, "messageSystem");
            return (Criteria) this;
        }

        public Criteria andMessageSystemNotIn(List<String> values) {
            addCriterion("message_system not in", values, "messageSystem");
            return (Criteria) this;
        }

        public Criteria andMessageSystemBetween(String value1, String value2) {
            addCriterion("message_system between", value1, value2, "messageSystem");
            return (Criteria) this;
        }

        public Criteria andMessageSystemNotBetween(String value1, String value2) {
            addCriterion("message_system not between", value1, value2, "messageSystem");
            return (Criteria) this;
        }

        public Criteria andMessageUrlIsNull() {
            addCriterion("message_url is null");
            return (Criteria) this;
        }

        public Criteria andMessageUrlIsNotNull() {
            addCriterion("message_url is not null");
            return (Criteria) this;
        }

        public Criteria andMessageUrlEqualTo(String value) {
            addCriterion("message_url =", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlNotEqualTo(String value) {
            addCriterion("message_url <>", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlGreaterThan(String value) {
            addCriterion("message_url >", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("message_url >=", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlLessThan(String value) {
            addCriterion("message_url <", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlLessThanOrEqualTo(String value) {
            addCriterion("message_url <=", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlLike(String value) {
            addCriterion("message_url like", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlNotLike(String value) {
            addCriterion("message_url not like", value, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlIn(List<String> values) {
            addCriterion("message_url in", values, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlNotIn(List<String> values) {
            addCriterion("message_url not in", values, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlBetween(String value1, String value2) {
            addCriterion("message_url between", value1, value2, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageUrlNotBetween(String value1, String value2) {
            addCriterion("message_url not between", value1, value2, "messageUrl");
            return (Criteria) this;
        }

        public Criteria andMessageExtraIsNull() {
            addCriterion("message_extra is null");
            return (Criteria) this;
        }

        public Criteria andMessageExtraIsNotNull() {
            addCriterion("message_extra is not null");
            return (Criteria) this;
        }

        public Criteria andMessageExtraEqualTo(String value) {
            addCriterion("message_extra =", value, "messageExtra");
            return (Criteria) this;
        }

        public Criteria andMessageExtraNotEqualTo(String value) {
            addCriterion("message_extra <>", value, "messageExtra");
            return (Criteria) this;
        }

        public Criteria andMessageExtraGreaterThan(String value) {
            addCriterion("message_extra >", value, "messageExtra");
            return (Criteria) this;
        }

        public Criteria andMessageExtraGreaterThanOrEqualTo(String value) {
            addCriterion("message_extra >=", value, "messageExtra");
            return (Criteria) this;
        }

        public Criteria andMessageExtraLessThan(String value) {
            addCriterion("message_extra <", value, "messageExtra");
            return (Criteria) this;
        }

        public Criteria andMessageExtraLessThanOrEqualTo(String value) {
            addCriterion("message_extra <=", value, "messageExtra");
            return (Criteria) this;
        }

        public Criteria andMessageExtraLike(String value) {
            addCriterion("message_extra like", value, "messageExtra");
            return (Criteria) this;
        }

        public Criteria andMessageExtraNotLike(String value) {
            addCriterion("message_extra not like", value, "messageExtra");
            return (Criteria) this;
        }

        public Criteria andMessageExtraIn(List<String> values) {
            addCriterion("message_extra in", values, "messageExtra");
            return (Criteria) this;
        }

        public Criteria andMessageExtraNotIn(List<String> values) {
            addCriterion("message_extra not in", values, "messageExtra");
            return (Criteria) this;
        }

        public Criteria andMessageExtraBetween(String value1, String value2) {
            addCriterion("message_extra between", value1, value2, "messageExtra");
            return (Criteria) this;
        }

        public Criteria andMessageExtraNotBetween(String value1, String value2) {
            addCriterion("message_extra not between", value1, value2, "messageExtra");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}