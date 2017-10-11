package dao;
public class TypeMean {
    private Long id;

    private Long wordId;

    private String type;

    private String mean;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean == null ? null : mean.trim();
    }
}