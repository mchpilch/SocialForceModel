package core.Elements;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.World;

public class Wall {

    float x1, y1, x2, y2;

    public float getX1() {
        return x1;
    }

    public float getY1() {
        return y1;
    }

    public float getX2() {
        return x2;
    }

    public float getY2() {
        return y2;
    }

    public Body body;

    public Body createWall(float x1, float y1, float x2, float y2, float density, World world) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;


        EdgeShape poly = new EdgeShape();
        poly.set(new Vector2(0, 0), new Vector2(this.x2 - this.x1, this.y2 - this.y1));

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(def);
        body.createFixture(poly, density);
        body.setTransform(this.x1, this.y1, 0);
        poly.dispose();


        return body;
    }
}
