package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private Long id;
    private String created;
    private String dimension;
    private String name;
    private List<String> residents;
    private String type;
    private String url;
}
