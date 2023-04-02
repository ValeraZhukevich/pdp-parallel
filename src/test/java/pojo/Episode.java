package pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import java.util.List;

@Data
public class Episode {
    @JsonAlias("air_date")
    private String airDate;
    private List<String> characters;
    private String created;
    private String episode;
    private Long id;
    private String name;
    private String url;
}
