package core.Element;

public class Door {
    private float x,y;
    private String code;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Door(float x, float y, String code, float scale, float moveX, float moveY) {
        this.x = scale*(x+moveX);
        this.y = scale*(y+moveY);
        this.code = code;
    }
}
