package in.adavi.pradyot.web.assistant.api.model.wtm;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Pradyot H Adavi 22/10/17
 */
public class WTMLeadRequest {

    @JsonProperty("chapter_name")
    private String chapterName;

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    @Override
    public String toString() {
        return "WTMLeadRequest{" +
                "chapterName='" + chapterName + '\'' +
                '}';
    }
}
