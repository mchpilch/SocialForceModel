package core.Element;

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

    public Room(float posX, float posY, float width, float height, World world, String name,  float scale, float moveX, float moveY) {
        this.name = name;
        posX = scale*(posX+moveX);
        posY = scale*(posY+moveY);
        width = scale*width;
        height = scale*height;
        createBoxRoom(world, posX, posY, width, height);
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

    public void hit(){
        System.out.println(name + " HIT");
    }
}
