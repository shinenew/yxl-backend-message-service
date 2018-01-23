package com.kingxunlian.message.biz.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageTemplateExample() {
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

        public Criteria andTemplateIdIsNull() {
            addCriterion("template_id is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(Long value) {
            addCriterion("template_id =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(Long value) {
            addCriterion("template_id <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(Long value) {
            addCriterion("template_id >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("template_id >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(Long value) {
            addCriterion("template_id <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(Long value) {
            addCriterion("template_id <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<Long> values) {
            addCriterion("template_id in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<Long> values) {
            addCriterion("template_id not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(Long value1, Long value2) {
            addCriterion("template_id between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(Long value1, Long value2) {
            addCriterion("template_id not between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeIsNull() {
            addCriterion("template_code is null");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeIsNotNull() {
            addCriterion("template_code is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeEqualTo(String value) {
            addCriterion("template_code =", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeNotEqualTo(String value) {
            addCriterion("template_code <>", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeGreaterThan(String value) {
            addCriterion("template_code >", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeGreaterThanOrEqualTo(String value) {
            addCriterion("template_code >=", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeLessThan(String value) {
            addCriterion("template_code <", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeLessThanOrEqualTo(String value) {
            addCriterion("template_code <=", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeLike(String value) {
            addCriterion("template_code like", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeNotLike(String value) {
            addCriterion("template_code not like", value, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeIn(List<String> values) {
            addCriterion("template_code in", values, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeNotIn(List<String> values) {
            addCriterion("template_code not in", values, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeBetween(String value1, String value2) {
            addCriterion("template_code between", value1, value2, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateCodeNotBetween(String value1, String value2) {
            addCriterion("template_code not between", value1, value2, "templateCode");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIsNull() {
            addCriterion("template_name is null");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIsNotNull() {
            addCriterion("template_name is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateNameEqualTo(String value) {
            addCriterion("template_name =", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotEqualTo(String value) {
            addCriterion("template_name <>", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameGreaterThan(String value) {
            addCriterion("template_name >", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameGreaterThanOrEqualTo(String value) {
            addCriterion("template_name >=", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLessThan(String value) {
            addCriterion("template_name <", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLessThanOrEqualTo(String value) {
            addCriterion("template_name <=", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLike(String value) {
            addCriterion("template_name like", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotLike(String value) {
            addCriterion("template_name not like", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIn(List<String> values) {
            addCriterion("template_name in", values, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotIn(List<String> values) {
            addCriterion("template_name not in", values, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameBetween(String value1, String value2) {
            addCriterion("template_name between", value1, value2, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotBetween(String value1, String value2) {
            addCriterion("template_name not between", value1, value2, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateContextIsNull() {
            addCriterion("template_context is null");
            return (Criteria) this;
        }

        public Criteria andTemplateContextIsNotNull() {
            addCriterion("template_context is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateContextEqualTo(String value) {
            addCriterion("template_context =", value, "templateContext");
            return (Criteria) this;
        }

        public Criteria andTemplateContextNotEqualTo(String value) {
            addCriterion("template_context <>", value, "templateContext");
            return (Criteria) this;
        }

        public Criteria andTemplateContextGreaterThan(String value) {
            addCriterion("template_context >", value, "templateContext");
            return (Criteria) this;
        }

        public Criteria andTemplateContextGreaterThanOrEqualTo(String value) {
            addCriterion("template_context >=", value, "templateContext");
            return (Criteria) this;
        }

        public Criteria andTemplateContextLessThan(String value) {
            addCriterion("template_context <", value, "templateContext");
            return (Criteria) this;
        }

        public Criteria andTemplateContextLessThanOrEqualTo(String value) {
            addCriterion("template_context <=", value, "templateContext");
            return (Criteria) this;
        }

        public Criteria andTemplateContextLike(String value) {
            addCriterion("template_context like", value, "templateContext");
            return (Criteria) this;
        }

        public Criteria andTemplateContextNotLike(String value) {
            addCriterion("template_context not like", value, "templateContext");
            return (Criteria) this;
        }

        public Criteria andTemplateContextIn(List<String> values) {
            addCriterion("template_context in", values, "templateContext");
            return (Criteria) this;
        }

        public Criteria andTemplateContextNotIn(List<String> values) {
            addCriterion("template_context not in", values, "templateContext");
            return (Criteria) this;
        }

        public Criteria andTemplateContextBetween(String value1, String value2) {
            addCriterion("template_context between", value1, value2, "templateContext");
            return (Criteria) this;
        }

        public Criteria andTemplateContextNotBetween(String value1, String value2) {
            addCriterion("template_context not between", value1, value2, "templateContext");
            return (Criteria) this;
        }

        public Criteria andTemplateUrlIsNull() {
            addCriterion("template_url is null");
            return (Criteria) this;
        }

        public Criteria andTemplateUrlIsNotNull() {
            addCriterion("template_url is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateUrlEqualTo(String value) {
            addCriterion("template_url =", value, "templateUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateUrlNotEqualTo(String value) {
            addCriterion("template_url <>", value, "templateUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateUrlGreaterThan(String value) {
            addCriterion("template_url >", value, "templateUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateUrlGreaterThanOrEqualTo(String value) {
            addCriterion("template_url >=", value, "templateUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateUrlLessThan(String value) {
            addCriterion("template_url <", value, "templateUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateUrlLessThanOrEqualTo(String value) {
            addCriterion("template_url <=", value, "templateUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateUrlLike(String value) {
            addCriterion("template_url like", value, "templateUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateUrlNotLike(String value) {
            addCriterion("template_url not like", value, "templateUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateUrlIn(List<String> values) {
            addCriterion("template_url in", values, "templateUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateUrlNotIn(List<String> values) {
            addCriterion("template_url not in", values, "templateUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateUrlBetween(String value1, String value2) {
            addCriterion("template_url between", value1, value2, "templateUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateUrlNotBetween(String value1, String value2) {
            addCriterion("template_url not between", value1, value2, "templateUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateAppIsNull() {
            addCriterion("template_app is null");
            return (Criteria) this;
        }

        public Criteria andTemplateAppIsNotNull() {
            addCriterion("template_app is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateAppEqualTo(String value) {
            addCriterion("template_app =", value, "templateApp");
            return (Criteria) this;
        }

        public Criteria andTemplateAppNotEqualTo(String value) {
            addCriterion("template_app <>", value, "templateApp");
            return (Criteria) this;
        }

        public Criteria andTemplateAppGreaterThan(String value) {
            addCriterion("template_app >", value, "templateApp");
            return (Criteria) this;
        }

        public Criteria andTemplateAppGreaterThanOrEqualTo(String value) {
            addCriterion("template_app >=", value, "templateApp");
            return (Criteria) this;
        }

        public Criteria andTemplateAppLessThan(String value) {
            addCriterion("template_app <", value, "templateApp");
            return (Criteria) this;
        }

        public Criteria andTemplateAppLessThanOrEqualTo(String value) {
            addCriterion("template_app <=", value, "templateApp");
            return (Criteria) this;
        }

        public Criteria andTemplateAppLike(String value) {
            addCriterion("template_app like", value, "templateApp");
            return (Criteria) this;
        }

        public Criteria andTemplateAppNotLike(String value) {
            addCriterion("template_app not like", value, "templateApp");
            return (Criteria) this;
        }

        public Criteria andTemplateAppIn(List<String> values) {
            addCriterion("template_app in", values, "templateApp");
            return (Criteria) this;
        }

        public Criteria andTemplateAppNotIn(List<String> values) {
            addCriterion("template_app not in", values, "templateApp");
            return (Criteria) this;
        }

        public Criteria andTemplateAppBetween(String value1, String value2) {
            addCriterion("template_app between", value1, value2, "templateApp");
            return (Criteria) this;
        }

        public Criteria andTemplateAppNotBetween(String value1, String value2) {
            addCriterion("template_app not between", value1, value2, "templateApp");
            return (Criteria) this;
        }

        public Criteria andTemplateEnableIsNull() {
            addCriterion("template_enable is null");
            return (Criteria) this;
        }

        public Criteria andTemplateEnableIsNotNull() {
            addCriterion("template_enable is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateEnableEqualTo(Byte value) {
            addCriterion("template_enable =", value, "templateEnable");
            return (Criteria) this;
        }

        public Criteria andTemplateEnableNotEqualTo(Byte value) {
            addCriterion("template_enable <>", value, "templateEnable");
            return (Criteria) this;
        }

        public Criteria andTemplateEnableGreaterThan(Byte value) {
            addCriterion("template_enable >", value, "templateEnable");
            return (Criteria) this;
        }

        public Criteria andTemplateEnableGreaterThanOrEqualTo(Byte value) {
            addCriterion("template_enable >=", value, "templateEnable");
            return (Criteria) this;
        }

        public Criteria andTemplateEnableLessThan(Byte value) {
            addCriterion("template_enable <", value, "templateEnable");
            return (Criteria) this;
        }

        public Criteria andTemplateEnableLessThanOrEqualTo(Byte value) {
            addCriterion("template_enable <=", value, "templateEnable");
            return (Criteria) this;
        }

        public Criteria andTemplateEnableIn(List<Byte> values) {
            addCriterion("template_enable in", values, "templateEnable");
            return (Criteria) this;
        }

        public Criteria andTemplateEnableNotIn(List<Byte> values) {
            addCriterion("template_enable not in", values, "templateEnable");
            return (Criteria) this;
        }

        public Criteria andTemplateEnableBetween(Byte value1, Byte value2) {
            addCriterion("template_enable between", value1, value2, "templateEnable");
            return (Criteria) this;
        }

        public Criteria andTemplateEnableNotBetween(Byte value1, Byte value2) {
            addCriterion("template_enable not between", value1, value2, "templateEnable");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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