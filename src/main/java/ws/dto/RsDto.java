package ws.dto;

public class RsDto {

    private String content;
    private static int id;

    public RsDto() {
    }

    public RsDto(String content) {
        this.content = content;
        this.id++;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }
}
