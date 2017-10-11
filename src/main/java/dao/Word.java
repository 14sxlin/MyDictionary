package dao;

public class Word {
    private Long id;

    private String eng;

    private String engPron;

    private String usaPron;

    private String engMedia;

    private String usaMedia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng == null ? null : eng.trim();
    }

    public String getEngPron() {
        return engPron;
    }

    public void setEngPron(String engPron) {
        this.engPron = engPron == null ? null : engPron.trim();
    }

    public String getUsaPron() {
        return usaPron;
    }

    public void setUsaPron(String usaPron) {
        this.usaPron = usaPron == null ? null : usaPron.trim();
    }

    public String getEngMedia() {
        return engMedia;
    }

    public void setEngMedia(String engMedia) {
        this.engMedia = engMedia == null ? null : engMedia.trim();
    }

    public String getUsaMedia() {
        return usaMedia;
    }

    public void setUsaMedia(String usaMedia) {
        this.usaMedia = usaMedia == null ? null : usaMedia.trim();
    }
}