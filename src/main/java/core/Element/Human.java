package core.Element;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.HashMap;
import java.util.Map;

public class Human{//extends Body


    public Body body; //posiada pozycję, typ ciała, kąt masę więc settery dla Human ustawić że ustawiają wartości w jego body
    public int id;
    public Map<Integer,Vector2> humanForces = new HashMap<>();//id, gravity force with this id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =  id;
    }

    @Override
    public String toString() {
        return "Human{" +
                "humanForces=" + humanForces +
                '}';
    }

    public Body createMan(float x, float y, float radius, float density, World world) {
        this.id = createID();
        CircleShape poly = new CircleShape();
        poly.setRadius(radius);

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        body.createFixture(poly, density);
        body.setTransform(x, y, 0);
        body.setFixedRotation(true); //dzięki temu ja odpowiadam za obrót a nie silnik, no. przy zderzeniach
        poly.dispose();

        return body;
    }

    private static int idCounter = 0;

    public static synchronized int createID()
    {
        return idCounter++;
    }
}
