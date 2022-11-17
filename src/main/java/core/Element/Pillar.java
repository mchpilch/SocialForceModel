package core.Element;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

public class Pillar {
    float x ,y;
    float radius;
    public Body body;

    public Pillar(float x, float y, float radius, World world) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        createPillar(this.x,this.y,this.radius,1000, world);
    }

    public Body createPillar(float x, float y, float radius, float density, World world) {


        CircleShape poly = new CircleShape();
        poly.setRadius(radius);

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(def);
        body.createFixture(poly, density);
        body.setTransform(x, y, 0);

        poly.dispose();



        //policzenie pierwszych drzwi
        return body;
    }

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
}
