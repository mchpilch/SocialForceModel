package core.Element;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Room {
    String name;

    public Body body;

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room(float posX, float posY, float width, float height, World world, String name,  float scale) {
        this.name = name;
        posX = scale*(posX);
        posY = scale*(posY);
        width = scale*width;
        height = scale*height;
        createBoxRoom(world, posX, posY, width, height);
    }

    public Room(float posX, float posY, Vector2[] vecArr, World world, String name, float scale, float moveX, float moveY) {
        this.name = name;
        posX = (posX + moveX);
        posY = (posY + moveY);
        createPolyRoom(world, posX, posY, vecArr, scale);
    }

    private void createBoxRoom(World world, float posX, float posY, float width, float height){
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX,posY);

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = poly;
        fixtureDef.density = 1.0f;
        fixtureDef.isSensor = true;//

        //poly.dispose();

        this.body = world.createBody(bodyDef);
        this.body.createFixture(fixtureDef).setUserData(this); //fixture considers itself as RoomObject, OK?
    }

    private void createPolyRoom(World world, float posX, float posY, Vector2[] vecArr, float scale){
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(posX,posY);

        PolygonShape poly = new PolygonShape();

        for(int i = 0; i < vecArr.length;i++){
            vecArr[i] = vecArr[i].scl(scale);
        }

        poly.set(vecArr);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = poly;
        fixtureDef.density = 1.0f;
        fixtureDef.isSensor = true;//

        //poly.dispose();

        this.body = world.createBody(bodyDef);
        this.body.createFixture(fixtureDef).setUserData(this); //fixture considers itself as RoomObject, OK?
    }


}
