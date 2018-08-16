package ws.dto;

public class RqDto {
    private String name;

    protected RqDto() {
    }

    public RqDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("RqDto{name='%s'}", name);
    }
}
