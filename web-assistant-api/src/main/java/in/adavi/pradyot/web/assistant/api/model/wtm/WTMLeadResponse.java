package in.adavi.pradyot.web.assistant.api.model.wtm;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Pradyot H Adavi 22/10/17
 */
public class WTMLeadResponse {


    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WTMLeadResponse{" +
                "name='" + name + '\'' +
                '}';
    }
}
