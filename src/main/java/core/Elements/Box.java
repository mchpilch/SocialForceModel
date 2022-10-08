package core.Elements;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Box {
    float width, height;
    Body body;

    public Box(float width, float height) {
        this.width = width;
        this.height = height;

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(this.width, this.height);

        BodyDef def = new BodyDef();
//        def.type = BodyDef.BodyType.StaticBody;
//        Body body = world.createBody(def);
//        body.createFixture(poly, 5f);
//        body.setTransform(x, y, 0);
        poly.dispose();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Box{" +
                " x = " + body.getPosition().x +
                ", y =" +  body.getPosition().y +
                ", w =" + width +
                ", h =" + height +
                ", body =" + body +
                '}';
    }
}
