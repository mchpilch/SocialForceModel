package core.Room;

import com.badlogic.gdx.physics.box2d.*;

public class RoomListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {// contact contains two fixtures A and B
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa == null || fb == null || fa.getUserData() == null || fb.getUserData() == null) return;

     }

    @Override
    public void endContact(Contact contact) {

    }

    //do not mess with them below
    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
};
