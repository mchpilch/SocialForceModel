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

    public Door exit;

    public Room room;

    public Door getExit() {
        return exit;
    }

    public void setExit(Door exit) {
        this.exit = exit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =  id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Human{" +
                "body=" + body +
                ", id=" + id +
                ", humanForces=" + humanForces +
                ", exit=" + exit +
                ", room=" + room +
                '}';
    }

    public Body createMan(float x, float y, float radius, float density, Door firstExit, World world) {
        this.id = createID();

        CircleShape poly = new CircleShape();
        poly.setRadius(radius);

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        body.createFixture(poly, density).setUserData(this);
        body.setTransform(x, y, 0);

        body.setFixedRotation(true); //dzięki temu ja odpowiadam za obrót a nie silnik, no. przy zderzeniach
        poly.dispose();

        setExit(firstExit);

        //policzenie pierwszych drzwi
        return body;
    }

    private static int idCounter = 0;

    public static synchronized int createID()
    {
        return idCounter++;
    }
}
