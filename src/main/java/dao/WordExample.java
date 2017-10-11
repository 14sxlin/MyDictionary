package dao;

import java.util.ArrayList;
import java.util.List;

public class WordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WordExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEngIsNull() {
            addCriterion("ENG is null");
            return (Criteria) this;
        }

        public Criteria andEngIsNotNull() {
            addCriterion("ENG is not null");
            return (Criteria) this;
        }

        public Criteria andEngEqualTo(String value) {
            addCriterion("ENG =", value, "eng");
            return (Criteria) this;
        }

        public Criteria andEngNotEqualTo(String value) {
            addCriterion("ENG <>", value, "eng");
            return (Criteria) this;
        }

        public Criteria andEngGreaterThan(String value) {
            addCriterion("ENG >", value, "eng");
            return (Criteria) this;
        }

        public Criteria andEngGreaterThanOrEqualTo(String value) {
            addCriterion("ENG >=", value, "eng");
            return (Criteria) this;
        }

        public Criteria andEngLessThan(String value) {
            addCriterion("ENG <", value, "eng");
            return (Criteria) this;
        }

        public Criteria andEngLessThanOrEqualTo(String value) {
            addCriterion("ENG <=", value, "eng");
            return (Criteria) this;
        }

        public Criteria andEngLike(String value) {
            addCriterion("ENG like", value, "eng");
            return (Criteria) this;
        }

        public Criteria andEngNotLike(String value) {
            addCriterion("ENG not like", value, "eng");
            return (Criteria) this;
        }

        public Criteria andEngIn(List<String> values) {
            addCriterion("ENG in", values, "eng");
            return (Criteria) this;
        }

        public Criteria andEngNotIn(List<String> values) {
            addCriterion("ENG not in", values, "eng");
            return (Criteria) this;
        }

        public Criteria andEngBetween(String value1, String value2) {
            addCriterion("ENG between", value1, value2, "eng");
            return (Criteria) this;
        }

        public Criteria andEngNotBetween(String value1, String value2) {
            addCriterion("ENG not between", value1, value2, "eng");
            return (Criteria) this;
        }

        public Criteria andEngPronIsNull() {
            addCriterion("ENG_PRON is null");
            return (Criteria) this;
        }

        public Criteria andEngPronIsNotNull() {
            addCriterion("ENG_PRON is not null");
            return (Criteria) this;
        }

        public Criteria andEngPronEqualTo(String value) {
            addCriterion("ENG_PRON =", value, "engPron");
            return (Criteria) this;
        }

        public Criteria andEngPronNotEqualTo(String value) {
            addCriterion("ENG_PRON <>", value, "engPron");
            return (Criteria) this;
        }

        public Criteria andEngPronGreaterThan(String value) {
            addCriterion("ENG_PRON >", value, "engPron");
            return (Criteria) this;
        }

        public Criteria andEngPronGreaterThanOrEqualTo(String value) {
            addCriterion("ENG_PRON >=", value, "engPron");
            return (Criteria) this;
        }

        public Criteria andEngPronLessThan(String value) {
            addCriterion("ENG_PRON <", value, "engPron");
            return (Criteria) this;
        }

        public Criteria andEngPronLessThanOrEqualTo(String value) {
            addCriterion("ENG_PRON <=", value, "engPron");
            return (Criteria) this;
        }

        public Criteria andEngPronLike(String value) {
            addCriterion("ENG_PRON like", value, "engPron");
            return (Criteria) this;
        }

        public Criteria andEngPronNotLike(String value) {
            addCriterion("ENG_PRON not like", value, "engPron");
            return (Criteria) this;
        }

        public Criteria andEngPronIn(List<String> values) {
            addCriterion("ENG_PRON in", values, "engPron");
            return (Criteria) this;
        }

        public Criteria andEngPronNotIn(List<String> values) {
            addCriterion("ENG_PRON not in", values, "engPron");
            return (Criteria) this;
        }

        public Criteria andEngPronBetween(String value1, String value2) {
            addCriterion("ENG_PRON between", value1, value2, "engPron");
            return (Criteria) this;
        }

        public Criteria andEngPronNotBetween(String value1, String value2) {
            addCriterion("ENG_PRON not between", value1, value2, "engPron");
            return (Criteria) this;
        }

        public Criteria andUsaPronIsNull() {
            addCriterion("USA_PRON is null");
            return (Criteria) this;
        }

        public Criteria andUsaPronIsNotNull() {
            addCriterion("USA_PRON is not null");
            return (Criteria) this;
        }

        public Criteria andUsaPronEqualTo(String value) {
            addCriterion("USA_PRON =", value, "usaPron");
            return (Criteria) this;
        }

        public Criteria andUsaPronNotEqualTo(String value) {
            addCriterion("USA_PRON <>", value, "usaPron");
            return (Criteria) this;
        }

        public Criteria andUsaPronGreaterThan(String value) {
            addCriterion("USA_PRON >", value, "usaPron");
            return (Criteria) this;
        }

        public Criteria andUsaPronGreaterThanOrEqualTo(String value) {
            addCriterion("USA_PRON >=", value, "usaPron");
            return (Criteria) this;
        }

        public Criteria andUsaPronLessThan(String value) {
            addCriterion("USA_PRON <", value, "usaPron");
            return (Criteria) this;
        }

        public Criteria andUsaPronLessThanOrEqualTo(String value) {
            addCriterion("USA_PRON <=", value, "usaPron");
            return (Criteria) this;
        }

        public Criteria andUsaPronLike(String value) {
            addCriterion("USA_PRON like", value, "usaPron");
            return (Criteria) this;
        }

        public Criteria andUsaPronNotLike(String value) {
            addCriterion("USA_PRON not like", value, "usaPron");
            return (Criteria) this;
        }

        public Criteria andUsaPronIn(List<String> values) {
            addCriterion("USA_PRON in", values, "usaPron");
            return (Criteria) this;
        }

        public Criteria andUsaPronNotIn(List<String> values) {
            addCriterion("USA_PRON not in", values, "usaPron");
            return (Criteria) this;
        }

        public Criteria andUsaPronBetween(String value1, String value2) {
            addCriterion("USA_PRON between", value1, value2, "usaPron");
            return (Criteria) this;
        }

        public Criteria andUsaPronNotBetween(String value1, String value2) {
            addCriterion("USA_PRON not between", value1, value2, "usaPron");
            return (Criteria) this;
        }

        public Criteria andEngMediaIsNull() {
            addCriterion("ENG_MEDIA is null");
            return (Criteria) this;
        }

        public Criteria andEngMediaIsNotNull() {
            addCriterion("ENG_MEDIA is not null");
            return (Criteria) this;
        }

        public Criteria andEngMediaEqualTo(String value) {
            addCriterion("ENG_MEDIA =", value, "engMedia");
            return (Criteria) this;
        }

        public Criteria andEngMediaNotEqualTo(String value) {
            addCriterion("ENG_MEDIA <>", value, "engMedia");
            return (Criteria) this;
        }

        public Criteria andEngMediaGreaterThan(String value) {
            addCriterion("ENG_MEDIA >", value, "engMedia");
            return (Criteria) this;
        }

        public Criteria andEngMediaGreaterThanOrEqualTo(String value) {
            addCriterion("ENG_MEDIA >=", value, "engMedia");
            return (Criteria) this;
        }

        public Criteria andEngMediaLessThan(String value) {
            addCriterion("ENG_MEDIA <", value, "engMedia");
            return (Criteria) this;
        }

        public Criteria andEngMediaLessThanOrEqualTo(String value) {
            addCriterion("ENG_MEDIA <=", value, "engMedia");
            return (Criteria) this;
        }

        public Criteria andEngMediaLike(String value) {
            addCriterion("ENG_MEDIA like", value, "engMedia");
            return (Criteria) this;
        }

        public Criteria andEngMediaNotLike(String value) {
            addCriterion("ENG_MEDIA not like", value, "engMedia");
            return (Criteria) this;
        }

        public Criteria andEngMediaIn(List<String> values) {
            addCriterion("ENG_MEDIA in", values, "engMedia");
            return (Criteria) this;
        }

        public Criteria andEngMediaNotIn(List<String> values) {
            addCriterion("ENG_MEDIA not in", values, "engMedia");
            return (Criteria) this;
        }

        public Criteria andEngMediaBetween(String value1, String value2) {
            addCriterion("ENG_MEDIA between", value1, value2, "engMedia");
            return (Criteria) this;
        }

        public Criteria andEngMediaNotBetween(String value1, String value2) {
            addCriterion("ENG_MEDIA not between", value1, value2, "engMedia");
            return (Criteria) this;
        }

        public Criteria andUsaMediaIsNull() {
            addCriterion("USA_MEDIA is null");
            return (Criteria) this;
        }

        public Criteria andUsaMediaIsNotNull() {
            addCriterion("USA_MEDIA is not null");
            return (Criteria) this;
        }

        public Criteria andUsaMediaEqualTo(String value) {
            addCriterion("USA_MEDIA =", value, "usaMedia");
            return (Criteria) this;
        }

        public Criteria andUsaMediaNotEqualTo(String value) {
            addCriterion("USA_MEDIA <>", value, "usaMedia");
            return (Criteria) this;
        }

        public Criteria andUsaMediaGreaterThan(String value) {
            addCriterion("USA_MEDIA >", value, "usaMedia");
            return (Criteria) this;
        }

        public Criteria andUsaMediaGreaterThanOrEqualTo(String value) {
            addCriterion("USA_MEDIA >=", value, "usaMedia");
            return (Criteria) this;
        }

        public Criteria andUsaMediaLessThan(String value) {
            addCriterion("USA_MEDIA <", value, "usaMedia");
            return (Criteria) this;
        }

        public Criteria andUsaMediaLessThanOrEqualTo(String value) {
            addCriterion("USA_MEDIA <=", value, "usaMedia");
            return (Criteria) this;
        }

        public Criteria andUsaMediaLike(String value) {
            addCriterion("USA_MEDIA like", value, "usaMedia");
            return (Criteria) this;
        }

        public Criteria andUsaMediaNotLike(String value) {
            addCriterion("USA_MEDIA not like", value, "usaMedia");
            return (Criteria) this;
        }

        public Criteria andUsaMediaIn(List<String> values) {
            addCriterion("USA_MEDIA in", values, "usaMedia");
            return (Criteria) this;
        }

        public Criteria andUsaMediaNotIn(List<String> values) {
            addCriterion("USA_MEDIA not in", values, "usaMedia");
            return (Criteria) this;
        }

        public Criteria andUsaMediaBetween(String value1, String value2) {
            addCriterion("USA_MEDIA between", value1, value2, "usaMedia");
            return (Criteria) this;
        }

        public Criteria andUsaMediaNotBetween(String value1, String value2) {
            addCriterion("USA_MEDIA not between", value1, value2, "usaMedia");
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