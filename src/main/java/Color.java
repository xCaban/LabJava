import jakarta.persistence.Embeddable;

@Embeddable
public record Color(int red, int green, int blue, int alpha) {
    public Color {
        boolean gtrThan = red > 255 || green > 255 || blue > 255 || alpha > 255;
        boolean ltThan = red < 0 || green < 0 || blue < 0 || alpha < 0;
        if (gtrThan) {
            throw new IllegalArgumentException("Color can't be greater than 255");
        }
        if (ltThan) {
            throw new IllegalArgumentException("Color can't be less than 0");
        }
    }
    public Color(int R, int G, int B) {
        this(R, G, B, 0);
    }
}
