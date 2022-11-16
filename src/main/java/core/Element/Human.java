package core.Element;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

public class Human {//extends Body

    public int id;

    private static int idCounter = 0;
    boolean isSave = false;

    boolean wasBodyDestroyed = false;
    float timeOfEvacuation = 10000f;
    public Door exit;
    public Room room;
    public Body body; //posiada pozycję, typ ciała, kąt masę więc settery dla Human ustawić że ustawiają wartości w jego body

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

    @Override
    public String toString() {
        return "Human{" +
                " id=" + id +
                ", exit=" + exit.getCode() +
                ", room=" + room.getName() +
                ", isSave=" + isSave() +
                '}';
    }

    public static synchronized int createID() {
        return idCounter++;
    }//id to kolejne liczby całkowite większe od zera

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSave() {
        return isSave;
    }

    public void setSave(boolean save) {
        isSave = save;
    }

    public boolean isWasBodyDestroyed() {
        return wasBodyDestroyed;
    }

    public void setWasBodyDestroyed(boolean wasBodyDestroyed) {
        this.wasBodyDestroyed = wasBodyDestroyed;
    }

    public float getTimeOfEvacuation() {
        return timeOfEvacuation;
    }

    public void setTimeOfEvacuation(float timeOfEvacuation) {
        this.timeOfEvacuation = timeOfEvacuation;
    }

    public Door getExit() {
        return exit;
    }

    public void setExit(Door exit) {
        this.exit = exit;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
