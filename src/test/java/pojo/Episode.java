package pojo;

import lombok.Data;
import java.util.List;

@Data
public class Episode {
    private String airDate;
    private List<String> characters;
    private String created;
    private String episode;
    private Long id;
    private String name;
    private String url;
}
